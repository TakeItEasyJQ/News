package com.example.news;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.news.Utility.HttpRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private DrawerLayout drawerlayout;
    private Button home;
    private TextView title;
    private JSONObject jsonObject;
    private JSONObject jsonObject1;
    private JSONArray jsonArray;
    private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerlayout=(DrawerLayout)findViewById(R.id.drawerlayout);
        home=(Button)findViewById(R.id.main_home);
        title=(TextView)findViewById(R.id.main_title);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TypeFragmen.typenames.clear();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            jsonObject=new JSONObject(HttpRequest.HttpRequest(Config.request_type,null,null));
                            jsonObject1=jsonObject.getJSONObject(Config.ACTION_showapi_res_body);
                            jsonArray=jsonObject1.getJSONArray(Config.ACTION_channelList);
                            for (int i=0;i<14;i++){
                                JSONObject object=jsonArray.getJSONObject(i);
                                name=object.getString("name");
                                Log.d(TAG, "run: "+name+TypeFragmen.typenames.size());
                                TypeFragmen.typenames.add(name);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        TypeFragmen.adapter.notifyDataSetChanged();
                                    }
                                });
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }).start();
                drawerlayout.openDrawer(GravityCompat.START);
            }
        });
    }
}
