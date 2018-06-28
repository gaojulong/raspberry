package cn.lovelqq.julong.voicerw.WebioUtile;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import netWork.HttpCallbackListener;
import netWork.SendUrl;
import netWork.okhttp.Okhttp;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UpDateGPIOValus {
    private Okhttp okhttp = new Okhttp();
    private OkHttpClient okHttpClient;
    private Request request;
    private Callba callba = new Callba();
    private static final String url = "http://192.168.123.218:8000/*";
    public static boolean isUpDate = false;  //判断获取的json数据是否改变
    private static String jsonResult = "";//获取的Json数据
    /**
     * 初始化Map，存放树莓派的引脚状态
     * 注意引脚下标从2开始，一共24个GPIO
     */
    private static String[] strs = {"IN", "0"};
    public static Map<String, String[]> pinValusMap = new HashMap<String, String[]>() {{
        for (int pin = 2; pin < 28; pin++) {
            if (pin == 14 || pin == 15 || pin == 16) {
                continue;
            }
            put("" + pin, strs);

        }
    }};

    /**
     * 刷新数据
     */
    public static void getUpJson() {

        SendUrl.senHttpget(url, new HttpCallbackListener() {
            @Override
            public void OnSucceed(String response) { //获取成功
                if (!jsonResult.equals(response)) {
                    jsonResult = response;
                    isUpDate = true;
                    parseJsonPin(jsonResult);         //解析结果
                } else {
                    isUpDate = false;
                }
            }
            @Override
            public void OnFailure(int code) {
                //提示用户获取失败
            }

            @Override
            public void OnError(Exception e) {
                //提示用户获取失败
            }
        });

    }

    /**
     * 根据传入引脚的ID来返回当前状态
     * 下标  -0 代表当前状态   -1代表当前值
     * @param
     * @return
     */
    public static String[] getGPIOStatr(int index){
        String[] strings = pinValusMap.get(String.valueOf(index));

        return strings;
    }

    /**
     * 禁止使用
     * 使用此方法会产生溢出
     *
     * @return
     */

    public Map getjsonPin() {
        okHttpClient = okhttp.getOkClien();
        request = okhttp.getOkrequest();
        okHttpClient.newCall(request).enqueue(callba);
        return pinValusMap;
    }

    /**
     * 解析返回的json数据
     *
     * @param json
     */
    private static void parseJsonPin(String json) {
        JSONObject jsonObject = JSON.parseObject(json);
        JSONObject jsonobjectGPIO = jsonObject.getJSONObject("GPIO");
        for (int i = 2; i < 28; i++) {
            if (i == 14 || i == 15) {
                continue;
            }
            JSONObject jsonObjectBin = jsonobjectGPIO.getJSONObject("" + i);
            String function = jsonObjectBin.getString("function");
            String value = jsonObjectBin.getString("value");

            String[] strings = new String[2];
            strings[0] = function; // 存放状态
            strings[1] = value;    //存放值
            pinValusMap.put("" + i, strings); //存放到map里
        }
    }

    private class Callba implements Callback {

        @Override
        public void onFailure(Call call, IOException e) {
            //发送系统消息，提示过去失败
            call.clone();
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            //获取引脚状态，进行解析
            parseJsonPin(response.body().string());

        }
    }


}
