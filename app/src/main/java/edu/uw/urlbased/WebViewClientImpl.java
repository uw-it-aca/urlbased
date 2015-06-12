package edu.uw.urlbased;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by aazri3 on 5/5/15.
 */
public class WebViewClientImpl extends WebViewClient {
    private Activity activity = null;

    public WebViewClientImpl(Activity activity) {
        this.activity = activity;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, String url) {
        if(url.contains("curry.eplt.washington.edu")) {
            Uri uri = Uri.parse(url);
            String query = uri.getQueryParameter("hybrid");
            Log.d("Intercepted", "URL intercepted: " + url);
            Log.d("Intercepted", "Query param of 'hybrid': " + query);
            Intent intent = new Intent(activity.getApplicationContext(), SecondPageActivity.class);
            intent.putExtra("url", url);
            activity.startActivity(intent);
            return true;
        }

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        activity.startActivity(intent);
        return true;
    }
}
