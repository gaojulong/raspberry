package netWork.okhttp;


import android.util.Log;

import java.io.IOException;

import cn.lovelqq.julong.voicerw.LoginUtils.User;
import okhttp3.Authenticator;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

public class Okhttp {
    //第一个参数为用户名，第二个参数为密码
//    private static final String basic = Credentials.basic("webiopi", "raspberry");
    private static final String basic = Credentials.basic(User.getUserName(), User.getUserPswd());
    private static final String url = "http://"+User.getUserID()+"/*";

    public static void okHttpGet() {
        OkHttpClient client = new OkHttpClient.Builder()
                .authenticator(new Authenticator() {
                    @Override
                    public Request authenticate(Route route, Response response) throws IOException {
                        return response.request().newBuilder().header("Authorization", basic).build();
                    }
                })
                .build();
        Request request = new Request.Builder().url(url).build();
        
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("google.sang", "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    Log.e("google.sang", "onResponse: " + response.body().string());
                }
            }
        });
    }
}
