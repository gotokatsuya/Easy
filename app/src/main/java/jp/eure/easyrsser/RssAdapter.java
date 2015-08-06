package jp.eure.easyrsser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import jp.eure.easyrsser.model.entity.RSSItem;

/**
 * Created by katsuyagoto on 15/07/31.
 */
public class RssAdapter extends ArrayAdapter<RSSItem> {

    public RssAdapter(Context context) {
        super(context, 0);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_rss_list, null);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.title);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        RSSItem rssItem = getItem(position);
        holder.title.setText(rssItem.title);
        return convertView;
    }

    public static class ViewHolder {

        TextView title;
    }

}
