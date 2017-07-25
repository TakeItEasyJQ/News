package com.example.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by My Computer on 2017/7/25.
 */

public class ContentFragment extends Fragment {   //显示标题缩略图简介日期来源的
    @Nullable
    public static List<Content> contentList=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.main_content,container,false);
        TextView title=(TextView)view.findViewById(R.id.cont_title);
        ImageView pic=(ImageView)view.findViewById(R.id.cont_img);
        TextView content=(TextView)view.findViewById(R.id.cont_content);
        TextView date=(TextView)view.findViewById(R.id.cont_date);
        TextView source=(TextView)view.findViewById(R.id.cont_source);
        return  view;
    }
}
