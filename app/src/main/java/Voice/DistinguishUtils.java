package Voice;

import android.content.Context;
import android.util.Log;

import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

import netWork.Json.GetVoiceJson;
import netWork.GetCallbackDate;

public class DistinguishUtils {
    private static String responVoice;
    /**
     * 初始化语音识别
     */
    public static void initSpeech(Context context, final GetCallbackDate getCallbackDate) {

        //1.创建RecognizerDialog对象
        RecognizerDialog recognizerDialog = new RecognizerDialog(context, null);
        //2.设置accent、language等参数
        recognizerDialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");//语种，这里可以有zh_cn和en_us
        recognizerDialog.setParameter(SpeechConstant.ACCENT, "mandarin");//设置口音，这里设置的是汉语普通话 具体支持口音请查看讯飞文档，
        recognizerDialog.setParameter(SpeechConstant.TEXT_ENCODING, "utf-8");//设置编码类型
        recognizerDialog.show();
        //其他设置请参考文档http://www.xfyun.cn/doccenter/awd
        //3.设置讯飞识别语音后的回调监听
        recognizerDialog.setListener(new RecognizerDialogListener() {
            @Override
            public void onResult(RecognizerResult recognizerResult, boolean b) {//返回结果
                if (!b) {
                    Log.e("讯飞识别的结果", recognizerResult.getResultString());
                     responVoice= GetVoiceJson.parseJsonVoice(recognizerResult.getResultString());
                     getCallbackDate.getDate(responVoice);
//                    tv.setText(responVoice);
//                    String url= VoiceSelectUrl.selectUrl(responVoice);
//                    sendHttpGet(url);
                }
            }
            @Override
            public void onError(SpeechError speechError) {//返回错误
                Log.e("返回的错误码", speechError.getErrorCode() + "");
            }

        });
        //显示讯飞语音识别视图

    }

}
