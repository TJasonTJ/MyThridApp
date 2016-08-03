package com.feicuiedu.gitdroid.Activity;


import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.feicuiedu.gitdroid.Adapter.HotRepoAdapter;
import com.feicuiedu.gitdroid.Base.BaseActivity;
import com.feicuiedu.gitdroid.Fragment.FavoriteFragment;
import com.feicuiedu.gitdroid.Fragment.HotRepoFragment;
import com.feicuiedu.gitdroid.Fragment.HotUsersFragment;
import com.feicuiedu.gitdroid.R;
import com.feicuiedu.gitdroid.Tools.ActivityUtils;
import com.feicuiedu.gitdroid.utils.UserRepo;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by TJ on 2016/7/27.
 */
public class HomeActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.navigationView)
    NavigationView navigationView;
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    private HotRepoFragment hotRepoFragment;
    private HotUsersFragment hotUserFragment;
    private FavoriteFragment favoriteFragment;
    private Button btnLogin;
    private ImageView ivIcon;
    private ActivityUtils activityUtils;
    @Override
    public void setLayout() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void getView() {
        activityUtils=new ActivityUtils(this);
        hotRepoFragment=new HotRepoFragment();
        favoriteFragment=new FavoriteFragment();
    }

    @Override
    public void setView() {
        navigationView.setNavigationItemSelectedListener(listener);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
        drawerLayout.addDrawerListener(toggle);
        btnLogin=ButterKnife.findById(navigationView.getHeaderView(0),R.id.btnLogin);
        ivIcon=ButterKnife.findById(navigationView.getHeaderView(0),R.id.ivIcon);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityUtils.startActivity(LoginActivity.class);
                finish();
            }
        });
        replaceFragment(hotRepoFragment);
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container,fragment);
        fragmentTransaction.commit();
    }
    private NavigationView.OnNavigationItemSelectedListener listener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            if (item.isChecked()) {
                item.setChecked(false);
            }
            switch (item.getItemId()) {
                case R.id.github_hot_repo:
                    if (!hotRepoFragment.isAdded()) {
                        replaceFragment(hotRepoFragment);
                    }
                    break;
                case R.id.github_hot_coder:
                    if (hotUserFragment == null) hotUserFragment = new HotUsersFragment();
                    if (!hotUserFragment.isAdded()) {
                        replaceFragment(hotUserFragment);
                    }
                    break;
                case R.id.github_trend:
                    break;
                case R.id.arsenal_my_repo:
                    if(favoriteFragment==null){
                        favoriteFragment=new FavoriteFragment();
                    }
                    if(!favoriteFragment.isAdded()){
                        replaceFragment(favoriteFragment);
                    }
                    break;
                case R.id.arsenal_recommend:
                    break;
                case R.id.tips_daily:
                    break;
                case R.id.tips_share:
                    break;
            }
            drawerLayout.post(new Runnable() {
                @Override public void run() {
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
            });
            // 返回true，代表将该菜单项变为checked状态
            return true;
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("msg","执行 ");
        if(UserRepo.isEmpty()){
            btnLogin.setText(R.string.login_github);
            return;
        }
        btnLogin.setText(R.string.switch_account);
        getSupportActionBar().setTitle(UserRepo.getUser().getName());
        String photoUrl=UserRepo.getUser().getAvatar();
        ImageLoader.getInstance().displayImage(photoUrl,ivIcon);
        Log.d("msg","名字："+UserRepo.getUser().getName());
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            drawerLayout.openDrawer(GravityCompat.START);
        }
        super.onBackPressed();
    }

}
