package com.feicuiedu.gitdroid.utils;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by TJ on 2016/7/31.
 */
public class User implements Serializable{
    private String login;
    private String name;
    private String id;
    @SerializedName("avatar_url")
    private String avatar;

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public String getAvatar() {
        return avatar;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
