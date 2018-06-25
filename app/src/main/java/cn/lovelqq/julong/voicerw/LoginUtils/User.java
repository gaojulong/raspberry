package cn.lovelqq.julong.voicerw.LoginUtils;

import android.util.Log;

public class User {
    private static String userID;
    public static String userName;
    public static String userPswd;



    public static void setUserID(String userID) {
        User.userID = userID;
    }

    public static void setUserName(String userName) {
        User.userName = userName;
    }

    public static void setUserPswd(String userPswd) {
        User.userPswd = userPswd;
    }

    public static String getUserID() {
        return userID;
    }

    public static String getUserName() {
        return userName;
    }

    public static String getUserPswd() {
        return userPswd;
    }
}
