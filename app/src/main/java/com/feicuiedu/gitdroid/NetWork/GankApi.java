package com.feicuiedu.gitdroid.NetWork;

import com.feicuiedu.gitdroid.utils.GankResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by TJ on 2016/8/5.
 */
public interface GankApi {
    String ENDPOINT="http://gank.io/api/";
    @GET("day/{year}/{month}/{day}")
    Call<GankResult> getDailyDate(
            @Path("year")int year,
            @Path("month")int month,
            @Path("day")int day);
}
