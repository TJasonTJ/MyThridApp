package com.feicuiedu.gitdroid.utils;

import android.support.annotation.NonNull;

import com.feicuiedu.gitdroid.NetWork.GankClient;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by TJ on 2016/8/5.
 */
public class GankPresenter {

    private Call<GankResult> call;
    private GankView gankView;

    public GankPresenter(@NonNull GankView gankView) {
        this.gankView = gankView;
    }
    public void getGanks(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int monty = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        call = GankClient.getInstance().getDailyDate(year, monty, day);
        call.enqueue(callback);
    }

    private Callback<GankResult> callback = new Callback<GankResult>() {
        @Override public void onResponse(Call<GankResult> call, Response<GankResult> response) {
            GankResult gankResult = response.body();
            if (gankResult == null) {
                gankView.showMessage("未知错误!");
                return;
            }
            if (gankResult.isError()
                    || gankResult.getResults() == null
                    || gankResult.getResults().getAndroidItems() == null
                    || gankResult.getResults().getAndroidItems().isEmpty()) {
                gankView.showEmptyView();
                return;
            }
            List<GankItem> gankItems = gankResult.getResults().getAndroidItems();
            gankView.hideEmptyView();
            gankView.setData(gankItems);
        }

        @Override public void onFailure(Call<GankResult> call, Throwable t) {
            gankView.showMessage("Error:" + t.getMessage());
        }
    };

    public interface GankView {
        void showEmptyView();
        void hideEmptyView();
        void showMessage(String msg);
        void setData(List<GankItem> gankItems);
    }
}