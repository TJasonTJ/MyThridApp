package com.feicuiedu.gitdroid.NetWork;

import com.feicuiedu.gitdroid.utils.AccessTokenResult;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;

/**
 * Created by TJ on 2016/7/28.
 */
public class GitHubClient implements GitHubApi{
    private GitHubApi gitHubApi;

    private static  GitHubClient gitHubClient;

    public static GitHubClient getInstance(){
        if(gitHubClient==null){
            gitHubClient=new GitHubClient();
        }
        return gitHubClient;
    }

    private GitHubClient() {
        OkHttpClient okHttpClient=new OkHttpClient.Builder().build();
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        gitHubApi=retrofit.create(GitHubApi.class);

    }

    public GitHubApi getGitHubApi() {
        return gitHubApi;
    }

    @Override
    public Call<AccessTokenResult> getOAuthThoken(@Field("client_id") String client, @Field("client_secret") String clitenSecret, @Field("code") String code) {
        return gitHubApi.getOAuthThoken(client,clitenSecret,code);
    }
}
