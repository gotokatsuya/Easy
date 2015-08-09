package jp.eure.easyrsser.rss;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import android.net.Uri;

import java.util.Date;
import java.util.Map;

import jp.eure.easyrsser.model.entity.MediaEnclosureModel;
import jp.eure.easyrsser.model.entity.MediaThumbnailModel;
import jp.eure.easyrsser.model.entity.RssModel;
import jp.eure.easyrsser.model.repository.MediaThumbnailRepository;
import jp.eure.easyrsser.model.repository.RssItemRepository;

public class RSSHandler extends DefaultHandler {

    private static final String RSS_ITEM = "item";

    private final Map<String, Setter> setters;

    private final RSSFeed feed = new RSSFeed();

    private RssModel item;

    private StringBuilder buffer;

    private Setter setter;

    private interface Setter {

    }

    private interface ContentSetter extends Setter {

        void set(String value);
    }

    private interface AttributeSetter extends Setter {

        void set(Attributes attributes);
    }

    private final Setter SET_TITLE = new ContentSetter() {
        @Override
        public void set(String title) {
            if (item != null) {
                item.title = title;
            }
        }
    };

    private final Setter SET_DESCRIPTION = new ContentSetter() {
        @Override
        public void set(String description) {
            if (item != null) {
                item.description = description;
            }
        }
    };

    private final Setter SET_CONTENT = new ContentSetter() {
        @Override
        public void set(String content) {
            if (item != null) {
                item.content = content;
            }
        }
    };

    private final Setter SET_LINK = new ContentSetter() {
        @Override
        public void set(String link) {
            final Uri uri = Uri.parse(link);
            if (item != null) {
                item.link = uri;
            }
        }
    };

    private final Setter SET_PUB_DATE = new ContentSetter() {
        @Override
        public void set(String pubDate) {
            final Date date = Utils.parseRfc822(pubDate);
            if (item != null) {
                item.pubDate = date;
            }
        }
    };

    private final Setter SET_LAST_BUILD_DATE = new ContentSetter() {
        @Override
        public void set(String pubDate) {
            final Date date = Utils.parseRfc822(pubDate);
            if (item == null) {
                feed.setLastBuildDate(date);
            }
        }
    };

    private final Setter SET_TTL = new ContentSetter() {
        @Override
        public void set(String ttl) {
            final Integer value = Utils.parseInteger(ttl);
            if (item == null) {
                feed.setTTL(value);
            }
        }
    };

    private final Setter ADD_CATEGORY = new ContentSetter() {

        @Override
        public void set(String category) {
            if (item != null) {
                item.addCategory(category);
            }
        }
    };

    private final Setter ADD_MEDIA_THUMBNAIL = new AttributeSetter() {

        private static final String MEDIA_THUMBNAIL_HEIGHT = "height";

        private static final String MEDIA_THUMBNAIL_WIDTH = "width";

        private static final String MEDIA_THUMBNAIL_URL = "url";

        private static final int DEFAULT_DIMENSION = -1;

        @Override
        public void set(org.xml.sax.Attributes attributes) {
            if (item == null) {
                return;
            }

            final int height = MediaAttributes.intValue(attributes, MEDIA_THUMBNAIL_HEIGHT, DEFAULT_DIMENSION);
            final int width = MediaAttributes.intValue(attributes, MEDIA_THUMBNAIL_WIDTH, DEFAULT_DIMENSION);
            final String url = MediaAttributes.stringValue(attributes, MEDIA_THUMBNAIL_URL);

            if (url == null) {
                return;
            }

            MediaThumbnailModel mediaThumbnailModel = new MediaThumbnailModel();
            mediaThumbnailModel.url = Uri.parse(url);
            mediaThumbnailModel.height = height;
            mediaThumbnailModel.width = width;
            MediaThumbnailRepository.save(item, mediaThumbnailModel);
        }

    };

    private final Setter SET_ENCLOSURE = new AttributeSetter() {

        private static final String URL = "url";

        private static final String LENGTH = "length";

        private static final String MIME_TYPE = "type";

        @Override
        public void set(org.xml.sax.Attributes attributes) {
            if (item == null) {
                return;
            }

            final String url = MediaAttributes.stringValue(attributes, URL);
            final Integer length = MediaAttributes.intValue(attributes, LENGTH);
            final String mimeType = MediaAttributes.stringValue(attributes, MIME_TYPE);

            if (url == null || length == null || mimeType == null) {
                return;
            }

            MediaEnclosureModel mediaEnclosureModel = new MediaEnclosureModel();
            mediaEnclosureModel.url = Uri.parse(url);
            mediaEnclosureModel.length = length;
            mediaEnclosureModel.mimeType = mimeType;
            mediaEnclosureModel.save();
            item.enclosure = mediaEnclosureModel;
        }
    };

    public RSSHandler() {
        setters = new java.util.HashMap<String, Setter>();
        setters.put("title", SET_TITLE);
        setters.put("description", SET_DESCRIPTION);
        setters.put("content:encoded", SET_CONTENT);
        setters.put("link", SET_LINK);
        setters.put("category", ADD_CATEGORY);
        setters.put("pubDate", SET_PUB_DATE);
        setters.put("media:thumbnail", ADD_MEDIA_THUMBNAIL);
        setters.put("lastBuildDate", SET_LAST_BUILD_DATE);
        setters.put("ttl", SET_TTL);
        setters.put("enclosure", SET_ENCLOSURE);
    }

    public RSSFeed feed() {
        return feed;
    }

    @Override
    public void startElement(String nsURI, String localName, String qname,
            org.xml.sax.Attributes attributes) {
        setter = setters.get(qname);
        if (setter == null) {
            if (RSS_ITEM.equals(qname)) {
                item = new RssModel();
            }
        } else if (setter instanceof AttributeSetter) {
            ((AttributeSetter) setter).set(attributes);
        } else {
            buffer = new StringBuilder();
        }
    }

    @Override
    public void endElement(String nsURI, String localName, String qname) {
        if (isBuffering()) {
            ((ContentSetter) setter).set(buffer.toString());
            buffer = null;
        } else if (RSS_ITEM.equals(qname)) {
            if (item != null) {
                RssItemRepository.save(item);
                feed.addItem(item);
                item = null;
            }
        }
    }

    @Override
    public void characters(char ch[], int start, int length) {
        if (isBuffering()) {
            buffer.append(ch, start, length);
        }
    }

    private boolean isBuffering() {
        return buffer != null && setter != null;
    }

}

