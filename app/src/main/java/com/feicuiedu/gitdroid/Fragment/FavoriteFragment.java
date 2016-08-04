package com.feicuiedu.gitdroid.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.feicuiedu.gitdroid.Adapter.FavoriteAdapter;
import com.feicuiedu.gitdroid.Base.BaseFragment;
import com.feicuiedu.gitdroid.DB.DBHelp;
import com.feicuiedu.gitdroid.DB.LocalRepo;
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
    private FavoriteAdapter adapter;
    private LocalRepo currentLocalRepo; // 当前操作的仓库(上下文菜单)
    private int currentRepoGroupId; // 当前仓库类别
    @Override
    public int setLayout() {
        return R.layout.fragment_favorite;
    }

    @Override
    public void getview() {
        activityUtils=new ActivityUtils(this);
        repoGroupDao=new RepoGroupDao(DBHelp.getInstance(getContext()));
        localRepoDao=new LocalRepoDao(DBHelp.getInstance(getContext()));
        adapter=new FavoriteAdapter();
    }

    @Override
    public void setview() {
        activityUtils.showToast("size=" + localRepoDao.queryForAll().size());
        listView.setAdapter(adapter);
        setDate(R.id.repo_group_all);
        registerForContextMenu(listView);
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
        currentRepoGroupId=item.getItemId();
//        Log.d("msg","currentRepoGroupId"+currentRepoGroupId+"");
        setDate(currentRepoGroupId);
//        setDate(item.getItemId());
        return true;
    }

    private void setDate(int groupId) {
        switch (groupId){
            case R.id.repo_group_all:
                adapter.setDate(localRepoDao.queryForAll());
                break;
            case R.id.repo_group_no:
                adapter.setDate(localRepoDao.queryForNoGroup());
                break;
            default:
                Log.d("msg","数据"+localRepoDao.queryForGroupId(groupId).toString());
                adapter.setDate(localRepoDao.queryForGroupId(groupId));
                break;
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if(v.getId()==R.id.listView){
            AdapterView.AdapterContextMenuInfo adapterMenuInfo= (AdapterView.AdapterContextMenuInfo) menuInfo;
            int position =adapterMenuInfo.position;
            currentLocalRepo =adapter.getItem(position);

            MenuInflater menuInflater=getActivity().getMenuInflater();
            menuInflater.inflate(R.menu.menu_context_favorite,menu);
            SubMenu subMenu=menu.findItem(R.id.sub_menu_move).getSubMenu();
            List<RepoGroup> repoGroups=repoGroupDao.queryForAll();
            for (RepoGroup repo:repoGroups
                 ) {
                subMenu.add(R.id.menu_group_move,repo.getId(),Menu.NONE,repo.getName());
            }
        }
    }





    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.delete){
            localRepoDao.delete(currentLocalRepo);
            setDate(currentRepoGroupId);
            return true;
        }
        int groupId=item.getGroupId();
        if(groupId==R.id.menu_group_move){
            if(id==R.id.repo_group_no){
                currentLocalRepo.setRepoGroup(null);
            }else{
                currentLocalRepo.setRepoGroup(repoGroupDao.queryForId(id));
            }
            localRepoDao.createOrUpdate(currentLocalRepo);
            setDate(currentRepoGroupId);
            return true;
        }
        return super.onContextItemSelected(item);
    }
}
