package com.feicuiedu.gitdroid.utils;

import android.os.AsyncTask;
import android.util.Log;

import com.feicuiedu.gitdroid.Interface.RepoListView;
import com.feicuiedu.gitdroid.NetWork.GitHubApi;
import com.feicuiedu.gitdroid.NetWork.GitHubClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by TJ on 2016/7/27.
 */
public class RepoListPresenter {
    private RepoListView repoListView;
//    private int count;
    private int nextPage=0;
    private Language language;
    private Call<RepoResult> repoCall;
    public RepoListPresenter(RepoListView repoListView,Language language) {
        this.repoListView = repoListView;
        this.language=language;
    }


    public void refresh(){
        repoListView.hideLoadMore();
        repoListView.showContentView();
        nextPage=1;
        repoCall=GitHubClient.getInstance().searchRepos("language:"+language.getPath(),nextPage);
        repoCall.enqueue(repoCallback);
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
        repoCall=GitHubClient.getInstance().searchRepos("language:"+language.getPath(),nextPage);
//        new LoadMoreTask().execute();
        repoCall.enqueue(loadMoreCallback);
    }
    private final Callback<RepoResult> loadMoreCallback=new Callback<RepoResult>() {
        @Override
        public void onResponse(Call<RepoResult> call, Response<RepoResult> response) {
            repoListView.hideLoadMore();
            RepoResult repoResult=response.body();
            if(repoResult==null){
                repoListView.showLoadMoreErro("结果为空!");
                return;
            }
            List<Repo> repoList=repoResult.getRepoList();
            repoListView.addMoreData(repoList);
            nextPage++;
        }

        @Override
        public void onFailure(Call<RepoResult> call, Throwable t) {
            repoListView.hideLoadMore();
            repoListView.showMessage("repoCallback onFailure" + t.getMessage());
        }
    };
    private final Callback<RepoResult> repoCallback=new Callback<RepoResult>() {
        @Override
        public void onResponse(Call<RepoResult> call, Response<RepoResult> response) {
            repoListView.stopRefresh();
            RepoResult repoResult=response.body();
            if(repoResult==null){
                repoListView.showErrorView("结果为空！");
                return;
            }
            if(repoResult.getTotalCount()<=0){
                repoListView.refreshData(null);
                repoListView.showEmptyView();
                return;
            }
            List<Repo> repoList=repoResult.getRepoList();
            repoListView.refreshData(repoList);
            nextPage=2;
        }

        @Override
        public void onFailure(Call<RepoResult> call, Throwable t) {
            repoListView.stopRefresh();
            repoListView.showMessage("repoCallback onFailure" + t.getMessage());
        }
    };

//    class RefreshTask extends AsyncTask<Void,Void,Void>{
//
//        @Override
//        protected Void doInBackground(Void... params) {
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
//            ArrayList<String> datas=new ArrayList<String>();
//            for(int i=0;i<20;i++){
//                datas.add("测试数据"+(count++));
//            }
//            repoListView.stopRefresh();
//            repoListView.refreshData(datas);
//            repoListView.showContentView();
//        }
//    }
//    final class LoadMoreTask extends AsyncTask<Void,Void,Void>{
//        @Override
//        protected Void doInBackground(Void... params) {
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
//            ArrayList<String> datas=new ArrayList<String>();
//            for(int i=0;i<20;i++){
//                datas.add("测试数据"+(count++));
//            }
//            repoListView.addMoreData(datas);
//            repoListView.hideLoadMore();
//        }
//    }
}
