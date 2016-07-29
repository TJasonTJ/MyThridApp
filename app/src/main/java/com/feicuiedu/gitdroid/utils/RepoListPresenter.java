package com.feicuiedu.gitdroid.utils;

import android.os.AsyncTask;
import android.util.Log;

import com.feicuiedu.gitdroid.Interface.RepoListView;
import com.feicuiedu.gitdroid.NetWork.GitHubApi;
import com.feicuiedu.gitdroid.NetWork.GitHubClient;

import java.io.IOException;
import java.util.ArrayList;



import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
//        new RefreshTask().execute();
//        GitHubClient gitHubClient=new GitHubClient();
//        GitHubApi gitHubApi=gitHubClient.getGitHubApi();
//        Call<ResponseBody> call=gitHubApi.getRetrofitContributors();
//        call.enqueue(refreshCallback);
    }

    private final Callback<ResponseBody> refreshCallback =new Callback<ResponseBody>() {
    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            repoListView.stopRefresh();
        if(response.isSuccessful()){
            try {
                ResponseBody body=response.body();
                if(body==null){
                    repoListView.showMessage("未知错误！");
                    return;
                }
                String content =body.string();
                Log.i("msg",content);
                repoListView.showContentView();
            } catch (IOException e) {
//                e.printStackTrace();
                onFailure(call,e);
            }
        }else{
            repoListView.showMessage("code:"+response.code());
        }
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        repoListView.stopRefresh();
        repoListView.showMessage(t.getMessage());
        repoListView.showContentView();
    }
};
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
