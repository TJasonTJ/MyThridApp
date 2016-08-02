package com.feicuiedu.gitdroid.utils;

import com.feicuiedu.gitdroid.Interface.UserListView;
import com.feicuiedu.gitdroid.NetWork.GitHubClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by TJ on 2016/8/2.
 */
public class HotUserPresenter {
    private UserListView userListView;
    private Call<UsersResult> usersCall;
    private int nextPage=0;

    public HotUserPresenter(UserListView userListView) {
        this.userListView = userListView;
    }
    public void refresh(){
        userListView.hideLoadMore();
        userListView.showContentView();
        nextPage=1;
        if(usersCall!=null){
            usersCall.cancel();
        }
        usersCall= GitHubClient.getInstance().searchUsers("followers:"+">1000",nextPage);
        usersCall.enqueue(ptrCallback);
    }
    public Callback<UsersResult> ptrCallback=new Callback<UsersResult>() {
        @Override
        public void onResponse(Call<UsersResult> call, Response<UsersResult> response) {
            userListView.stopRefresh();
            UsersResult usersResult=response.body();
            if(usersResult==null){
                userListView.showErrorView("结果为空！");
                return;
            }
            List<User> userList=usersResult.getItems();
            userListView.refreshData(userList);
            nextPage=2;
        }

        @Override
        public void onFailure(Call<UsersResult> call, Throwable t) {
            userListView.stopRefresh();
            userListView.showMessage("ptrCallback onFailure"+t.getMessage());
        }
    };
    public void loadMore(){
        userListView.showLoadMoreLoading();
        if(usersCall!=null){
            usersCall.cancel();
        }
        usersCall=GitHubClient.getInstance().searchUsers("followers:"+">1000",nextPage);
        usersCall.enqueue(loadmoreCallback);
    }
    public Callback<UsersResult> loadmoreCallback=new Callback<UsersResult>() {
        @Override
        public void onResponse(Call<UsersResult> call, Response<UsersResult> response) {
            userListView.hideLoadMore();
            UsersResult usersResult=response.body();
            if(usersResult==null){
                userListView.showErrorView("结果为空！");
                return;
            }
            List<User> userList=usersResult.getItems();
            nextPage++;
        }

        @Override
        public void onFailure(Call<UsersResult> call, Throwable t) {
            userListView.hideLoadMore();
            userListView.showMessage("loadmoreCallback onFailure" + t.getMessage());
        }
    };
}
