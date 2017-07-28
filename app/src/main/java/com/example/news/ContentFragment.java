package com.example.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.news.Utility.ContentAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by My Computer on 2017/7/25.
 */

public class ContentFragment extends Fragment {   //显示标题缩略图简介日期来源的
    @Nullable
    public static List<Content> contentList = new ArrayList<>();
    public static ContentAdapter adapter;
    public static ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_content, container, false);
        adapter = new ContentAdapter(getContext(), R.layout.content_item, contentList);
        listView = (ListView) view.findViewById(R.id.cont_listview);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Content cont = contentList.get(position);
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("link", cont.getLinkUrl());
                startActivity(intent);
            }
        });
        return view;
    }

}
