package selecturl;

public class VoiceSelectUrl {
    private static  String url_gettemp="http://pi.lovelqq.cn/temp";
    private static  String url_onled="http://pi.lovelqq.cn/led/on";
    private static  String url_offled="http://pi.lovelqq.cn/led/off";
    public static String selectUrl(String voiceresponse){
    String url="";
    if ("开灯".equals(voiceresponse)){
        url=url_onled;
    }
    if ("关灯".equals(voiceresponse)){
        url=url_offled;
    }
    if ("查看温度".equals(voiceresponse)){
        url=url_gettemp;
    }
    return url;
    }
}
