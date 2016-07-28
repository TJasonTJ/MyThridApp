package com.feicuiedu.gitdroid.utils;

import android.os.AsyncTask;

import com.feicuiedu.gitdroid.Fragment.RepoListFragment;
import com.feicuiedu.gitdroid.Interface.RepoListInterface;

import java.util.ArrayList;

/**
 * Created by TJ on 2016/7/27.
 */
public class RepoListPresenter {
    private RepoListInterface repoListInterface;
    private int count;

    public RepoListPresenter(RepoListInterface repoListInterface) {
        this.repoListInterface = repoListInterface;
    }
    public void refresh(){
        new RefreshTask().execute();
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
            repoListInterface.stopRefresh();
            repoListInterface.refreshData(datas);
            repoListInterface.showContentView();
        }
    }
}
