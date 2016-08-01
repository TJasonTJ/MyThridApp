package com.feicuiedu.gitdroid.Interface;

import android.view.View;

import com.feicuiedu.gitdroid.utils.Repo;

import java.util.List;

/**
 * Created by TJ on 2016/7/28.
 */
public interface RepoListPtrInterface {
    public void showContentView();
    public void showErrorView(String errorMsg);
    public void showEmptyView();
    public void showMessage(String msg);
    public void stopRefresh();
    public void refreshData(List<Repo> data);
}
