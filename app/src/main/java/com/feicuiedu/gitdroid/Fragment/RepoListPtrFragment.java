package com.feicuiedu.gitdroid.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.feicuiedu.gitdroid.Adapter.RepoListAdapter;
import com.feicuiedu.gitdroid.Base.BaseFragment;
import com.feicuiedu.gitdroid.FrameLayout.FooterView;
import com.feicuiedu.gitdroid.Interface.RepoListPtrInterface;
import com.feicuiedu.gitdroid.Interface.RepoListView;
import com.feicuiedu.gitdroid.R;
import com.feicuiedu.gitdroid.Tools.ActivityUtils;
import com.feicuiedu.gitdroid.utils.Language;
import com.feicuiedu.gitdroid.utils.Repo;
import com.feicuiedu.gitdroid.utils.RepoListPresenter;
import com.mugen.Mugen;
import com.mugen.MugenCallbacks;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.header.StoreHouseHeader;

/**
 * Created by TJ on 2016/7/27.
 */
public class RepoListPtrFragment extends BaseFragment implements RepoListView {
    @BindView(R.id.lvRepos)
    ListView lvRepos;
    @BindView(R.id.ptrClassicFrameLayout)
    PtrClassicFrameLayout ptrClassicFrameLayout;
    @BindView(R.id.emptyView)
    TextView emptyView;
    @BindView(R.id.errorView)
    TextView errorView;
    private RepoListAdapter adapter;

    private static final String KEY_LANGUAGE="key_language";
    private FooterView footerView;
    private RepoListPresenter repoListPresenter;
    private ActivityUtils activityUtils;

    private Language getLanguage(){
        return (Language) getArguments().getSerializable(KEY_LANGUAGE);
    }
    public static RepoListPtrFragment getInstance(Language language){
        RepoListPtrFragment fragment=new RepoListPtrFragment();
        Bundle bundle=new Bundle();
        bundle.putSerializable(KEY_LANGUAGE,language);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int setLayout() {
        return R.layout.fragment_repo_list;
    }

    @Override
    public void getview() {
        activityUtils=new ActivityUtils(this);
        footerView=new FooterView(getContext());
        repoListPresenter=new RepoListPresenter(this,getLanguage());

        adapter=new RepoListAdapter();
    }

    @Override
    public void setview() {
        lvRepos.setAdapter(adapter);
        initPullToRefresh();
        initLoadMoreScroll();
    }

    private void initLoadMoreScroll() {
        Mugen.with(lvRepos, new MugenCallbacks() {
            @Override
            public void onLoadMore() {
                repoListPresenter.loadMore();
            }

            @Override
            public boolean isLoading() {
                return lvRepos.getFooterViewsCount()>0&&footerView.isLoading();
            }

            @Override
            public boolean hasLoadedAllItems() {
                return lvRepos.getFooterViewsCount()>0&&footerView.isComplete();
            }
        }).start();
    }

    private void initPullToRefresh() {
        ptrClassicFrameLayout.setLastUpdateTimeRelateObject(this);
        ptrClassicFrameLayout.setDurationToCloseHeader(1500);
        ptrClassicFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                repoListPresenter.refresh();
            }
        });
        StoreHouseHeader header=new StoreHouseHeader(getContext());
        header.initWithString("I LIKE "+" JAVA");
        header.setPadding(0,60,0,60);
        ptrClassicFrameLayout.setHeaderView(header);
        ptrClassicFrameLayout.addPtrUIHandler(header);
        ptrClassicFrameLayout.setBackgroundResource(R.color.colorRefresh);
    }
        @Override
        public void showContentView(){
            ptrClassicFrameLayout.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
            errorView.setVisibility(View.GONE);
        }
    @Override
        public void showErrorView(String errorMsg){
            ptrClassicFrameLayout.setVisibility(View.GONE);
            emptyView.setVisibility(View.GONE);
            errorView.setVisibility(View.VISIBLE);
        }
    @Override
        public void showEmptyView(){
             ptrClassicFrameLayout.setVisibility(View.GONE);
             emptyView.setVisibility(View.VISIBLE);
            errorView.setVisibility(View.GONE);
    }
    @Override
        public void showMessage(String msg){
            activityUtils.showToast(msg);
        }
    @Override
        public void stopRefresh(){
            ptrClassicFrameLayout.refreshComplete();
        }

        public void refreshData(List<Repo> datas){
            adapter.clear();
            adapter.addAll(datas);
        }

    @Override
    public void showLoadMoreLoading() {
        if(lvRepos.getFooterViewsCount()==0){
            lvRepos.addFooterView(footerView);
        }
        footerView.showLoading();
    }

    @Override
    public void hideLoadMore() {
        lvRepos.removeFooterView(footerView);
    }

    @Override
    public void showLoadMoreErro(String erroMsg) {
        if(lvRepos.getFooterViewsCount()==0){
            lvRepos.addFooterView(footerView);
        }
        footerView.showError(erroMsg);
    }

    @Override
    public void addMoreData(List<Repo> datas) {
        adapter.addAll(datas);
    }
}
