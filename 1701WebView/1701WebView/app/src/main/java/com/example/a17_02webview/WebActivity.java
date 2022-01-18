package com.example.a17_02webview;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class WebActivity extends AppCompatActivity {
    WebView webView = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view);
        Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
        webView=findViewById(R.id.wv);
        TextView provaText =findViewById(R.id.provaText);

        provaText.setText("NO");

       Bundle bundle = getIntent().getExtras();
       String url = bundle.getString("keyUrl");
       Toast.makeText(this, url, Toast.LENGTH_LONG).show();
       provaText.setText(url);


        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        //webView.loadUrl(url);
    }
}
