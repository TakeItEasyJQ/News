package com.example.news.Utility;

import com.example.news.Config;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by My Computer on 2017/7/24.
 */

public class HttpRequest {

    public static String HttpRequest(String addresstype,String channelID,String channelName) {

        StringBuffer sb=new StringBuffer();
        try {
            URL url=new URL(addresstype+Config.ACTION_channelId+"="+channelID+Config.ACTION_channelName+"="+channelName);
            HttpURLConnection connection=(HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            InputStream in=connection.getInputStream();
            BufferedReader bf=new BufferedReader(new InputStreamReader(in));
            String s="";
            while ((s=bf.readLine())!=null){
                sb.append(s);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return sb.toString();
    }
}
