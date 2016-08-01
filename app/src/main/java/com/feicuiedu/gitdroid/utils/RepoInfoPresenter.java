package com.feicuiedu.gitdroid.utils;

import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.util.Base64;

import com.feicuiedu.gitdroid.NetWork.GitHubApi;
import com.feicuiedu.gitdroid.NetWork.GitHubClient;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by TJ on 2016/8/1.
 */
public class RepoInfoPresenter {
    //视图接口
    public interface RepoInfoView{
        void showProgress();
        void hideProgress();
        void showMessage(String msg);
        void setDate(String htmlContent);
    }
    private RepoInfoView repoInfoView;
    private Call<RepoContentResult> repoContentResultCall;
    private Call<ResponseBody> mdhtmlCall;

    public RepoInfoPresenter(@NonNull RepoInfoView repoInfoView) {
        this.repoInfoView = repoInfoView;
    }
    public void getReadme(Repo repo){
        repoInfoView.showProgress();
        String login=repo.getOwner().getLogin();
        String name=repo.getName();
        if(repoContentResultCall!=null){
            repoContentResultCall.cancel();
        }
        repoContentResultCall= GitHubClient.getInstance().getReadme(login,name);
        repoContentResultCall.enqueue(repoContentCallback);
    }
    public Callback<RepoContentResult> repoContentCallback=new Callback<RepoContentResult>() {
        @Override
        public void onResponse(Call<RepoContentResult> call, Response<RepoContentResult> response) {
            String content=response.body().getContent();
            byte[] data= Base64.decode(content, Base64.DEFAULT);
            MediaType mediaType=MediaType.parse("text/plain");
            RequestBody body=RequestBody.create(mediaType,data);
            if(mdhtmlCall!=null){
                mdhtmlCall.cancel();
            }else{
                mdhtmlCall=GitHubClient.getInstance().markDown(body);
                mdhtmlCall.enqueue(mdhtmlCallback);
            }
        }

        @Override
        public void onFailure(Call<RepoContentResult> call, Throwable t) {
            repoInfoView.hideProgress();
            repoInfoView.showMessage(t.getMessage());
        }
    };
    private Callback<ResponseBody> mdhtmlCallback=new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            repoInfoView.hideProgress();
            try {
                String htmlContent=response.body().string();
                repoInfoView.setDate(htmlContent);
            } catch (IOException e) {
                onFailure(call,e);
            }
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            repoInfoView.hideProgress();
            repoInfoView.showMessage(t.getMessage());
        }
    };
}
