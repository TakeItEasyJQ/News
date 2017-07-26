package com.example.news.Utility;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.news.Content;
import com.example.news.ContentFragment;
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
        Content cont= ContentFragment.contentList.get(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView title=(TextView)view.findViewById(R.id.cont_title);
        ImageView pic=(ImageView)view.findViewById(R.id.cont_img);
        TextView content=(TextView)view.findViewById(R.id.cont_content);
        TextView date=(TextView)view.findViewById(R.id.cont_date);
        TextView source=(TextView)view.findViewById(R.id.cont_source);
        title.setText(cont.getTitle());
        content.setText(cont.getShortContent());
        date.setText(cont.getDate());
        source.setText(cont.getSource());
        if (cont.isHasPic()){
            Glide.with(getContext()).load(cont.getPicUrl()).into(pic);
        }else {
            pic.setVisibility(View.GONE);
        }
        return view;
    }
}
