package com.example.android.news;

/**
 * Created by Rachit on 12/4/2016.
 */
public class NewsClass {
    private String mSection;
    private String mNews;
    private String mUrl;
    public NewsClass(String section,String news,String url){
        mSection=section;
        mNews=news;
        mUrl=url;
    }
    public String getSection(){
        return mSection;
    }
    public String getNews(){
        return mNews;
    }
    public String getUrl(){
        return mUrl;
    }
}
