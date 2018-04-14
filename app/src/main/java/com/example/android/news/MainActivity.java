package com.example.android.news;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<NewsClass>>{

    String news_api=
            "http://content.guardianapis.com/search?page-size=10&q=news&api-key=0fc305c0-c13b-40dd-b619-cc5149f58189";
    private int loader_id=1;
    private NewsAdapter mAdapter=null;
    private TextView mEmptyTextView=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView newsList = findViewById(R.id.list);
        mEmptyTextView = findViewById(R.id.empty_view);
        mAdapter = new NewsAdapter(this, new ArrayList<NewsClass>());

        newsList.setAdapter(mAdapter);
        newsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                NewsClass currentNews = mAdapter.getItem(position);
                Uri newsUri = Uri.parse(currentNews.getUrl());
                Intent newsIntent = new Intent(Intent.ACTION_VIEW, newsUri);
                startActivity(newsIntent);
            }
        });
        android.app.LoaderManager loaderManager;

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            mEmptyTextView.setVisibility(View.GONE);
            loaderManager = getLoaderManager();
            loaderManager.initLoader(loader_id, null, MainActivity.this);
        } else {
            View loadingIndicator = findViewById(R.id.loading_bar);
            loadingIndicator.setVisibility(View.INVISIBLE);
            mEmptyTextView.setText(getString(R.string.no_connection));
        }
    }

    @Override
    public Loader<List<NewsClass>> onCreateLoader(int id, Bundle args) {
        return new NewsLoader(this,news_api);
    }

    @Override
    public void onLoadFinished(Loader<List<NewsClass>> loader, List<NewsClass> data) {
        mEmptyTextView.setText(getString(R.string.loading_error));
        mAdapter.clear();
        ProgressBar progress = findViewById(R.id.loading_bar);
        progress.setVisibility(View.GONE);
        if (data != null && !data.isEmpty()) {
            mAdapter.addAll(data);
        } else mEmptyTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoaderReset(Loader<List<NewsClass>> loader) {
        mAdapter.clear();
    }
}
