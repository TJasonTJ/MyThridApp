package com.feicuiedu.gitdroid.DB;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by TJ on 2016/8/3.
 */
public class LocalRepoDao {
    private Dao<LocalRepo,Long> dao;

    public LocalRepoDao(DBHelp dbHelp) {
        try {
            dao=dbHelp.getDao(LocalRepo.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void createOrUpdate(LocalRepo localRepo){
        try {
            dao.createOrUpdate(localRepo);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void createOrUpdate(List<LocalRepo> localRepos){
        for (LocalRepo localRepo:localRepos
                ) {
            createOrUpdate(localRepo);
        }
    }
    public void delete(LocalRepo localRepo){
        try {
            dao.delete(localRepo);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<LocalRepo> queryForGroupId(int groupid){
        try {
            return dao.queryForEq(LocalRepo.COLUMN_GROUP_ID, groupid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<LocalRepo> queryForNoGroup(){
        try {
            return dao.queryBuilder().where().isNull(LocalRepo.COLUMN_GROUP_ID).query();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<LocalRepo> queryForAll(){
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            throw new  RuntimeException(e);
        }
    }
}
