package com.feicuiedu.gitdroid.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.feicuiedu.gitdroid.FragmentLayout.LeadPage0;
import com.feicuiedu.gitdroid.FragmentLayout.LeadPage1;
import com.feicuiedu.gitdroid.FragmentLayout.LeadPage2;

/**
 * Created by TJ on 2016/7/26.
 */
public class SplashPageAdapter extends PagerAdapter{
    private final View[] views;

    public SplashPageAdapter(Context context) {
        views=new View[]{
                new LeadPage0(context),
                new LeadPage1(context),
                new LeadPage2(context)
        };
    }
    public View getView(int position) {
        return views[position];
    }
    @Override
    public int getCount() {
        return views.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views[position],0);
        return views[position];
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
