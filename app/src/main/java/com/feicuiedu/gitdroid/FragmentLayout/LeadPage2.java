package com.feicuiedu.gitdroid.FragmentLayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.feicuiedu.gitdroid.R;

import butterknife.BindView;

/**
 * Created by TJ on 2016/7/26.
 */
public class LeadPage2 extends FrameLayout {
    @BindView(R.id.ivBubble1)
    ImageView ivBubble1;
    @BindView(R.id.ivBubble2)
    ImageView ivBubble2;
    @BindView(R.id.ivBubble3)
    ImageView ivBubble3;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvIntroduction)
    TextView tvIntroduction;

    public LeadPage2(Context context) {
        this(context, null);
    }

    public LeadPage2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LeadPage2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.content_pager_2, this, true);
    }
}
