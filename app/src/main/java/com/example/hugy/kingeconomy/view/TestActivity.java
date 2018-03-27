package com.example.hugy.kingeconomy.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.hugy.kingeconomy.R;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        WebView viewById = findViewById(R.id.wv_page);
        viewById.getSettings().setJavaScriptEnabled(true);
        viewById.setWebViewClient(new WebViewClient());
        viewById.loadUrl("http://www.baidu.com");
    }
}
