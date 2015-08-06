package jp.eure.easyrsser;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import jp.eure.easyrsser.model.entity.RSSItem;
import jp.eure.easyrsser.model.repository.RssItemRepository;
import jp.eure.easyrsser.rss.RSSFeed;
import jp.eure.easyrsser.rss.RSSReader;
import jp.eure.easyrsser.rss.RSSReaderException;

public class RssFragment extends Fragment implements AbsListView.OnItemClickListener {

    private ListView mListView;

    private RssAdapter mAdapter;

    public static RssFragment newInstance() {
        return new RssFragment();
    }

    public RssFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new RssAdapter(getActivity());
        fetchRss();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rss_list, container, false);
        mListView = (ListView) view.findViewById(android.R.id.list);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
        return view;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }

    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();
        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

    private void fetchRss() {
        mAdapter.addAll(RssItemRepository.findAll());

        // Debugging. check if database is not empty;
        if (!mAdapter.isEmpty()) {
            return;
        }

        final String uri = "http://www.pairs.lv/feed";
        new AsyncTask<Void, Void, List<RSSItem>>() {
            @Override
            protected List<RSSItem> doInBackground(Void... params) {
                RSSReader reader = new RSSReader();
                final RSSFeed feed;
                try {
                    feed = reader.load(uri);
                    if (feed != null) {
                        return feed.getItems();
                    }
                } catch (RSSReaderException e) {
                    e.printStackTrace();
                }
                return new ArrayList<RSSItem>();
            }

            @Override
            protected void onPostExecute(List<RSSItem> result) {
                mAdapter.addAll(result);
            }
        }.execute();
    }

}
