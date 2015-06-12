package edu.uw.urlbased;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.webkit.WebView;

public class SecondPageActivity extends ActionBarActivity {

    private static final String TAG = "SecondPageActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);

        setTitle("Second Page");

        WebView mWebView = (WebView) findViewById(R.id.second_webview);
        String url = getIntent().getStringExtra("url");
        Log.d(TAG, "URL received: " + url);
        mWebView.loadUrl(url);
    }

}
