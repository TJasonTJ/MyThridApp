package com.feicuiedu.gitdroid.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.feicuiedu.gitdroid.utils.Language;

import java.util.List;

/**
 * Created by TJ on 2016/8/2.
 */
public class HotUsersAdapter extends FragmentPagerAdapter{
    private List<Language> languages;
    public HotUsersAdapter(FragmentManager fm,Context context) {
        super(fm);
        languages=Language.getDefaultLanguages(context);
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
