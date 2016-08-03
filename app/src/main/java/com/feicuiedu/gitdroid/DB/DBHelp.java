package com.feicuiedu.gitdroid.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.feicuiedu.gitdroid.utils.RepoGroup;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by TJ on 2016/8/3.
 */
public class DBHelp extends OrmLiteSqliteOpenHelper{
    private static final String DB_NAME= "favorite.db";
    private static final int VERSION=1;

    private Context context;
    private static DBHelp dbHelp;
    public static synchronized DBHelp getInstance(Context context){
        if(dbHelp==null){
            dbHelp=new DBHelp(context.getApplicationContext());
        }
        return dbHelp;
    }
    public DBHelp(Context context) {
        super(context, DB_NAME, null, VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, RepoGroup.class);
            TableUtils.createTable(connectionSource, LocalRepo.class);
            new RepoGroupDao(this).createOrUpdate(RepoGroup.getDefaultGroups(context));
            new LocalRepoDao(this).createOrUpdate(LocalRepo.getDefaultLocalRepos(context));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, RepoGroup.class,true);
            TableUtils.dropTable(connectionSource,LocalRepo.class,true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
