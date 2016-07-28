package com.feicuiedu.gitdroid.Fragment;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.feicuiedu.gitdroid.Base.BaseFragment;
import com.feicuiedu.gitdroid.FrameLayout.FooterView;
import com.feicuiedu.gitdroid.Interface.RepoListPtrInterface;
import com.feicuiedu.gitdroid.Interface.RepoListView;
import com.feicuiedu.gitdroid.R;
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
    private ArrayAdapter<String> adapter;

    private FooterView footerView;
    private RepoListPresenter repoListPresenter;
    @Override
    public int setLayout() {
        return R.layout.fragment_repo_list;
    }

    @Override
    public void getview() {
        footerView=new FooterView(getContext());
        repoListPresenter=new RepoListPresenter(this);
    }

    @Override
    public void setview() {
        adapter = new ArrayAdapter<String>(
                getContext(), android.R.layout.simple_list_item_1,
                new ArrayList<String>());
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

        }
    @Override
        public void stopRefresh(){
            ptrClassicFrameLayout.refreshComplete();
        }

        public void refreshData(List<String> data){
            adapter.clear();
            adapter.addAll(data);
            adapter.notifyDataSetChanged();
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
    public void addMoreData(List<String> datas) {
        adapter.addAll(datas);
        adapter.notifyDataSetChanged();
    }
}
