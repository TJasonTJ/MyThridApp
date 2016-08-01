package com.feicuiedu.gitdroid.utils;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by TJ on 2016/8/1.
 */
public class Repo implements Serializable{
    private int id;
    private String name;
    @SerializedName("full_name")
    private String fullname;
    private String description;
    @SerializedName("stargazers_count")
    private int starCount;
    @SerializedName("forks_count")
    private int forkCount;

    private User owner;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFullname() {
        return fullname;
    }

    public String getDescription() {
        return description;
    }

    public int getStarCount() {
        return starCount;
    }

    public int getForkCount() {
        return forkCount;
    }

    public User getOwner() {
        return owner;
    }
    //    "id": 29028775,
//            "name": "react-native",
//            "full_name": "facebook/react-native",
//            "owner": {
//        "login": "facebook",
//                "id": 69631,
//                "avatar_url": "https://avatars.githubusercontent.com/u/69631?v=3",
//    },
//            "description": "A framework for building native apps with React.",
//            "stargazers_count": 33961,
//            "forks_count": 7122,
}
