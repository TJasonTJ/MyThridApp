package com.feicuiedu.gitdroid.Interface;

import com.feicuiedu.gitdroid.utils.Repo;

import java.util.List;

/**
 * Created by TJ on 2016/7/28.
 */
public interface RepoListLoadMoreView {
    void showLoadMoreLoading();

    void hideLoadMore();

    void showLoadMoreErro(String erroMsg);

    void addMoreData(List<Repo> datas);
}
