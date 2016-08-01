package com.feicuiedu.gitdroid.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.feicuiedu.gitdroid.R;
import com.feicuiedu.gitdroid.utils.Repo;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.Collection;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by TJ on 2016/8/1.
 */
public class RepoListAdapter extends BaseAdapter {
    private final ArrayList<Repo> datas;

    public RepoListAdapter() {
        datas = new ArrayList<Repo>();
    }

    public void addAll(Collection<Repo> repos) {
        datas.addAll(repos);
        notifyDataSetChanged();
    }

    public void clear() {
        datas.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Repo getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder vh=null;
        if (view == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_repo, parent, false);
            vh=new ViewHolder(view);
            view.setTag(vh);
        }else{
            vh= (ViewHolder) view.getTag();
        }
        Repo repo=getItem(position);
        vh.tvRepoInfo.setText(repo.getDescription());
        vh.tvRepoName.setText(repo.getFullname());
        vh.tvRepoStars.setText(repo.getStarCount()+"");
        ImageLoader.getInstance().displayImage(repo.getOwner().getAvatar(),vh.ivIcon);
        return view;
    }
    class ViewHolder {
        @BindView(R.id.ivIcon)
        ImageView ivIcon;
        @BindView(R.id.tvRepoName)
        TextView tvRepoName;
        @BindView(R.id.tvRepoInfo)
        TextView tvRepoInfo;
        @BindView(R.id.tvRepoStars)
        TextView tvRepoStars;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
