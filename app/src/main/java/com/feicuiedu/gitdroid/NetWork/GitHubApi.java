package com.feicuiedu.gitdroid.NetWork;

import com.feicuiedu.gitdroid.utils.AccessTokenResult;
import com.feicuiedu.gitdroid.utils.RepoContentResult;
import com.feicuiedu.gitdroid.utils.RepoResult;
import com.feicuiedu.gitdroid.utils.User;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;

import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by TJ on 2016/7/28.
 */
public interface GitHubApi {

    String CALL_BACK="fc";
    String CLIENT_ID="444caa6ae2f43b32825a";
    String CLIENT_SECRET="68e4c1ba9f3471d31ded10d6d2ad2ffdb9ef5519";

    String AUTH_SCOPE="user,public_repo,repo";
    String AUTH_RUL="https://github.com/login/oauth/authorize?client_id="+CLIENT_ID+"&scope="+AUTH_SCOPE;

    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("https://github.com/login/oauth/access_token")
    Call<AccessTokenResult> getOAuthThoken(
            @Field("client_id") String client,
            @Field("client_secret")String clientSecret,
            @Field("code")String code);

    @GET("user")
    Call<User> getUserInfo();

    @GET("search/repositories")
    Call<RepoResult> searchRepos(@Query("q")String query,@Query("page") int pageId);
    @GET("/repos/{owner}/{repo}/readme")
    Call<RepoContentResult> getReadme(
            @Path("owner")String owner,
            @Path("repo")String repo
    );
    /***
     * 获取一个markdonw内容对应的HTML页面
     *
     * @param body 请求体,内容来自getReadme后的RepoContentResult
     */
    @Headers({
            "Content-Type:text/plain"
    })
    @POST("/markdown/raw")
    Call<ResponseBody> markDown(@Body RequestBody body);


}