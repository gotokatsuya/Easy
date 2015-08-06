package jp.eure.easyrsser.rss;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class RSSParser {

    public RSSParser() {
    }

    public RSSFeed parse(InputStream feed) {
        try {
            final SAXParserFactory factory = SAXParserFactory.newInstance();

            factory.setFeature("http://xml.org/sax/features/namespaces", false);
            factory.setFeature("http://xml.org/sax/features/namespace-prefixes", true);

            final SAXParser parser = factory.newSAXParser();

            return parse(parser, feed);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private RSSFeed parse(SAXParser parser, InputStream feed)
            throws SAXException, IOException {
        if (parser == null) {
            throw new IllegalArgumentException("RSS parser must not be null.");
        } else if (feed == null) {
            throw new IllegalArgumentException("RSS feed must not be null.");
        }

        final InputSource source = new InputSource(feed);
        final XMLReader xmlreader = parser.getXMLReader();
        final RSSHandler handler = new RSSHandler();

        xmlreader.setContentHandler(handler);
        xmlreader.parse(source);

        return handler.feed();
    }

}

