package com.goka.easy;

import android.text.TextUtils;
import android.util.Log;

import timber.log.Timber;

/**
 * Created by katsuyagoto on 15/08/12.
 */
public class ERDebugTree extends Timber.DebugTree {

    private static final int MAX_LOG_LENGTH = 4000;

    private static final String STACK_INFO_FORMAT = " at %s(%s:%s)";

    private static final String THREAD_INFO_FORMAT = " on thread %d";

    private String mOptionInfo;

    @Override
    protected void log(int priority, String tag, String message, Throwable t) {
        mOptionInfo = getStackInfo(new Throwable().getStackTrace());
        mOptionInfo = mOptionInfo + threadID();

        if (message.length() < MAX_LOG_LENGTH) {
            printSingleLine(priority, tag, message + mOptionInfo);
        } else {
            printMultipleLines(priority, tag, message);
        }
    }

    private void printMultipleLines(int priority, String tag, String message) {
        for (int i = 0, length = message.length(); i < length; i++) {
            int newline = message.indexOf('\n', i);
            newline = newline != -1 ? newline : length;
            do {
                int end = Math.min(newline, i + MAX_LOG_LENGTH);
                String part = message.substring(i, end);
                printSingleLine(priority, tag, part);
                i = end;
            } while (i < newline);
        }

        if (!TextUtils.isEmpty(mOptionInfo)) {
            printSingleLine(priority, tag, mOptionInfo);
        }
    }

    private void printSingleLine(int priority, String tag, String message) {
        if (priority == Log.ASSERT) {
            Log.wtf(tag, message);
        } else {
            Log.println(priority, tag, message);
        }
    }

    private String getStackInfo(StackTraceElement[] stacks) {
        if (stacks == null || stacks.length < 5) {
            return "";
        }
        return stackInfo(stacks[5]);
    }

    private static String stackInfo(StackTraceElement stack) {
        String className = stack.getClassName();
        String packageName = className.substring(0, className.lastIndexOf("."));
        return String.format(STACK_INFO_FORMAT, packageName,
                stack.getFileName(), stack.getLineNumber());
    }

    private static String threadID() {
        return String.format(THREAD_INFO_FORMAT, Thread.currentThread().getId());
    }
}
