package com.feicuiedu.gitdroid.Base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by TJ on 2016/7/26.
 */
public abstract class BaseActivity extends AppCompatActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout();
        getView();
        setView();
    }
    public abstract void setLayout();
    public abstract void getView();
    public abstract void setView();
}
