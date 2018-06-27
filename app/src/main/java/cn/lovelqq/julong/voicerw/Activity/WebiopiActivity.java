package cn.lovelqq.julong.voicerw.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import java.lang.ref.PhantomReference;
import java.util.HashMap;
import java.util.Map;

import cn.lovelqq.julong.voicerw.LoginUtils.User;
import cn.lovelqq.julong.voicerw.R;
import cn.lovelqq.julong.voicerw.RaspberryValus.GetButtonId;
import cn.lovelqq.julong.voicerw.RaspberryValus.GetPinValus;
import netWork.HttpCallbackListener;
import netWork.SendUrl;
import netWork.okhttp.Okhttp;


public class WebiopiActivity extends Activity implements View.OnClickListener {
    private Button GPIOFunctionBut;
    private Button GPIOPinVlueBut;
    private GetPinValus getPinValus;
    private Handler handler;
    private static final String TAG = "WebiopiActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uiview);
        getPinValus= new GetPinValus();
        handler = new Handler();
        handler.post(new TimerWhiel());
    }

    /**
     * 实时更新UI
     * @throws Exception
     */
    private void upDateAndUI() throws Exception{
        Map<String,String[]> pinValusMap = new HashMap<String, String[]>();
        getPinValus.getJson();   //刷新GPIO引脚数据

        pinValusMap = GetPinValus.pinValusMap;
        for (String key : pinValusMap.keySet()){  //每个GPIO引脚状态
            String [] GPIOS = pinValusMap.get(key);
            int  GPIOindex = Integer.valueOf(key);     //获取引脚的下标
            String GPIOfunction = GPIOS[0];            //gpio的状态
            int GPIOVlue =  Integer.valueOf(GPIOS[1]); //GPIO的值
            setGPIO(GPIOindex,GPIOfunction,GPIOVlue);
//            Log.i("GPIO","引脚下标:"+GPIOindex+", 状态"+ GPIOfunction+", 值"+GPIOVlue);
        }
    }

    /**
     * 传入GPIO的按钮ID
     * 根据当前状态判断设置输入还是输出,和引脚的高低选择相对应的背景
     * @param GPIOid
     */
    private void setGPIO(int GPIOid,String function,int vlue){
        int GPIOFunctionId =GetButtonId.getGPIOFunctionId(GPIOid); // 获取状态对应引脚的ID
        int GPIOVlusID = GetButtonId.getGPIOVluesId(GPIOid);       //获取GPIO值对应的ID

        GPIOFunctionBut = findViewById(GPIOFunctionId);
        GPIOPinVlueBut = findViewById(GPIOVlusID);
        //把状态在按钮上显示
        if ("IN".equals(function)){
            GPIOFunctionBut.setText("IN");
        }if ("OUT".equals(function)){
            GPIOFunctionBut.setText("OUT");
        }
        //如果引脚值为0，则是黑背景；如果是1则是黄背景
        if (1==vlue){
            GPIOPinVlueBut.setBackground(getResources().getDrawable(R.drawable.button_edge_yellow));
        }
        if (0==vlue){
            GPIOPinVlueBut.setBackground(getResources().getDrawable(R.drawable.button_edge_black));
        }
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (GetButtonId.isInGPIOFunctionID(id)) // 是否存在于状态引脚中
            {
                postGPIO(urlFunction(id));        //发送更改状态请求
            }
        if (GetButtonId.isInGPIOVluesID(id)) // 是否存在于状态引脚中
        {
            postGPIO(urlVlue(id));            //发送更改请求引脚值
        }

    }

    /**
     * 根据点击的按钮id来获取下标，生成改变状态是OUT还是IN的url
     * @param GPIOfunctionid
     * @return
     */
    private static String urlFunction(int GPIOfunctionid){
        String url ="";
        String function = "";//需要切换的状态
        String ip = User.getUserIP(); // 连接地址
        int index = GetButtonId.getGPIOFunctionIndex(GPIOfunctionid); // 获取引脚的下标

        String [] strings = GetPinValus.pinValusMap.get(""+index); //获取当前的状态
        if ("OUT".equals(strings[0])){          //如果是OUT则变为In
            function = "IN";
        }
        if ("IN".equals(strings[0])){
            function = "OUT";
        }
        url = "http://"+ip+"/GPIO/"+index+"/function/"+function;
        return url;
    }
    /**
     * 根据点击的按钮id来获取下标，生成改变引脚是1还是0的url
     * @param
     * @return
     */
    private static String urlVlue(int GPIOVluseid){
        String url ="";
        String vluse = "";//需要切换的状态
        String ip = User.getUserIP(); // 连接地址
        int index = GetButtonId.getGPIOVlueIndex(GPIOVluseid); // 获取引脚的下标
        Log.e("返回下标",""+index);

        String [] strings = GetPinValus.pinValusMap.get(""+index); //获取当前的状态
        if ("1".equals(strings[1])){          //如果是OUT则变为In
            vluse = "0";
        }
        if ("0".equals(strings[1])){
            vluse = "1";
        }
        url = "http://"+ip+"/GPIO/"+index+"/value/"+vluse;
        return url;
    }
    /**
     * 切换GPIO的功能
     * @param
     */
    private void postGPIO(String url){
        SendUrl.senHttpPost(url, new HttpCallbackListener() {
            @Override
            public void OnSucceed(String response) {
                Log.i(TAG,"连接成功"+response);
            }

            @Override
            public void OnFailure(int code) {
                Log.e(TAG,"连接失败"+code);
                if (code == 403){
                    Toast.makeText(WebiopiActivity.this,"",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void OnError(Exception e) {
                Log.e(TAG,"连接错误");
            }
        });
    }

    private class TimerWhiel implements Runnable{
        @Override
        public void run() {
            try {
                upDateAndUI();
            } catch (Exception e) {
                e.printStackTrace();
            }
            handler.postDelayed(this,100);//每个进行一次刷新
        }
    }
}
