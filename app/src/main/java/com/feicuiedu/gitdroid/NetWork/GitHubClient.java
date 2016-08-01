package com.feicuiedu.gitdroid.NetWork;

import com.feicuiedu.gitdroid.utils.AccessTokenResult;
import com.feicuiedu.gitdroid.utils.Language;
import com.feicuiedu.gitdroid.utils.RepoContentResult;
import com.feicuiedu.gitdroid.utils.RepoResult;
import com.feicuiedu.gitdroid.utils.User;

import java.sql.ResultSet;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.Path;
import retrofit2.http.Query;

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
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                // 添加token拦截器
                .addInterceptor(new TokenInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .client(okHttpClient)
                        // Gson转换器
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // 构建API
        gitHubApi = retrofit.create(GitHubApi.class);
    }


    @Override
    public Call<AccessTokenResult> getOAuthThoken(@Field("client_id") String client, @Field("client_secret") String clitenSecret, @Field("code") String code) {
        return gitHubApi.getOAuthThoken(client, clitenSecret, code);
    }
    @Override
    public Call<User> getUserInfo(){
        return gitHubApi.getUserInfo();
    }
    @Override
    public Call<RepoResult> searchRepos(@Query("q")String query,@Query("page")int pageId){
        return gitHubApi.searchRepos(query, pageId);
    }
    @Override
    public Call<RepoContentResult> getReadme(@Path("owner")String owner,@Path("repo")String repo){
        return gitHubApi.getReadme(owner, repo);
    }

    @Override
    public Call<ResponseBody> markDown(@Body RequestBody body) {
        return gitHubApi.markDown(body);
    }

}
