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
import android.view.MenuItem;

import com.feicuiedu.gitdroid.Adapter.HotRepoAdapter;
import com.feicuiedu.gitdroid.Base.BaseActivity;
import com.feicuiedu.gitdroid.Fragment.HotRepoFragment;
import com.feicuiedu.gitdroid.R;

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
    @Override
    public void setLayout() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void getView() {
        hotRepoFragment=new HotRepoFragment();
    }

    @Override
    public void setView() {
        navigationView.setNavigationItemSelectedListener(listener);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
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
            switch (item.getItemId()) {
                case R.id.github_hot_repo:
                    break;
                case R.id.github_hot_coder:
                    break;
                case R.id.github_trend:
                    break;
                case R.id.arsenal_my_repo:
                    break;
                case R.id.arsenal_recommend:
                    break;
                case R.id.tips_daily:
                    break;
                case R.id.tips_share:
                    break;
            }
            // 返回true，代表将该菜单项变为checked状态
            return true;
        }
    };

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
