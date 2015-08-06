package jp.eure.easyrsser.rss;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class RSSReader {

    private final OkHttpClient client = new OkHttpClient();

    private final RSSParser parser = new RSSParser();

    public RSSReader() {
    }

    public RSSFeed load(String uri) throws RSSReaderException {
        Request request = new Request.Builder()
                .url(uri)
                .get()
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new RSSReaderException(response.code(), response.message());
            }
            RSSFeed feed = parser.parse(response.body().byteStream());
            return feed;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

