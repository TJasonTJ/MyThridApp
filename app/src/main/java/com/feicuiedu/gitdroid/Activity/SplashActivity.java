package com.feicuiedu.gitdroid.Activity;

import android.view.View;
import android.widget.Button;

import com.feicuiedu.gitdroid.Base.BaseActivity;
import com.feicuiedu.gitdroid.R;
import com.feicuiedu.gitdroid.Tools.ActivityUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by TJ on 2016/7/26.
 */
public class SplashActivity extends BaseActivity {
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.btnEnter)
    Button btnEnter;

    private ActivityUtils activityUtils;
    @Override
    public void setLayout() {
        setContentView(R.layout.activity_splash);
    }

    @Override
    public void getView() {
        activityUtils=new ActivityUtils(this);
    }

    @Override
    public void setView() {

    }
    @OnClick({R.id.btnLogin, R.id.btnEnter})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                activityUtils.startActivity(LoginActivity.class);
                finish();
                break;
            case R.id.btnEnter:
                activityUtils.startActivity(HomeActivity.class);
                finish();
                break;
        }
    }
}
