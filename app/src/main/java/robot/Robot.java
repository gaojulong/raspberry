package robot;

import android.net.Uri;

public class Robot {
    private static final String ENDPOINT = "http://www.tuling123.com/openapi/api";
    private static String APPKEY="fc74371b992d40afb612785844a15229";
    private static final String KEY = "key";
    private static final String INFO = "info";
    private void getRobotResult(String request){




    }
    /**
     * 构造URL
     */
    public static String construcUrl(String content) {
        String url = Uri.parse(ENDPOINT).buildUpon()
                .appendQueryParameter(KEY, APPKEY)
                .appendQueryParameter(INFO, content).build().toString();
        return url;
    }
}
