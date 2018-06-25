package netWork;

import android.accounts.NetworkErrorException;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import cn.lovelqq.julong.voicerw.MyApplication;

/**
 * Created by julong on 2018/4/2.
 */

public class SendUrl {

    public static void senHttpget(final String url,final HttpCallbackListener httpCallbackListener) {
        if (!NetworkUtils.isNetworkAvailable(MyApplication.getContext())){
            Toast.makeText(MyApplication.getContext(),"请检查网络",Toast.LENGTH_LONG).show();
        }
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

                    int responseCode = conn.getResponseCode();
                    Log.e("GET","GET"+responseCode);
                    if (responseCode == 200) {

                        InputStream is = conn.getInputStream();
                        String response = getStringFromInputStream(is);
                        Log.e("获取成功",""+response);
                        httpCallbackListener.OnSucceed(response);
                    } else {
                        Log.e("请求失败","请求失败");
                        httpCallbackListener.OnSucceed("请求失败,错误代码"+responseCode);
                        throw new NetworkErrorException("response status is "+responseCode);
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                    httpCallbackListener.OnError(e);
                    Log.e("程序异常","程序异常");
                }
                finally {
                    if (conn != null) {
                        conn.disconnect();
                        Log.e("TAG","执行结束关闭连接");
                    }
                }
            }
        }).start();

    }
    public static void senHttpPost(final String url,final HttpCallbackListener httpCallbackListener) {
        if (!NetworkUtils.isNetworkAvailable(MyApplication.getContext())){
            Toast.makeText(MyApplication.getContext(),"请检查网络",Toast.LENGTH_LONG).show();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn = null;
                try {
                    // 利用string url构建URL对象
                    URL mURL = new URL(url);
                    conn = (HttpURLConnection) mURL.openConnection();

                    conn.setRequestMethod("POST");
                    conn.setReadTimeout(5000);
                    conn.setConnectTimeout(10000);

                    int responseCode = conn.getResponseCode();
                    Log.e("GET","GET"+responseCode);
                    if (responseCode == 200) {

                        InputStream is = conn.getInputStream();
                        String response = getStringFromInputStream(is);
                        Log.e("获取成功",""+response);
                        httpCallbackListener.OnSucceed(response);
                    } else {
                        Log.e("请求失败","请求失败");
                        httpCallbackListener.OnSucceed("请求失败");
                        throw new NetworkErrorException("response status is "+responseCode);
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                    httpCallbackListener.OnError(e);
                    Log.e("程序异常","程序异常");
                }
                finally {
                    if (conn != null) {
                        conn.disconnect();
                        Log.e("TAG","执行结束关闭连接");
                    }
                }
            }
        }).start();

    }


    private static String getStringFromInputStream(InputStream is)
            throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        // 模板代码 必须熟练
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = is.read(buffer)) != -1) {
            os.write(buffer, 0, len);
        }
        is.close();
        String state = os.toString();// 把流中的数据转换成字符串,采用的编码是utf-8(模拟器默认编码)
        os.close();
        return state;
    }

}
