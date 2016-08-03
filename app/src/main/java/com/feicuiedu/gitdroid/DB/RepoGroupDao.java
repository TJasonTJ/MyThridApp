package com.feicuiedu.gitdroid.DB;

import com.feicuiedu.gitdroid.utils.RepoGroup;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by TJ on 2016/8/3.
 */
public class RepoGroupDao {
    private Dao<RepoGroup,Long> dao;

    public RepoGroupDao(DBHelp dbHelp) {
        try {
            dao=dbHelp.getDao(RepoGroup.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void createOrUpdate(RepoGroup table){
        try {
            dao.createOrUpdate(table);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void createOrUpdate(List<RepoGroup> tables) {
        for (RepoGroup table : tables) {
            createOrUpdate(table);
        }
    }
    public RepoGroup queryForId(long id){
        try {
            return dao.queryForId(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<RepoGroup> queryForAll(){
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
