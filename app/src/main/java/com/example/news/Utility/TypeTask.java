package com.example.news.Utility;

import android.os.AsyncTask;

import com.example.news.Class.Type;
import com.example.news.Config;
import com.example.news.MainActivity;
import com.example.news.TypeFragmen;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by My Computer on 2017/7/28.
 */

public class TypeTask extends AsyncTask<String,Void,Void> {
    private JSONObject jsonObject;
    private JSONObject jsonObject1;
    private JSONArray jsonArray;
    private String name;
    private String id;

    public TypeTask() {
        super();
        }


    @Override
    protected Void doInBackground(String... params) {
        TypeFragmen.typeList.clear();
        try {
            jsonObject = new JSONObject(HttpRequest.HttpRequest(Config.request_type, null, null));
            jsonObject1 = jsonObject.getJSONObject(Config.ACTION_showapi_res_body);
            jsonArray = jsonObject1.getJSONArray(Config.ACTION_channelList);
            for (int i = 0; i < 14; i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                name = object.getString("name");
                id = object.getString("channelId");
                Type type = new Type();
                type.setName(name);
                type.setId(id);
                TypeFragmen.typeList.add(type);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        TypeFragmen.adapter.notifyDataSetChanged();
    }
}
