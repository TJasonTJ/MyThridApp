package com.feicuiedu.gitdroid.NetWork;

import com.feicuiedu.gitdroid.Tools.LogInterceptor;
import com.feicuiedu.gitdroid.utils.GankResult;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Path;

/**
 * Created by TJ on 2016/8/5.
 */
public class GankClient implements GankApi{
    private static GankClient gankClient;
    public static GankClient getInstance(){
        if(gankClient==null){
            gankClient=new GankClient();
        }
        return gankClient;
    }
    private final GankApi gankApi;

    public GankClient() {
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .addInterceptor(new LogInterceptor())
                .build();
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        gankApi=retrofit.create(GankApi.class);
    }

    @Override
    public Call<GankResult> getDailyDate(@Path("year") int year, @Path("month") int month, @Path("day") int day) {

        return gankApi.getDailyDate(year,month,day);
    }
}
