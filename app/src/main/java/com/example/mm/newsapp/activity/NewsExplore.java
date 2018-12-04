package com.example.mm.newsapp.activity;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.mm.newsapp.R;

import static com.example.mm.newsapp.Model.Constants.KEY_NEWS_URL;

public class NewsExplore extends AppCompatActivity {

    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_explore);

        Intent intent = getIntent();
        String url = intent.getStringExtra(KEY_NEWS_URL);

        webview = findViewById(R.id.webView);
        webview.loadUrl(url);
        webview.setWebViewClient(new WebViewClient());
//            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                view.loadUrl(request.getUrl().toString());
//                return false;
//            }
//        });





    }
}
