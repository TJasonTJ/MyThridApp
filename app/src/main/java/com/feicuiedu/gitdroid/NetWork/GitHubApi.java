package com.feicuiedu.gitdroid.NetWork;

import com.feicuiedu.gitdroid.utils.AccessTokenResult;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;

import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by TJ on 2016/7/28.
 */
public interface GitHubApi {

    String CALL_BACK="http://www.yunjie@163.com";
    String CLIENT_ID="444caa6ae2f43b32825a";
    String CLIENT_SECRET="68e4c1ba9f3471d31ded10d6d2ad2ffdb9ef5519";
    String AUTE_SCOPE="user,public_repo,repo";
    String AUTE_RUL="https://github.com/login/oauth/authorize?client_id="+CLIENT_ID+"&scope="+CLIENT_SECRET;

    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("https://github.com/login/oauth/access_token")
    Call<AccessTokenResult> getOAuthThoken(
            @Field("client_id") String client,
            @Field("client_secret")String clitenSecret,
            @Field("code")String code);
//    @GET("repos/square/retrofit/contributors")
//    Call<ResponseBody> getRetrofitContributors();

}
