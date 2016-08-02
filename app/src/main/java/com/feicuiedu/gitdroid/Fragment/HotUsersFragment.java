package com.feicuiedu.gitdroid.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.feicuiedu.gitdroid.Adapter.UserListAdapter;
import com.feicuiedu.gitdroid.Base.BaseFragment;
import com.feicuiedu.gitdroid.FrameLayout.FooterView;
import com.feicuiedu.gitdroid.Interface.UserListView;
import com.feicuiedu.gitdroid.R;
import com.feicuiedu.gitdroid.Tools.ActivityUtils;
import com.feicuiedu.gitdroid.utils.HotUserPresenter;
import com.feicuiedu.gitdroid.utils.User;
import com.mugen.Mugen;
import com.mugen.MugenCallbacks;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.header.StoreHouseHeader;

/**
 * Created by TJ on 2016/8/2.
 */
public class HotUsersFragment extends BaseFragment implements UserListView {
    @BindView(R.id.ptrClassicFrameLayout)
    PtrClassicFrameLayout ptrClassicFrameLayout;
    @BindView(R.id.emptyView)
    TextView emptyView;
    @BindView(R.id.errorView)
    TextView errorView;
    @BindView(R.id.lvRepos)
    ListView lvRepos;
    private FooterView footView;
    private ActivityUtils activityUtils;
    private FooterView footerView;
    private HotUserPresenter presenter;
    private UserListAdapter adapter;

    @Override
    public int setLayout() {
        return R.layout.fragment_hot_user;
    }

    @Override
    public void getview() {
        activityUtils = new ActivityUtils(this);
        presenter = new HotUserPresenter(this);
        adapter = new UserListAdapter();

    }

    @Override
    public void setview() {
        lvRepos.setAdapter(adapter);
        initPullToRefresh();
        // 初始上拉加载更多
        initLoadMoreScroll();
        if (adapter.getCount() == 0) {
            ptrClassicFrameLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ptrClassicFrameLayout.autoRefresh();
                }
            }, 200);
        }
    }

    private void initPullToRefresh() {
        ptrClassicFrameLayout.setLastUpdateTimeRelateObject(true);
        ptrClassicFrameLayout.setDurationToCloseHeader(1500);
        ptrClassicFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.refresh();
            }
        });
        StoreHouseHeader header = new StoreHouseHeader(getContext());
        header.initWithString("I LIKE" + " JAVA");
        header.setPadding(0, 60, 0, 60);
        ptrClassicFrameLayout.setHeaderView(header);
        ptrClassicFrameLayout.addPtrUIHandler(header);
        ptrClassicFrameLayout.setBackgroundResource(R.color.colorRefresh);
    }

    private void initLoadMoreScroll() {
        footerView = new FooterView(getContext());
        Mugen.with(lvRepos, new MugenCallbacks() {
            @Override
            public void onLoadMore() {
                presenter.loadMore();
            }

            @Override
            public boolean isLoading() {
                return lvRepos.getFooterViewsCount() > 0 && footerView.isLoading();
            }

            @Override
            public boolean hasLoadedAllItems() {
                return lvRepos.getFooterViewsCount() > 0 && footerView.isComplete();
            }
        });
    }

    @Override
    public void showLoadMoreLoading() {
        if (lvRepos.getFooterViewsCount() == 0) {
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
        if (lvRepos.getFooterViewsCount() == 0) {
            lvRepos.addFooterView(footerView);
        }
        footerView.showError(erroMsg);
    }

    @Override
    public void addMoreData(List<User> datas) {
        adapter.addAll(datas);
    }

    @Override
    public void showContentView() {
        ptrClassicFrameLayout.setVisibility(View.VISIBLE);
        emptyView.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
    }

    @Override
    public void showErrorView(String errorMsg) {
        ptrClassicFrameLayout.setVisibility(View.GONE);
        emptyView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEmptyView() {
        ptrClassicFrameLayout.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
        errorView.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(String msg) {
        activityUtils.showToast(msg);
    }

    @Override
    public void stopRefresh() {
        ptrClassicFrameLayout.refreshComplete();
    }

    @Override
    public void refreshData(List<User> data) {
        adapter.clear();
        adapter.addAll(data);
    }
}
