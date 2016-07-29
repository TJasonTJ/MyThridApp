package com.feicuiedu.gitdroid.Activity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.feicuiedu.gitdroid.Base.BaseActivity;
import com.feicuiedu.gitdroid.NetWork.GitHubApi;
import com.feicuiedu.gitdroid.R;
import com.feicuiedu.gitdroid.utils.LoginPresenter;


import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.droidsonroids.gif.GifImageView;

/**
 * Created by TJ on 2016/7/29.
 */
public class LoginActivity extends BaseActivity {
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.gifImageView)
    GifImageView gifImageView;

    private LoginPresenter presenter;
    @Override
    public void setLayout() {
        setContentView(R.layout.activity_login);
    }

    @Override
    public void getView() {
        presenter=new LoginPresenter();
        initWebView();
    }

    private void initWebView() {
        CookieManager cookieManager=CookieManager.getInstance();
        cookieManager.removeAllCookie();
        webView.loadUrl(GitHubApi.AUTE_RUL);
        webView.setFocusable(true);
        webView.setFocusableInTouchMode(true);
        webView.setWebChromeClient(webChromeClient);
        webView.setWebViewClient(webViewClient);
    }
    private WebChromeClient webChromeClient=new WebChromeClient(){
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if(newProgress>=100){
                gifImageView.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);
            }
        }
    };
    private WebViewClient webViewClient=new WebViewClient(){
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Uri uri=Uri.parse(url);
            if(GitHubApi.CALL_BACK.equals(uri.getScheme())){
                String code=uri.getQueryParameter("code");
                presenter.login(code);
                return true;
            }
            return super.shouldOverrideUrlLoading(view, url);
        }
    };
    @Override
    public void setView() {

    }

}
