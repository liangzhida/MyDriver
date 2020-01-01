package com.example.driver.Http;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpUtils {
    static String url = "http://v.juhe.cn/jztk/query?subject=1&model=c1&key=f5fa410f03e84fc4f3d491fb3a324aaf&testType=order";
    static String url1 = "http://v.juhe.cn/jztk/query?subject=1&model=c1&key=f5fa410f03e84fc4f3d491fb3a324aaf&testType=rand";
    static OkHttpClient okHttpClient = new OkHttpClient();

    public static String get1() throws IOException {
        Request request = new Request.Builder().get().url(url).build();
        String string = okHttpClient.newCall(request).execute().body().string();
        return string;
    }
    public static String get2() throws IOException {
        Request request = new Request.Builder().get().url(url1).build();
        String string = okHttpClient.newCall(request).execute().body().string();
        return string;
    }
}
