package com.feicuiedu.gitdroid.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.feicuiedu.gitdroid.Base.BaseFragment;
import com.feicuiedu.gitdroid.R;
import com.feicuiedu.gitdroid.utils.RepoListPresenter;

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
public class RepoListFragment extends BaseFragment {
    @BindView(R.id.lvRepos)
    ListView lvRepos;
    @BindView(R.id.ptrClassicFrameLayout)
    PtrClassicFrameLayout ptrClassicFrameLayout;
    @BindView(R.id.emptyView)
    TextView emptyView;
    @BindView(R.id.errorView)
    TextView errorView;
    private ArrayAdapter<String> adapter;

    private RepoListPresenter repoListPresenter;
    @Override
    public int setLayout() {
        return R.layout.fragment_repo_list;
    }

    @Override
    public void getview() {
        repoListPresenter=new RepoListPresenter(this);
    }

    @Override
    public void setview() {
        adapter = new ArrayAdapter<String>(
                getContext(), android.R.layout.simple_list_item_1,
                new ArrayList<String>());
        lvRepos.setAdapter(adapter);
        initPullToRefresh();
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
        public void showContentView(){
            ptrClassicFrameLayout.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
            errorView.setVisibility(View.GONE);
        }
        public void showErrorView(String errorMsg){
            ptrClassicFrameLayout.setVisibility(View.GONE);
            emptyView.setVisibility(View.GONE);
            errorView.setVisibility(View.VISIBLE);
        }
        public void showEmptyView(){
             ptrClassicFrameLayout.setVisibility(View.GONE);
             emptyView.setVisibility(View.VISIBLE);
            errorView.setVisibility(View.GONE);
    }
        public void showMessage(String msg){

        }
        public void stopRefresh(){
            ptrClassicFrameLayout.refreshComplete();
        }
        public void refreshData(List<String> data){
            adapter.clear();
            adapter.addAll(data);
            adapter.notifyDataSetChanged();
        }
}
