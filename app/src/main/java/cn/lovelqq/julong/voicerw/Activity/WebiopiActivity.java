package cn.lovelqq.julong.voicerw.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import cn.lovelqq.julong.voicerw.LoginUtils.User;
import cn.lovelqq.julong.voicerw.R;
import cn.lovelqq.julong.voicerw.WebioUtile.GetButtonId;
import cn.lovelqq.julong.voicerw.WebioUtile.UpDateGPIOValus;
import cn.lovelqq.julong.voicerw.WebioUtile.UtilsGPIO;
import netWork.HttpCallbackListener;
import netWork.SendUrl;


public class WebiopiActivity extends Activity implements View.OnClickListener {
    private Button GPIOFunctionBut;
    private Button GPIOPinVlueBut;
    private UpDateGPIOValus getPinValus;
    private Handler handler;
    private static final String TAG = "WebiopiActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uiview);
        getPinValus = new UpDateGPIOValus();
        handler = new Handler();
        handler.post(new TimerWhiel());
    }

    /**
     * 全部按键的监听事件
     * @param view
     */
    @Override
    public void onClick(View view) {

        int id = view.getId();
        postGPIO(urlPsotControl(id));        //生成URL,发送更改状态请求
        uPLocalUi(id);                     //按下立刻再本地更新UI
    }

    /**
     * 实时更新UI
     *
     * @throws Exception
     */


    private void upDateAndUI() throws Exception {
        Map<String, String[]> pinValusMap = new HashMap<String, String[]>();
        getPinValus.getUpJson();   //刷新GPIO引脚数据。如果刷新返回true
        if (UpDateGPIOValus.isUpDate) {  //判断数据是否改变，如果改变刷信界面
            pinValusMap = UpDateGPIOValus.pinValusMap;    //获取map里存放的数据
            for (String key : pinValusMap.keySet()) {  //每个GPIO引脚状态
                String[] GPIOStatr = pinValusMap.get(key);
                int GPIOindex = Integer.valueOf(key);     //获取引脚的下标
                setGPIOUI(GPIOindex, GPIOStatr);  //更新UI数据
            }
        }
    }

    /**
     * 传入GPIO的按钮ID
     * 根据当前状态判断设置输入还是输出,和引脚的高低选择相对应的背景
     * @param GPIOindex
     */
    private void setGPIOUI(int GPIOindex, String GPIOstatr[]) {
        int GPIOFunctionId = GetButtonId.getGPIOFunctionId(GPIOindex); // 获取状态对应引脚的ID
        int GPIOVlusID = GetButtonId.getGPIOVluesId(GPIOindex);       //获取GPIO值对应的ID

        GPIOFunctionBut = findViewById(GPIOFunctionId);
        GPIOPinVlueBut = findViewById(GPIOVlusID);
        //把状态在按钮上显示
        if ("IN".equals(GPIOstatr[0])) {
            GPIOFunctionBut.setText("IN");
        }
        if ("OUT".equals(GPIOstatr[0])) {
            GPIOFunctionBut.setText("OUT");
        }
        //如果引脚值为0，则是黑背景；如果是1则是黄背景
        if (1 == Integer.parseInt(GPIOstatr[1])) {
            GPIOPinVlueBut.setBackground(getResources().getDrawable(R.drawable.button_edge_yellow));
        }
        if (0 == Integer.parseInt(GPIOstatr[1])) {
            GPIOPinVlueBut.setBackground(getResources().getDrawable(R.drawable.button_edge_black));
        }
    }



    /**
     * 网路请求延时500毫秒，会给人一种卡顿效果
     * 所以按钮按下后，在本地立刻更新UI
     * @param GPIOId
     */
    private void uPLocalUi(int GPIOId){
        //把状态在按钮上显示
        int index = GetButtonId.getGPIOIndex(GPIOId);
        String [] GPIOStatr = UpDateGPIOValus.getGPIOStatr(index);
        if (GetButtonId.isInGPIOFunctionID(GPIOId)){    //是否属于功能按键
            GPIOStatr[0] = UtilsGPIO.switchState(GPIOStatr[0]);
        }
        if (GetButtonId.isInGPIOVluesID(GPIOId)){       //是否属于引脚值按键
            //如果对应gpio的功能按钮为IN，则不能更新
            Button GPIOFunction = findViewById(GetButtonId.getGPIOFunctionId(index));//查找对应的功能按键
            if (!"IN".equals(GPIOFunction.getText())) {
                GPIOStatr[1] = UtilsGPIO.switchState(GPIOStatr[1]);
            }
        }
        setGPIOUI(index,GPIOStatr);
    }

    /**
     * 根据点击的按钮id来获取下标，生成改变状态是OUT还是IN的url
     * @param GPIOid
     * @return
     */
    private static String urlPsotControl(int GPIOid) {
        String url = "";
        String state = "";//需要切换的状态
        String ip = User.getUserIP(); // 连接地址
        int index = GetButtonId.getGPIOIndex(GPIOid);
        String[] strings = UpDateGPIOValus.getGPIOStatr(index);

        if (GetButtonId.isInGPIOFunctionID(GPIOid))  //判断按键属于状态还是引脚的值
        {
            state = UtilsGPIO.switchState(strings[0]); //切换OUT IN
            url = "http://" + ip + "/GPIO/" + index + "/function/" + state;
        }
        if (GetButtonId.isInGPIOVluesID(GPIOid)){
            state = UtilsGPIO.switchState(strings[1]); //切换1 0;
            url = "http://" + ip + "/GPIO/" + index + "/value/" + state;
        }

        return url;
    }

    /**
     * 切换GPIO的功能
     *
     * @param
     */
    private void postGPIO(String url) {
        SendUrl.senHttpPost(url, new HttpCallbackListener() {
            @Override
            public void OnSucceed(String response) {
                Log.i(TAG, "连接成功" + response);
            }

            @Override
            public void OnFailure(int code) {
                Log.e(TAG, "连接失败" + code);
                if (code == 403) {
                    toastUitl("GPIO已禁止");
                }
                if (code == 400) {
                    toastUitl("错误请求（路径不完整）");
                }
                if (code == 404) {
                    toastUitl("未找到资源");
                }
            }

            @Override
            public void OnError(Exception e) {
                Log.e(TAG, "连接错误");
            }
        });
    }

    /**
     * Toast提示用户
     * @param str
     */
    private void toastUitl(final String str){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(WebiopiActivity.this, str, Toast.LENGTH_LONG).show();
            }
        });

    }
    private class TimerWhiel implements Runnable {
        @Override
        public void run() {
            try {
                upDateAndUI();
            } catch (Exception e) {
                e.printStackTrace();
            }
            handler.postDelayed(this, 500);//每个1s进行一次刷新
        }
    }
}
