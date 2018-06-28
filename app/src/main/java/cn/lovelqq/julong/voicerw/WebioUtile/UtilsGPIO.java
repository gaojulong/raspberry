package cn.lovelqq.julong.voicerw.WebioUtile;

public class UtilsGPIO {
    /**
     * 切换状态
     */
    public static String switchState(String string){
        if ("OUT".equals(string)) {          //如果是OUT则变为In
            string = "IN";
            return string;
        }
        if ("IN".equals(string)) {
            string = "OUT";
            return string;
        }
        if ("0".equals(string)) {          //如果是0则变为1
            string = "1";
            return string;
        }
        if ("1".equals(string)) {
            string = "0";
            return string;
        }
        return string;
    }

}
