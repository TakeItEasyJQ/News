package com.example.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.news.Utility.HttpRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by My Computer on 2017/7/24.
 */

public class TypeFragmen extends Fragment {
    private static final String TAG = "TypeFragmen";
    public static List typenames=new ArrayList();
    private ListView listview;
    public static ArrayAdapter adapter;
    public static Button back;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.type,container,false);
        back=(Button)view.findViewById(R.id.back);
        listview=(ListView)view.findViewById(R.id.type_listview);
        adapter=new ArrayAdapter(getContext(),R.layout.listview_item,typenames);
        listview.setAdapter(adapter);
        Log.d(TAG, "onCreateView: "+typenames.size());
        return view;
    }
}
