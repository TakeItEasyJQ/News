package com.example.news;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.news.Class.Type;
import com.example.news.Utility.MyTask;
import com.example.news.Utility.TypeAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by My Computer on 2017/7/24.
 */

public class TypeFragmen extends Fragment {
    public static List<Type> typeList = new ArrayList<>();
    private ListView listview;
    public static TypeAdapter adapter;
    public static Button back;
    public static String channelid;
    public static String name;
//    private String url;
//    private JSONObject object1;
//    private JSONObject object2;
//    private JSONObject object3;
//    private JSONArray jsonArray;
//    public static int size;
//    private JSONArray urlarray;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.type, container, false);
        back = (Button) view.findViewById(R.id.back);
        listview = (ListView) view.findViewById(R.id.type_listview);
        adapter = new TypeAdapter(getContext(), R.layout.listview_item, typeList);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                MainActivity.drawerlayout.closeDrawers();
                MainActivity.refresh.setRefreshing(true);
                Type type = typeList.get(position);
                channelid = type.getId();
                name = type.getName();
                SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(getContext());
                SharedPreferences.Editor editor=pref.edit();
                editor.putString("channelId",channelid);
                editor.putString("name",name);
                editor.apply();
                new MyTask(channelid, name).execute();
                MainActivity activity = (MainActivity) getActivity();
                activity.title.setText(name);
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        ContentFragment.contentList.clear();
//                        try {
//                            object1 = new JSONObject(HttpRequest.HttpRequest(Config.request_news, channelid, name));
//                            object2 = object1.getJSONObject(Config.ACTION_showapi_res_body);
//                            object3 = object2.getJSONObject(Config.ACTION_pagebean);
//                            jsonArray = object3.getJSONArray(Config.ACTION_contentlist);  //内容的List
//                            for (int i = 0; i < jsonArray.length(); i++) {
//                                Content content = new Content();
//                                JSONObject jsonObject = jsonArray.getJSONObject(i);
//                                content.setHasPic(jsonObject.getBoolean(Config.ACTION_havePic));
//                                content.setDate(jsonObject.getString(Config.ACTION_pubDate));
//                                content.setTitle(jsonObject.getString(Config.ACTION_title));
//                                if (jsonObject.getBoolean(Config.ACTION_havePic)){
//                                    urlarray = jsonObject.getJSONArray(Config.ACTION_imageurls);
//                                    url=urlarray.getJSONObject(0).getString(Config.ACTION_imageUrl);
//                                    content.setPicUrl(url);
//                                }else {
//                                    content.setPicUrl(null);
//                                }
//                                content.setShortContent(jsonObject.getString(Config.ACTION_desc));
//                                content.setSource(jsonObject.getString(Config.ACTION_source));
//                                content.setLinkUrl(jsonObject.getString(Config.ACTION_link));
//                                ContentFragment.contentList.add(content);
//                            }
//                            size = jsonArray.length();
//                            if (ContentFragment.contentList.size() == size) {
//                                MainActivity.show = true;
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }).start();

//                MainActivity activity = (MainActivity) getActivity();
//                if (MainActivity.show = true) {
//                    activity.showContent();
//                }
            }
        });
        return view;
    }
}

