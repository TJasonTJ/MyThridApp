package com.feicuiedu.gitdroid.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.feicuiedu.gitdroid.Fragment.RepoListFragment;

/**
 * Created by TJ on 2016/7/27.
 */
public class HotRepoAdapter extends FragmentPagerAdapter{
    public HotRepoAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new RepoListFragment();
    }

    @Override
    public int getCount() {
        return 10;
    }
    @Override public CharSequence getPageTitle(int position) {
        return "Java"+position;
    }
}
