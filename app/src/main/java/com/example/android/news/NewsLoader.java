package com.example.android.news;

import android.content.Context;
import android.content.AsyncTaskLoader;

import java.util.List;

/**
 * Created by Rachit on 12/4/2016.
 */
public class NewsLoader extends AsyncTaskLoader<List<NewsClass>>{
    private String mUrl;

    public NewsLoader(Context context, String url){
        super(context);
        mUrl=url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<NewsClass> loadInBackground() {
        if(mUrl==null){
            return null;
        }
        return NewsUtils.fetchNewsInfo(mUrl);
    }
}
