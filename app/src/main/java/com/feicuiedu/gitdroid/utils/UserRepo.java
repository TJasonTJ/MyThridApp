package com.feicuiedu.gitdroid.utils;

/**
 * Created by TJ on 2016/7/31.
 */
public class UserRepo {
    private UserRepo(){}
    private static User user;
    private static String accessToken;
    public static boolean hasAcessToken(){
        return accessToken !=null;
    }
    public static boolean isEmpty(){
        return accessToken==null||user==null;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        UserRepo.user = user;
    }

    public static String getAccessToken() {
        return accessToken;
    }

    public static void setAccessToken(String accessToken) {
        UserRepo.accessToken = accessToken;
    }

    public static void clear(){
        accessToken=null;
        user=null;
    }
}
