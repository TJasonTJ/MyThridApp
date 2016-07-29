package com.feicuiedu.gitdroid.utils;

import android.util.Log;

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
    public void login(String code){
        if(tokenResultCall!=null){
            tokenResultCall.cancel();
        }
        tokenResultCall= GitHubClient.getInstance().getOAuthThoken(GitHubApi.CLIENT_ID
        ,GitHubApi.CLIENT_SECRET,code);
        tokenResultCall.enqueue(tokenCallback);
    }
    private Callback<AccessTokenResult> tokenCallback=new Callback<AccessTokenResult>() {
        @Override
        public void onResponse(Call<AccessTokenResult> call, Response<AccessTokenResult> response) {
            AccessTokenResult result=response.body();
            String token=result.getAccessToken();
            LogUtils.d("token = " + token);
        }

        @Override
        public void onFailure(Call<AccessTokenResult> call, Throwable t) {
            LogUtils.d("tokenCallback onFailure");
        }
    };
}
