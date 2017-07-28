package com.example.news.Utility;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.example.news.Config;
import com.example.news.Content;
import com.example.news.ContentFragment;
import com.example.news.MainActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by My Computer on 2017/7/27.
 */

public class MyTask extends AsyncTask<String, Void, Void> {
    private String id;
    private String name;
    private String url;
    private JSONObject object1;
    private JSONObject object2;
    private JSONObject object3;
    private JSONArray jsonArray;
    private JSONArray urlarray;
    private List<Content> contents=new ArrayList<>();
    public MyTask(String id, String name) {
        super();
        this.id = id;
        this.name = name;
    }


    @Override
    protected Void doInBackground(String... params) {
        ContentFragment.contentList.clear();
        try {
            object1 = new JSONObject(HttpRequest.HttpRequest(Config.request_news, id, name));
            object2 = object1.getJSONObject(Config.ACTION_showapi_res_body);
            object3 = object2.getJSONObject(Config.ACTION_pagebean);
            jsonArray = object3.getJSONArray(Config.ACTION_contentlist);  //内容的List
            for (int i = 0; i < jsonArray.length(); i++) {
                Content content = new Content();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                content.setHasPic(jsonObject.getBoolean(Config.ACTION_havePic));
                content.setDate(jsonObject.getString(Config.ACTION_pubDate).substring(10,19));
                content.setTitle(jsonObject.getString(Config.ACTION_title));
                if (jsonObject.getBoolean(Config.ACTION_havePic)) {
                    urlarray = jsonObject.getJSONArray(Config.ACTION_imageurls);
                    url = urlarray.getJSONObject(0).getString(Config.ACTION_imageUrl);
                    content.setPicUrl(url);
                } else {
                    content.setPicUrl(null);
                }
                content.setShortContent(jsonObject.getString(Config.ACTION_desc));
                content.setSource("来源: "+jsonObject.getString(Config.ACTION_source));
                content.setLinkUrl(jsonObject.getString(Config.ACTION_link));
                ContentFragment.contentList.add(content);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        MainActivity.dialog.cancel();
        ContentFragment.adapter.notifyDataSetChanged();
        MainActivity.refresh.setRefreshing(false);
    }
}
