package com.feicuiedu.gitdroid.DB;

import android.content.Context;

import com.feicuiedu.gitdroid.utils.RepoGroup;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by TJ on 2016/8/3.
 */
@DatabaseTable(tableName = "local_repo")
public class LocalRepo {
    public static final String COLUMN_GROUP_ID = "group_id";
    @DatabaseField(id = true)
    private long id;
    @DatabaseField
    private String name;
    @DatabaseField(columnName = "full_name")
    private String full_name;
    @DatabaseField()
    private String description;
    @DatabaseField(columnName = "start_count")
    private int start_count;
    @DatabaseField(columnName ="fork_count")
    private int fork_count;
    @DatabaseField(columnName = "avatar_url")
    private String avatar_url;

    @DatabaseField(columnName = COLUMN_GROUP_ID,foreign = true,canBeNull = true)
    @SerializedName("group")
    private RepoGroup repoGroup;

    public static String getColumnGroupId() {
        return COLUMN_GROUP_ID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStart_count() {
        return start_count;
    }

    public void setStart_count(int start_count) {
        this.start_count = start_count;
    }

    public int getFork_count() {
        return fork_count;
    }

    public void setFork_count(int fork_count) {
        this.fork_count = fork_count;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public RepoGroup getRepoGroup() {
        return repoGroup;
    }

    public void setRepoGroup(RepoGroup repoGroup) {
        this.repoGroup = repoGroup;
    }
    public static List<LocalRepo> getDefaultLocalRepos(Context context){
        try {
            InputStream inputStream=context.getAssets().open("defaultrepos.json");
            String content= IOUtils.toString(inputStream);
            Gson gson=new Gson();
            return gson.fromJson(content,new TypeToken<List<LocalRepo>>(){}.getType());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
