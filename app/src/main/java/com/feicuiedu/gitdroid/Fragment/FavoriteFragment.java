package com.feicuiedu.gitdroid.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.feicuiedu.gitdroid.Base.BaseFragment;
import com.feicuiedu.gitdroid.DB.DBHelp;
import com.feicuiedu.gitdroid.DB.LocalRepoDao;
import com.feicuiedu.gitdroid.DB.RepoGroupDao;
import com.feicuiedu.gitdroid.R;
import com.feicuiedu.gitdroid.Tools.ActivityUtils;
import com.feicuiedu.gitdroid.utils.RepoGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by TJ on 2016/8/3.
 */
public class FavoriteFragment extends BaseFragment implements PopupMenu.OnMenuItemClickListener {
    @BindView(R.id.tvGroupType)
    TextView tvGroupType;
    @BindView(R.id.btnFilter)
    ImageButton btnFilter;
    @BindView(R.id.listView)
    ListView listView;
    
    private ActivityUtils activityUtils;
    private RepoGroupDao repoGroupDao;
    private LocalRepoDao localRepoDao;
    @Override
    public int setLayout() {
        return R.layout.fragment_favorite;
    }

    @Override
    public void getview() {
        activityUtils=new ActivityUtils(this);
        repoGroupDao=new RepoGroupDao(DBHelp.getInstance(getContext()));
        localRepoDao=new LocalRepoDao(DBHelp.getInstance(getContext()));
    }

    @Override
    public void setview() {
        activityUtils.showToast("size="+localRepoDao.queryForAll().size());
    }
    @OnClick(R.id.btnFilter)
    public void showPopupMenu(View view){
        PopupMenu popupMenu=new PopupMenu(getContext(),view);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.menu_popup_repo_groups);
        Menu menu=popupMenu.getMenu();
        List<RepoGroup> repoGroups=repoGroupDao.queryForAll();
        for (RepoGroup repoGroup:repoGroups) {
            menu.add(Menu.NONE,repoGroup.getId(),Menu.NONE,repoGroup.getName());
        }
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        String title=item.getTitle().toString();
        tvGroupType.setText(title);
        setDate(item.getItemId());
        return true;
    }

    private void setDate(int groupId) {
        switch (groupId){
            case R.id.repo_group_all:

                break;
            case R.id.repo_group_no:

                break;
            default:

                break;
        }
    }
}
