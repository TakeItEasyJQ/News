package com.example.news;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.news.Class.Type;
import com.example.news.Utility.HttpRequest;
import com.example.news.Utility.MyTask;
import com.example.news.Utility.TypeTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public static DrawerLayout drawerlayout;
    public static SwipeRefreshLayout refresh;
    private Button home;
    public TextView title;
    private ConnectivityManager manager;
    private NetworkInfo info;
    public static ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialog=new ProgressDialog(MainActivity.this);
        drawerlayout=(DrawerLayout)findViewById(R.id.drawerlayout);
        home=(Button)findViewById(R.id.main_home);
        title=(TextView)findViewById(R.id.main_title);
        SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        TypeFragmen.channelid=pref.getString("channelId",null);
        TypeFragmen.name=pref.getString("name",null);
        if (TypeFragmen.channelid!=null&&TypeFragmen.name!=null){
            new MyTask(TypeFragmen.channelid,TypeFragmen.name).execute();
            title.setText(TypeFragmen.name);
        }else {
            drawerlayout.openDrawer(GravityCompat.START);
            new TypeTask().execute();
        }
        refresh=(SwipeRefreshLayout)findViewById(R.id.refresh_layout) ;

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh.setRefreshing(false);
                dialog.setMessage("最新新闻已在路上...");
                dialog.setCancelable(false);
                dialog.show();
                new MyTask(TypeFragmen.channelid,TypeFragmen.name).execute();
            }
        });
        hasSamename();
        TypeFragmen.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerlayout.closeDrawers();
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE) ;
                info=manager.getActiveNetworkInfo();
                if (info!=null&&info.isAvailable()){
                        TypeFragmen.typeList.clear();
                        new TypeTask().execute();
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                            try{
//                                jsonObject=new JSONObject(HttpRequest.HttpRequest(Config.request_type,null,null));
//                                jsonObject1=jsonObject.getJSONObject(Config.ACTION_showapi_res_body);
//                                jsonArray=jsonObject1.getJSONArray(Config.ACTION_channelList);
//                                for (int i=0;i<14;i++){
//                                    JSONObject object=jsonArray.getJSONObject(i);
//                                    name=object.getString("name");
//                                    id=object.getString("channelId");
//                                    Type type=new Type();
//                                    type.setName(name);
//                                    type.setId(id);
//                                    TypeFragmen.typeList.add(type);
//                                    runOnUiThread(new Runnable() {
//                                        @Override
//                                        public void run() {
//                                            TypeFragmen.adapter.notifyDataSetChanged();
//                                        }
//                                    });
//                                }
//                            }catch (JSONException e){
//                                e.printStackTrace();
//                            }
//                        }
//                    }).start();

                }else {
                    Toast.makeText(getApplicationContext(),"网络连接失败，请检查网络设置",Toast.LENGTH_SHORT).show();
                }

                drawerlayout.openDrawer(GravityCompat.START);
            }
        });
    }
    public static boolean hasSamename(){
        if (TypeFragmen.typeList!=null){
            for (int i=0;i<TypeFragmen.typeList.size();i++){
                for (int j=0;j<TypeFragmen.typeList.size()-1;j++){
                    if (!TypeFragmen.typeList.get(i).equals(TypeFragmen.typeList.get(j+1))){
                        j++;
                    }else {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
