package myapp.bwie.com.newstoplihui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import myapp.bwie.com.newstoplihui.R;

public class NewsDetails extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        Intent intent = getIntent();
        String details = intent.getStringExtra("newsDetail");

        webView = (WebView) findViewById(R.id.wv);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);

        webView.setWebChromeClient(new WebChromeClient());

        webView.loadUrl(details);
    }
}
