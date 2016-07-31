package com.feicuiedu.gitdroid.utils;

import android.util.Log;

import com.feicuiedu.gitdroid.Interface.LoginView;
import com.feicuiedu.gitdroid.NetWork.GitHubApi;
import com.feicuiedu.gitdroid.NetWork.GitHubClient;
import com.feicuiedu.gitdroid.Tools.LogUtils;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by TJ on 2016/7/29.
 */
public class LoginPresenter {
    private Call<AccessTokenResult> tokenResultCall;
    private Call<User> userCall;
    private LoginView loginView;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
    }

    public void login(String code){
        if(tokenResultCall!=null)
            tokenResultCall.cancel();
        tokenResultCall= GitHubClient.getInstance().getOAuthThoken(
                GitHubApi.CLIENT_ID
                , GitHubApi.CLIENT_SECRET
                , code);
        tokenResultCall.enqueue(tokenCallback);
    }
    private Callback<AccessTokenResult> tokenCallback=new Callback<AccessTokenResult>() {
        @Override
        public void onResponse(Call<AccessTokenResult> call, Response<AccessTokenResult> response) {
            AccessTokenResult result=response.body();
            Log.d("msg",result.toString());
            String token=result.getAccessToken();
            UserRepo.setAccessToken(token);
            if(userCall!=null)
                userCall.cancel();

            userCall=GitHubClient.getInstance().getUserInfo();
            userCall.enqueue(userCallback);
            LogUtils.d("token = " + token);
        }

        @Override
        public void onFailure(Call<AccessTokenResult> call, Throwable t) {
            loginView.showMessage(t.getMessage());
            loginView.showProgress();
            loginView.resetWeb();
//            LogUtils.d("tokenCallback onFailure");
        }
    };
    private Callback<User> userCallback=new Callback<User>(){

        @Override
        public void onResponse(Call<User> call, Response<User> response) {
            User user=response.body();
            UserRepo.setUser(user);
            loginView.showMessage("登录成功");
            loginView.navigateToMain();
            Log.d("msg","登录成功");
        }

        @Override
        public void onFailure(Call<User> call, Throwable t) {
            loginView.showMessage(t.getMessage());
            loginView.showProgress();
            loginView.resetWeb();
            Log.d("msg", "登录失败");
        }
    };

}
