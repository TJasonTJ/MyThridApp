package com.feicuiedu.gitdroid.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.feicuiedu.gitdroid.Base.BaseFragment;
import com.feicuiedu.gitdroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by TJ on 2016/7/27.
 */
public class RepoListFragment extends BaseFragment {
    @BindView(R.id.lvRepos)
    ListView lvRepos;
    private ArrayAdapter<String> adapter;
    @Override
    public int setLayout() {
        return R.layout.fragment_repo_list;
    }

    @Override
    public void getview() {

    }

    @Override
    public void setview() {
        String[] dates={"Aaaaa","Bbbbb","Ccccc","Ddddd"};
        adapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,dates);
        lvRepos.setAdapter(adapter);
    }

}
