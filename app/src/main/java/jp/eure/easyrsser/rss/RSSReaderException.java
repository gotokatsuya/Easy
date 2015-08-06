package jp.eure.easyrsser.rss;

public class RSSReaderException extends Exception {

    private final int status;

    public RSSReaderException(int status, String message) {
        super(message);
        this.status = status;
    }

    public RSSReaderException(int status, Throwable cause) {
        super(cause);
        this.status = status;
    }

    public RSSReaderException(int status, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}

