package com.feicuiedu.gitdroid.FrameLayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.feicuiedu.gitdroid.R;

/**
 * Created by TJ on 2016/7/26.
 */
public class LeadPage0 extends FrameLayout{
    public LeadPage0(Context context) {
        this(context,null);
    }

    public LeadPage0(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LeadPage0(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    public void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.content_pager_0,this,true);
    }
}
