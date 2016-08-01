package com.feicuiedu.gitdroid.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.feicuiedu.gitdroid.Base.BaseActivity;
import com.feicuiedu.gitdroid.R;
import com.feicuiedu.gitdroid.Tools.ActivityUtils;
import com.feicuiedu.gitdroid.utils.Repo;
import com.feicuiedu.gitdroid.utils.RepoInfoPresenter;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by TJ on 2016/8/1.
 */
public class RepoInfoActivity extends BaseActivity implements RepoInfoPresenter.RepoInfoView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ivIcon)
    ImageView ivIcon;
    @BindView(R.id.tvRepoName)
    TextView tvRepoName;
    @BindView(R.id.tvRepoStars)
    TextView tvRepoStars;
    @BindView(R.id.tvRepoInfo)
    TextView tvRepoInfo;
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    private ActivityUtils activityUtils;
    private RepoInfoPresenter presenter;
    private Repo repo;
    private static final String KEY_REPO = "key_repo";

    public static void open(Context context, @NonNull Repo repo) {
        Intent intent = new Intent(context, RepoInfoActivity.class);
        intent.putExtra(KEY_REPO,repo);
        context.startActivity(intent);
    }

    @Override
    public void setLayout() {
        activityUtils = new ActivityUtils(this);
        setContentView(R.layout.activity_repo_info);
    }

    @Override
    public void getView() {
        presenter=new RepoInfoPresenter(this);

    }

    @Override
    public void setView() {
        setSupportActionBar(toolbar);
        repo= (Repo) getIntent().getSerializableExtra(KEY_REPO);
        presenter.getReadme(repo);
        getSupportActionBar().setTitle(repo.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvRepoInfo.setText(repo.getDescription());
        tvRepoName.setText(repo.getFullname());
        tvRepoStars.setText(String.format("star: %d  fork: %d", repo.getStarCount(), repo.getForkCount()));
        ImageLoader.getInstance().displayImage(repo.getOwner().getAvatar(), ivIcon);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMessage(String msg) {
        activityUtils.showToast(msg);
    }

    @Override
    public void setDate(String htmlContent) {
        webView.loadData(htmlContent,"text/html", "UTF-8");
    }

}
