package com.example.news.Utility;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.news.R;

import java.util.List;

/**
 * Created by My Computer on 2017/7/25.
 */

public class ContentAdapter extends ArrayAdapter {

    private int resourceId;
    public ContentAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        this.resourceId=resource;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view= LayoutInflater.from(getContext()).inflate(R.layout.content_item,parent,false);


    }
}
