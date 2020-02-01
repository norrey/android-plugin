package com.talklift.plugin;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.talklift.plugin.R;

/**
 * @author Norrey Okumu<okumu.norrey@gmail.com>
 * @since 28.01.2020 19:31
 */

public class TalkLiftPluginMainActivity extends Activity {

    private WebView mWebView;

    @Override
    @SuppressLint("SetJavaScriptEnabled")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talklift_plugin_activity_main);

        mWebView = findViewById(R.id.activity_main_webview);
        mWebView.setWebViewClient(new android.webkit.WebViewClient());
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        try {
            ApplicationInfo ai = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            Bundle bundle = ai.metaData;


            String base_color = bundle.getString("talklift_chat_base_color");
            String text_color = bundle.getString("talklift_chat_text_color");
            String c_code = bundle.getString("talklift_chat_c_color");
            String org_id = bundle.getString("talklift_chat_org_id");

            mWebView.loadUrl("https://app.talklift.com/web-widget/1/?org_id="+org_id+"&c_code=" + c_code + "&base_color=" + base_color + "&text_color=" + text_color);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        mWebView.setWebViewClient(new TalkLiftWebViewClient());
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
