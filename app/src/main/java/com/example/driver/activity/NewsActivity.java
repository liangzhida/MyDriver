package com.example.driver.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.driver.R;

public class NewsActivity extends AppCompatActivity {


    private ImageView imgfanhui;
    private WebView webview;
    private ImageView imgtui;
    private ImageView imgmain;
    private ImageView imgqian1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        initView();

        imgfanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
        final String url = intent.getStringExtra("url");
        webview.loadUrl(url);
        webview.getSettings().setJavaScriptEnabled(true);//支持javascript
        webview.setWebViewClient(new ArticleWebViewClient());
        imgmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webview.loadUrl(url);
            }
        });

    }

    private void initView() {
        imgfanhui = (ImageView) findViewById(R.id.imgfanhui);
        webview = (WebView) findViewById(R.id.webview);
        imgtui = (ImageView) findViewById(R.id.imgtui);
        imgmain = (ImageView) findViewById(R.id.imgmain);
        imgqian1 = (ImageView) findViewById(R.id.imgqian1);
    }

    private class ArticleWebViewClient extends WebViewClient {

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            //重置webview中img标签的图片大小
            imgReset();
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    /**
     * 对图片进行重置大小，宽度就是手机屏幕宽度，高度根据宽度比便自动缩放
     **/
    private void imgReset() {
        webview.loadUrl("javascript:(function(){" +
                "var objs = document.getElementsByTagName('img'); " +
                "for(var i=0;i<objs.length;i++)  " +
                "{"
                + "var img = objs[i];   " +
                "    img.style.maxWidth = '100%'; img.style.height = 'auto';  " +
                "}" +
                "})()");
    }


}
