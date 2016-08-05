package com.feicuiedu.gitdroid.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.feicuiedu.gitdroid.Adapter.GankAdapter;
import com.feicuiedu.gitdroid.Base.BaseFragment;
import com.feicuiedu.gitdroid.R;
import com.feicuiedu.gitdroid.Tools.ActivityUtils;
import com.feicuiedu.gitdroid.utils.GankItem;
import com.feicuiedu.gitdroid.utils.GankPresenter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by TJ on 2016/8/5.
 */
public class GankFragment extends BaseFragment implements GankPresenter.GankView {

    @BindView(R.id.tvDate)
    TextView tvDate;
    @BindView(R.id.emptyView)
    FrameLayout emptyView;
    @BindView(R.id.btnFilter)
    ImageButton btnFilter;
    @BindView(R.id.content)
    ListView content;
    private GankAdapter gankAdapter;
    private ActivityUtils activityUtils;
    private Unbinder unbinder;

    private Date date;
    private SimpleDateFormat simpleDateFormat;
    private Calendar calendar;

    private GankPresenter gankPresenter;

    @Override
    public int setLayout() {
        return R.layout.fragment_gank;
    }

    @Override
    public void getview() {
        activityUtils = new ActivityUtils(this);
        calendar = Calendar.getInstance(Locale.CHINA);
        date = new Date(System.currentTimeMillis());
        gankPresenter = new GankPresenter(this);
    }

    @Override
    public void setview() {
        unbinder = ButterKnife.bind(this, view);
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        tvDate.setText(simpleDateFormat.format(date));
        gankAdapter = new GankAdapter();
        content.setAdapter(gankAdapter);
        content.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("msg", "点击监听！");
                GankItem gankItem = gankAdapter.getItem(position);
                activityUtils.startBrowser(gankItem.getUrl());
                Log.d("msg", "gankItem.getUrl()" + gankItem.getUrl());
            }
        });
        gankPresenter.getGanks(date);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showEmptyView() {
        emptyView.setVisibility(View.VISIBLE);
        content.setVisibility(View.GONE);
        YoYo.with(Techniques.FadeIn).duration(500).playOn(emptyView);
    }

    @Override
    public void hideEmptyView() {
        content.setVisibility(View.VISIBLE);
        emptyView.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(String msg) {
        activityUtils.showToast(msg);
    }

    @Override
    public void setData(List<GankItem> gankItems) {
        gankAdapter.setDate(gankItems);
        gankAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.btnFilter)
    public void showDateDialog() {
        int year = calendar.get(Calendar.YEAR);
        int monty = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(),
                dateSetListener,
                year, monty, day
        );
        datePickerDialog.show();
    }

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            calendar.set(year, monthOfYear, dayOfMonth);
            date = calendar.getTime();
            tvDate.setText(simpleDateFormat.format(date));
            gankPresenter.getGanks(date);
        }
    };

}
