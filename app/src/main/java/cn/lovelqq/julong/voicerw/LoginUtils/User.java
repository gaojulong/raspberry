package cn.lovelqq.julong.voicerw.LoginUtils;

public class User {
    private static String userIP;
    public static String userName;
    public static String userPswd;



    public static void setUserIP(String userID) {
        User.userIP = userID;
    }

    public static void setUserName(String userName) {
        User.userName = userName;
    }

    public static void setUserPswd(String userPswd) {
        User.userPswd = userPswd;
    }

    public static String getUserIP() {
        return userIP;
    }

    public static String getUserName() {
        return userName;
    }

    public static String getUserPswd() {
        return userPswd;
    }
}
