package cn.lovelqq.julong.voicerw.Activity;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import Voice.DistinguishUtils;
import Voice.SynthesisUtils;
import cn.lovelqq.julong.voicerw.MyApplication;
import cn.lovelqq.julong.voicerw.R;
import netWork.GetCallbackDate;
import netWork.HttpCallbackListener;
import netWork.Json.GetVoiceJson;
import netWork.NetworkUtils;
import netWork.SendUrl;
import netWork.okhttp.Okhttp;
import robot.Robot;
import selecturl.VoiceSelectUrl;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static String TAG="MainActivity";
    private TextView tv;
    private Button btn,bt_test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        btn = findViewById(R.id.btn);
        bt_test=findViewById(R.id.bt_Test);

        bt_test.setOnClickListener(this);
        btn.setOnClickListener(this);
    }

    /**
     * boolean是否是聊天URL
     * 发送get请求
     */
    private  void sendHttpGet(String url, final boolean islocal){
        SendUrl.senHttpget(url, new HttpCallbackListener() {
            @Override
            public void OnSucceed(String response) {
              //处理聊天机器人的Json数据
                if (islocal){
                    response= GetVoiceJson.parseJsonRobot(response);
                }
                //播放返回结果
                SynthesisUtils.getInstance().speakText(response);
                //显示返回结果
                showResponse(response);
            }

            @Override
            public void OnFailure(int code) {

            }

            @Override
            public void OnError(Exception e) {
            }
        });
    }
    /**
     *显示文字
     * @param response
     */
    private void showResponse(final String response){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv.setText(response);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_Test:

//                sendHttpGet("http://webiopi.lovelqq.cn:13141/",true);
//                Okhttp.okHttpGet();
                break;
            case R.id.btn:
                if (NetworkUtils.isNetworkAvailable(MyApplication.getContext())) {
                    DistinguishUtils.initSpeech(this, new GetCallbackDate() {
                        @Override
                        public void getDate(String date) {
                            String voiceResult = date;//返回的识别结果
                            showResponse(voiceResult);//显示识别结果
                            String url = VoiceSelectUrl.selectUrl(voiceResult);//查找匹配的URL
                            if ("".equals(url)){
                                //发送给机器人
                                 Log.e(TAG,Robot.construcUrl(voiceResult));
                                 sendHttpGet(Robot.construcUrl(voiceResult),true);
                            }else {
                                sendHttpGet(url,false);//发送Get请求
                            }
                        }
                    });
                }else {
                    Toast.makeText(this,"请检查网络",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
