package com.jay.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by 李文杰 on 2018/11/26
 * 功能：
 */
public class Util {


    //向指定的地址发送get请求
    public static String get(String url){
        try {
            URL urlObj = new URL(url);
            URLConnection connection = urlObj.openConnection();
            InputStream inputStream = connection.getInputStream();
            byte[] b = new byte[1024];
            int len;
            StringBuffer sb = new StringBuffer();
            while ((len = inputStream.read(b)) != -1){
                sb.append(new String(b,0,len));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String post(String url, String data) {
        try {
            URL urlobj = new URL(url);
            URLConnection connection = urlobj.openConnection();
            connection.setDoOutput(true);
            OutputStream os = connection.getOutputStream();
            os.write(data.getBytes());
            os.close();
            InputStream is = connection.getInputStream();
            byte[] b = new byte[1024];
            int len;
            StringBuffer sb = new StringBuffer();
            while ((len = is.read(b)) != -1){
                sb.append(new String(b,0,len));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
