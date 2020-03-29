package com.example.gutschein2go;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends AppCompatActivity {

    WebView webView;
    String url = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webView = findViewById(R.id.WebView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                // do your handling codes here, which url is the requested url
                // probably you need to open that url rather than redirect:
                view.loadUrl(url);
                return false; // then it is not handled by default action
            }
        });
        Intent i = this.getIntent();
        Bundle extras = i.getExtras();
        if(extras != null) {
            url = extras.getString("url");
            if(Patterns.WEB_URL.matcher(url).matches()){
                if(url.contains("g2g.at"))
                {
                    webView.loadUrl(url);
                }
                else {
                    webView.loadUrl("https://www.g2g.at/verify?enter&mobile");
                }
            }
            else {
                Intent intent = new Intent(this, MainActivity.class);
                this.startActivity(intent);
            }

        }
        else {
            webView.loadUrl("https://www.g2g.at/verify?enter&mobile");
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuwebview, menu);
        return true;
    }

    public void MenuItemBack_Click (MenuItem m) {
        Intent i = new Intent(this, MainActivity.class);
        this.startActivity(i);
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack())
            webView.goBack();
        else {
            super.onBackPressed();
        }
    }
}
