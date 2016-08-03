package com.feicuiedu.gitdroid.utils;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.google.gson.Gson;
/**
 * Created by TJ on 2016/8/3.
 */
@DatabaseTable(tableName = "repostiory_group")
public class RepoGroup {
    @DatabaseField(id = true)
    private int id;
    @DatabaseField(columnName = "name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    private static List<RepoGroup> defaultGroups;

    public static List<RepoGroup> getDefaultGroups(Context context){
        if(defaultGroups!=null){
            return defaultGroups;
        }
        try {
            InputStream inputStream=context.getAssets().open("repogroup.json");
            String content = IOUtils.toString(inputStream);
            Gson gson=new Gson();
            defaultGroups=gson.fromJson(content,new TypeToken<List<RepoGroup>>(){}.getType());
            return defaultGroups;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
