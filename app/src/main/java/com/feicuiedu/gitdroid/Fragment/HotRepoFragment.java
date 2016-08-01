package com.feicuiedu.gitdroid.Fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.feicuiedu.gitdroid.Adapter.HotRepoAdapter;
import com.feicuiedu.gitdroid.Base.BaseFragment;
import com.feicuiedu.gitdroid.R;

import butterknife.BindView;

/**
 * Created by TJ on 2016/7/27.
 */
public class HotRepoFragment extends BaseFragment {
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private HotRepoAdapter hotRepoAdapter;

    @Override
    public int setLayout() {
        return R.layout.fragment_hot_repo;
    }

    @Override
    public void getview() {
        hotRepoAdapter=new HotRepoAdapter(getFragmentManager(),getContext());
    }
    @Override
    public void setview() {
        viewPager.setAdapter(hotRepoAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
