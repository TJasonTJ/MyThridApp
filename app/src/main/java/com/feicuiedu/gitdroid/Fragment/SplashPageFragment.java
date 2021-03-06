package com.feicuiedu.gitdroid.Fragment;

import android.animation.ArgbEvaluator;
import android.support.v4.view.ViewPager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.feicuiedu.gitdroid.Adapter.SplashPageAdapter;
import com.feicuiedu.gitdroid.Base.BaseFragment;
import com.feicuiedu.gitdroid.FrameLayout.LeadPage2;
import com.feicuiedu.gitdroid.R;

import butterknife.BindColor;
import butterknife.BindView;
import me.relex.circleindicator.CircleIndicator;


/**
 * Created by TJ on 2016/7/26.
 */
public class SplashPageFragment extends BaseFragment {
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.indicator)
    CircleIndicator indicator;
    private SplashPageAdapter adapter;
    @BindView(R.id.content)
    FrameLayout frameLayout;
    @BindView(R.id.layoutPhone)
    FrameLayout layoutPhone;
    @BindColor(R.color.colorGreen)
    int colorGreen;
    @BindColor(R.color.colorRed)
    int colorRed;
    @BindColor(R.color.colorYellow)
    int colorYellow;
    @BindView(R.id.ivPhoneFont)
    ImageView ivPhoneFont;
    private boolean mflag=false;
    @Override
    public int setLayout() {
        return R.layout.fragment_splash_pager;
    }

    @Override
    public void getview() {
        adapter = new SplashPageAdapter(getContext());
    }

    @Override
    public void setview() {
        viewPager.setAdapter(adapter);
        indicator.setViewPager(viewPager);
        viewPager.addOnPageChangeListener(pageColorListener);
        viewPager.addOnPageChangeListener(phoneViewListener);
    }

    // 主要为了做背景颜色渐变处理
    private ViewPager.OnPageChangeListener pageColorListener = new ViewPager.OnPageChangeListener() {
        final ArgbEvaluator argbEvaluator=new ArgbEvaluator();
        // 在Scroll过程中进行触发的方法
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            // 第一个页面到第二个页面之间
            if(position==0){
                int color = (int) argbEvaluator.evaluate(positionOffset,colorGreen,colorRed);
                frameLayout.setBackgroundColor(color);
            }
            // 第二个页面到第三个页面之间
            if(position==1){
                int color= (int) argbEvaluator.evaluate(positionOffset,colorRed,colorYellow);
                frameLayout.setBackgroundColor(color);
            }
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    // 主要为了做"手机"的动画效果处理(平移、缩放、透明度变化)
    private ViewPager.OnPageChangeListener phoneViewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            // 第一个页面到第二个页面之间
            if(position==0){
                //手机的缩放处理
                float scale = 0.3f+positionOffset*0.7f;
                layoutPhone.setScaleX(scale);
                layoutPhone.setScaleY(scale);
                //手机平移处理
                int scroll = (int) ((positionOffset-1)*300);
                layoutPhone.setTranslationX(scroll);
                //手机字体的渐变
                ivPhoneFont.setAlpha(positionOffset);
                return;
            }
            //在第二个页面和第三个页面之间
            if(position==1){
                layoutPhone.setTranslationX(-positionOffsetPixels);
            }
        }

        @Override
        public void onPageSelected(int position) {
            // 当显示出最后一个pager时，播放它自己的动画

            if(mflag){
                if(position==2) {
                    LeadPage2 page2 = (LeadPage2) adapter.getView(position);
                    page2.Show();
                }
            }else{
                if(position==2){
                    LeadPage2 page2= (LeadPage2) adapter.getView(position);
                    page2.showAnimation();
                    mflag=true;
                }
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}
