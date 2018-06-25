package cn.lovelqq.julong.voicerw;

import android.app.Application;
import android.content.Context;

import com.iflytek.cloud.SpeechUtility;

import Voice.SynthesisUtils;

public class MyApplication extends Application {
    public static Context context;




    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
        SpeechUtility.createUtility(getApplicationContext(), "appid=5a2a1917");
        SynthesisUtils.getInstance().init(getApplicationContext());
    }

    /**
     *
     * @return
     */
    public static Context getContext(){

        return context;

    }
}
