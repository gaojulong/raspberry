package Voice;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SynthesizerListener;

public class SynthesisUtils {

    private static SynthesisUtils audioUtils;

    private SpeechSynthesizer mySynthesizer;

    public SynthesisUtils() {
    }


    public static SynthesisUtils getInstance() {
        if (audioUtils == null) {
            synchronized (SynthesisUtils.class) {
                if (audioUtils == null) {
                    audioUtils = new SynthesisUtils();
                }
            }
        }
        return audioUtils;
    }

    private InitListener myInitListener = new InitListener() {
        @Override
        public void onInit(int code) {
            Log.d("mySynthesiezer:", "InitListener init() code = " + code);
        }
    };


    public void init(Context context) {
        //处理语音合成关键类
        mySynthesizer = SpeechSynthesizer.createSynthesizer(context, myInitListener);
        //设置发音人
        mySynthesizer.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan");
        //设置音调
        mySynthesizer.setParameter(SpeechConstant.PITCH, "50");
        //设置音量
        mySynthesizer.setParameter(SpeechConstant.VOLUME, "50");
    }


    public void speakText(String content) {
        int code = mySynthesizer.startSpeaking(content, new SynthesizerListener() {
            @Override
            public void onSpeakBegin() {

            }

            @Override
            public void onBufferProgress(int i, int i1, int i2, String s) {

            }

            @Override
            public void onSpeakPaused() {

            }

            @Override
            public void onSpeakResumed() {

            }

            @Override
            public void onSpeakProgress(int i, int i1, int i2) {

            }

            @Override
            public void onCompleted(SpeechError speechError) {

            }

            @Override
            public void onEvent(int i, int i1, int i2, Bundle bundle) {

            }
        });
    }
}