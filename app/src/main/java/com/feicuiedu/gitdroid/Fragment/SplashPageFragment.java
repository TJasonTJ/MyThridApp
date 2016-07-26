package com.feicuiedu.gitdroid.Fragment;

import android.support.v4.view.ViewPager;

import com.feicuiedu.gitdroid.Adapter.SplashPageAdapter;
import com.feicuiedu.gitdroid.Base.BaseFragment;
import com.feicuiedu.gitdroid.R;

import butterknife.BindView;
import me.relex.circleindicator.CircleIndicator;


/**
 * Created by TJ on 2016/7/26.
 */
public class SplashPageFragment extends BaseFragment {
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.indicator)
    CircleIndicator indicator;
    private SplashPageAdapter adapter;

    @Override
    public int setLayout() {
        return R.layout.fragment_splash_pager;
    }

    @Override
    public void getview() {
        adapter = new SplashPageAdapter(getContext());
    }

    @Override
    public void setview() {
        viewPager.setAdapter(adapter);
        indicator.setViewPager(viewPager);
        viewPager.addOnPageChangeListener(pageColorListener);
        viewPager.addOnPageChangeListener(phoneViewListener);
    }

    // 主要为了做背景颜色渐变处理
    private ViewPager.OnPageChangeListener pageColorListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    // 主要为了做"手机"的动画效果处理(平移、缩放、透明度变化)
    private ViewPager.OnPageChangeListener phoneViewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}
