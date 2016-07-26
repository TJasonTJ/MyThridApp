package com.feicuiedu.gitdroid.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindViews;
import butterknife.ButterKnife;

/**
 * Created by TJ on 2016/7/26.
 */
public abstract class BaseFragment extends Fragment{
    public static View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(setLayout(),null);
        ButterKnife.bind(this,view);
        getview();
        setview();
        return view;
    }
    public abstract int setLayout();
    public abstract void getview();
    public abstract void setview();
}
