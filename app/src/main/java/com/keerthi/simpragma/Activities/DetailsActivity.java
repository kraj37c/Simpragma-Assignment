package com.keerthi.simpragma.Activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.keerthi.simpragma.R;
import com.squareup.picasso.Picasso;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class DetailsActivity extends AppCompatActivity {

    TextView tvTitle, tvIngridents;
    ImageView ivTumbnail;
    WebView webView;
    String title, ingredients, thumbnail, href;
    final Activity activity = this;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        tvTitle = (TextView) findViewById(R.id.activity_details_textView_title);
        tvIngridents = (TextView) findViewById(R.id.activity_details_textView_ingridents);
        ivTumbnail = (ImageView) findViewById(R.id.activity_details_imageView_tumbnail);
        webView = (WebView) findViewById(R.id.activity_details_webView);
        progressBar = (ProgressBar)findViewById(R.id.activity_details_progressBar);

        Intent intent = getIntent();

        title = intent.getStringExtra("title");
        ingredients = intent.getStringExtra("ingredients");
        thumbnail = intent.getStringExtra("thumbnail");
        href = intent.getStringExtra("href");

        if(!thumbnail.equals(""))
            Picasso.with(DetailsActivity.this).load(thumbnail).into(ivTumbnail);

        tvTitle.setText(title);
        tvIngridents.setText(ingredients);

        String formattedString = href.replaceAll("\"", "\"");

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

//        webView.setWebChromeClient(new WebChromeClient() {
//            public void onProgressChanged(WebView view, int progress)
//            {
//                if(progress == 100)
//                    progressBar.setVisibility(View.GONE);
//            }
//        });
//
//        webView.setWebViewClient(new WebViewClient() {
//            @Override
//            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl){ }
//
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url)
//            {
//                view.loadUrl(url);
//                return true;
//            }
//        });
//        webView.loadUrl(formattedString);

        webView.setWebViewClient(new myWebClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(formattedString);
    }

    public class myWebClient extends WebViewClient
    {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            progressBar.setVisibility(View.VISIBLE);
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
        }
    }


}
