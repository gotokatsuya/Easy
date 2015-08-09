package jp.eure.easyrsser.view.fragment;

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

import jp.eure.easyrsser.R;
import jp.eure.easyrsser.model.entity.ArticleModel;
import jp.eure.easyrsser.model.entity.xml.RSS;
import jp.eure.easyrsser.model.net.RssClient;
import jp.eure.easyrsser.model.repository.ArticleRepository;
import jp.eure.easyrsser.model.repository.RSSRepository;
import jp.eure.easyrsser.view.adapter.RssAdapter;

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

        mAdapter.addAll(ArticleRepository.findAll());

        final String endpoint = "http://www.pairs.lv";
        new AsyncTask<Void, Void, List<ArticleModel>>() {
            @Override
            protected List<ArticleModel> doInBackground(Void... params) {
                RSS rss = RssClient.get(endpoint);
                boolean save = RSSRepository.save(rss);
                return save ? ArticleRepository.findAll() : new ArrayList<ArticleModel>();
            }

            @Override
            protected void onPostExecute(List<ArticleModel> result) {
                if (result.isEmpty()) {
                    return;
                }
                mAdapter.clear();
                mAdapter.addAll(result);
            }
        }.execute();
    }

}
