package com.feicuiedu.gitdroid.utils;

import android.os.AsyncTask;

import com.feicuiedu.gitdroid.Interface.RepoListPtrInterface;
import com.feicuiedu.gitdroid.Interface.RepoListView;

import java.util.ArrayList;

/**
 * Created by TJ on 2016/7/27.
 */
public class RepoListPresenter {
    private RepoListView repoListView;
    private int count;

    public RepoListPresenter(RepoListView repoListView) {
        this.repoListView = repoListView;
    }
    public void refresh(){
        new RefreshTask().execute();
    }

    public void loadMore(){
        repoListView.showLoadMoreLoading();
        new LoadMoreTask().execute();
    }
    class RefreshTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            ArrayList<String> datas=new ArrayList<String>();
            for(int i=0;i<20;i++){
                datas.add("测试数据"+(count++));
            }
            repoListView.stopRefresh();
            repoListView.refreshData(datas);
            repoListView.showContentView();
        }
    }
    final class LoadMoreTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            ArrayList<String> datas=new ArrayList<String>();
            for(int i=0;i<20;i++){
                datas.add("测试数据"+(count++));
            }
            repoListView.addMoreData(datas);
            repoListView.hideLoadMore();
        }
    }
}
