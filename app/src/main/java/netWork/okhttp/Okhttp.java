package netWork.okhttp;


import android.util.Log;

import java.io.IOException;
import java.util.logging.Handler;

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
    private Handler handler;
    //第一个参数为用户名，第二个参数为密码
    private   String basic = Credentials.basic(User.getUserName(), User.getUserPswd());
    private   String url = "http://"+User.getUserID()+"/*";

    private int Failure = 0;  //请求成功
    private int Response = 1; //请求失败
    //空构造函数
    public Okhttp(){

    }
    //构造函数
    public Okhttp(Handler handler){
        this.handler=handler;
    }
    /**
     * 获取OKHTTP的客户端
     * @return
     */
    public  OkHttpClient getOkClien(){
        OkHttpClient client = new OkHttpClient.Builder()
                .authenticator(new Authenticator() {
                    @Override
                    public Request authenticate(Route route, Response response) throws IOException {
                        return response.request().newBuilder().header("Authorization", basic).build();
                    }
                })
                .build();
        return client;
    }
    public  Request getOkrequest(){
        Log.i("url",url);
        return new Request.Builder().url(url).build();
    }
    public  void okHttpGet() {

        OkHttpClient client = getOkClien();
        Request request = getOkrequest();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("google.sang", "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    Log.i("google.sang", "onResponse: " + response.body().string());
                }
            }
        });
    }
}
