package com.feicuiedu.gitdroid.FrameLayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.feicuiedu.gitdroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by TJ on 2016/7/28.
 */
public class FooterView extends FrameLayout {
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.tv_complete)
    TextView tvComplete;
    @BindView(R.id.tv_error)
    TextView tvError;

    private static final int STATE_LOADING=0;
    private static final int STATE_COMPLETE=1;
    private static final int STATE_ERROE=2;

    private int state=STATE_LOADING;
    public FooterView(Context context) {
        this(context, null);
    }

    public FooterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.content_load_footer, this, true);
        ButterKnife.bind(this);
    }

    public boolean isLoading() {
        return state==STATE_LOADING;
    }
    public boolean isComplete(){
        return state==STATE_COMPLETE;
    }
    public void showLoading(){
        state=STATE_LOADING;
        progressBar.setVisibility(View.VISIBLE);
        tvComplete.setVisibility(View.GONE);
        tvError.setVisibility(View.GONE);
    }
    public void showComplete(){
        state=STATE_COMPLETE;
        progressBar.setVisibility(View.GONE);
        tvComplete.setVisibility(View.VISIBLE);
        tvError.setVisibility(View.GONE);
    }
    public void showError(String errorMsg){
        state=STATE_ERROE;
        progressBar.setVisibility(View.GONE);
        tvComplete.setVisibility(View.GONE);
        tvError.setVisibility(View.VISIBLE);
    }
    public void setErrorClickListener(OnClickListener onClickListener){
        tvError.setOnClickListener(onClickListener);
    }
}
