package com.example.android.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Rachit on 12/4/2016.
 */
public class NewsAdapter extends ArrayAdapter<NewsClass> {
    public NewsAdapter(Context context, List<NewsClass> news) {
        super(context, 0,news);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        NewsClass currentNews = getItem(position);
        TextView section = (TextView) listItemView.findViewById(R.id.section);
        section.setText(currentNews.getSection());
        TextView news = (TextView) listItemView.findViewById(R.id.news);
        news.setText(currentNews.getNews());
        return listItemView;
    }
}
