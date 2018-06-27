package cn.lovelqq.julong.voicerw.RaspberryValus;


import android.accounts.NetworkErrorException;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.apache.commons.codec.binary.Base64;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import cn.lovelqq.julong.voicerw.LoginUtils.User;
import netWork.SendUrl;
import netWork.okhttp.Okhttp;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetPinValus {
    private Okhttp okhttp = new Okhttp();
    private OkHttpClient okHttpClient ;
    private Request request;
    private Callba callba=new Callba();
    private static final String url ="http://192.168.123.218:8000/*";
    private static  String authString = User.getUserName()+":"+User.getUserPswd();
    /**
     * 初始化Map，存放树莓派的引脚状态
     * 注意引脚下标从2开始，一共24个GPIO
     */
    private static String[] strs = {"IN","0"};
    public  static Map<String,String[]> pinValusMap = new HashMap<String, String[]>(){{
        for (int pin = 2; pin<28; pin++){
            if (pin == 14 || pin==15 || pin==16){
                continue;
            }
            put(""+pin,strs);

        }
    }};

    public static Map getJson(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn = null;
                try {
                    // 利用string url构建URL对象
                    URL mURL = new URL(url);
                    conn = (HttpURLConnection) mURL.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setReadTimeout(5000);
                    conn.setConnectTimeout(10000);

//                    authString = "webiopi"+":"+"raspberry";
                    byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
                    String authStringEnc = new String(authEncBytes);
                    conn.setRequestProperty("Authorization", "Basic " + authStringEnc);
                    conn.setRequestProperty("User-Agent", "MSIE 7.0");

                    int responseCode = conn.getResponseCode();
                    Log.v("GET","GET"+responseCode);
                    if (responseCode == 200) {

                        InputStream is = conn.getInputStream();
                        String response = SendUrl.getStringFromInputStream(is);
                        parseJsonPin(response);
//                        Log.e("获取成功",""+response);
                    } else {
                        Log.e("请求失败","请求失败");
                        throw new NetworkErrorException("response status is "+responseCode);
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                    Log.e("程序异常","程序异常");
                }
                finally {
                    if (conn != null) {
                        conn.disconnect();
//                        Log.e("TAG","执行结束关闭连接");
                    }
                }

            }
        }).start();
        return pinValusMap;
    }

    /**
     * 禁止使用
     * 使用此方法会产生溢出
     * @return
     */

    public Map getjsonPin(){
        okHttpClient = okhttp.getOkClien();
        request = okhttp.getOkrequest();
        okHttpClient.newCall(request).enqueue(callba);
        return pinValusMap;
    }

    /**
     * 解析返回的json数据
     * @param json
     */
    private static void parseJsonPin(String json){
        JSONObject  jsonObject = JSON.parseObject(json);
        JSONObject  jsonobjectGPIO = jsonObject.getJSONObject("GPIO");
        for (int i=2;i<28;i++){
            if (i==14 ||i==15 ){
                continue;
            }
            JSONObject  jsonObjectBin = jsonobjectGPIO.getJSONObject(""+i);
            String function = jsonObjectBin.getString("function");
            String value = jsonObjectBin.getString("value");

            String [] strings = new String[2];
            strings[0] = function; // 存放状态
            strings[1] = value;    //存放值
            pinValusMap.put(""+i,strings); //存放到map里
        }
    }
    private class Callba implements Callback{

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
