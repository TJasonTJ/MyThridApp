package com.feicuiedu.gitdroid.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.feicuiedu.gitdroid.R;
import com.feicuiedu.gitdroid.utils.GankItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by TJ on 2016/8/5.
 */
public class GankAdapter extends BaseAdapter {
    private final List<GankItem> list;

    public GankAdapter() {
        list = new ArrayList<GankItem>();
    }

    public void setDate(List<GankItem> gankItems) {
        list.clear();
        list.addAll(gankItems);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public GankItem getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder vh = null;
        if (view == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_gank, null);
            vh=new ViewHolder(view);
            view.setTag(vh);
        }else{
            vh= (ViewHolder) view.getTag();
        }
        GankItem gankItem=getItem(position);
        vh.gankItem.setText(gankItem.getDesc());
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.gank_item)
        TextView gankItem;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
