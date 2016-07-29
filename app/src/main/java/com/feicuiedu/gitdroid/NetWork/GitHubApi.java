package com.feicuiedu.gitdroid.NetWork;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by TJ on 2016/7/28.
 */
public interface GitHubApi {
    @GET("repos/square/retrofit/contributors")
    Call<ResponseBody> getRetrofitContributors();
}
