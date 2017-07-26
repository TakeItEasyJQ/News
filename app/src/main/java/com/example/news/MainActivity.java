package com.example.news;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.news.Class.Type;
import com.example.news.Utility.HttpRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public static DrawerLayout drawerlayout;
    private Button home;
    private TextView title;
    private JSONObject jsonObject;
    private JSONObject jsonObject1;
    private JSONArray jsonArray;
    private String name;
    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerlayout=(DrawerLayout)findViewById(R.id.drawerlayout);
        home=(Button)findViewById(R.id.main_home);
        title=(TextView)findViewById(R.id.main_title);
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
                if (TypeFragmen.typeList.size()<13||hasSamename()){
                    TypeFragmen.typeList.clear();
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
                                    id=object.getString("channelId");
                                    Type type=new Type();
                                    type.setName(name);
                                    type.setId(id);
                                    TypeFragmen.typeList.add(type);
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
                }
                drawerlayout.openDrawer(GravityCompat.START);
            }
        });
    }
    public  void showContent(){
        ContentFragment.adapter.notifyDataSetChanged();
        Toast.makeText(this, String.valueOf(ContentFragment.contentList.size()),Toast.LENGTH_SHORT).show();
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
