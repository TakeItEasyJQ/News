package com.example.news;

import android.app.Activity;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.news.Class.Type;
import com.example.news.Utility.HttpRequest;
import com.example.news.Utility.TypeAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

/**
 * Created by My Computer on 2017/7/24.
 */

public class TypeFragmen extends Fragment {
    private static final String TAG = "TypeFragmen";
    public static List<Type> typeList=new ArrayList<>();
    private ListView listview;
    public static TypeAdapter adapter;
    public static Button back;
    private String channelid;
    private String name;
    private String url;
    private JSONObject object1;
    private JSONObject object2;
    private JSONObject object3;
    private JSONArray jsonArray;
    private JSONArray urlarray;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.type,container,false);
        back=(Button)view.findViewById(R.id.back);
        listview=(ListView)view.findViewById(R.id.type_listview);
        adapter=new TypeAdapter(getContext(),R.layout.listview_item,typeList);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Type type=typeList.get(position);
                channelid=type.getId();
                name=type.getName();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ContentFragment.contentList.clear();
                        try {
                            object1=new JSONObject(HttpRequest.HttpRequest(Config.request_news,channelid,name));
                            object2=object1.getJSONObject(Config.ACTION_showapi_res_body);
                            object3=object2.getJSONObject(Config.ACTION_pagebean);
                            jsonArray=object3.getJSONArray(Config.ACTION_contentlist);
                            for (int i=0;i<jsonArray.length();i++){
                                Content content=new Content();
                                JSONObject jsonObject=jsonArray.getJSONObject(i);
                                content.setHasPic(jsonObject.getBoolean(Config.ACTION_havePic));
                                content.setDate(jsonObject.getString(Config.ACTION_pubDate));
                                content.setTitle(jsonObject.getString(Config.ACTION_title));
                                urlarray=jsonObject.getJSONArray(Config.ACTION_imageurls);
//                                for (int j=0;j<urlarray.length();j++){
//                                    JSONObject jo=urlarray.getJSONObject(j);
//                                    content.setPicUrl(jo.getString(Config.ACTION_imageUrl));
//                                }
                                content.setShortContent(jsonObject.getString(Config.ACTION_desc));
                                content.setSource(jsonObject.getString(Config.ACTION_source));
                                content.setLinkUrl(jsonObject.getString(Config.ACTION_link));
                                ContentFragment.contentList.add(content);
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }).start();
                MainActivity.drawerlayout.closeDrawers();
                MainActivity activity=(MainActivity)getActivity();
                if (ContentFragment.contentList.size()==20){
                    activity.showContent();
                }
            }
        });
        return view;
    }
}
