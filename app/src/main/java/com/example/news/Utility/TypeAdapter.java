package com.example.news.Utility;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.news.Class.Type;
import com.example.news.R;
import com.example.news.TypeFragmen;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by My Computer on 2017/7/26.
 */

public class TypeAdapter extends ArrayAdapter {
    private List<Type> types=new ArrayList<>();
    private int resouceId;
    public TypeAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        this.resouceId=resource;
        this.types=(List<Type>)objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view= LayoutInflater.from(getContext()).inflate(resouceId,parent,false);
        Type type= types.get(position);
        TextView typename=(TextView)view.findViewById(R.id.type_name);
        typename.setText(type.getName());
        return view;
    }
}
