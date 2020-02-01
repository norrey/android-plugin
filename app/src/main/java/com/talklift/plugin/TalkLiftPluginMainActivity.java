package com.talklift.plugin;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
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

//                base_color,
//                text_color,
//                c_code,
        Intent intent = getIntent();
        String base_color = intent.getStringExtra("base_color");
        String text_color = intent.getStringExtra("text_color");
        String c_code = intent.getStringExtra("c_code");


        // This URL should be loaded automatically
         mWebView.loadUrl("https://app.talklift.com/web-widget/1/?org_id=1&c_code="+c_code+"&base_color="+base_color+"&text_color="+text_color);
         mWebView.setWebViewClient(new TalkLiftWebViewClient());
    }

    @Override
    public void onBackPressed() {
        if(mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
