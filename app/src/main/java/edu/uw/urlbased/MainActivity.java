package edu.uw.urlbased;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.webkit.WebView;

/**
 * Created by Aba (azri92/aazri3) for UW-IT
 */

public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    //Fragment managing the behaviors, interactions and presentation of the navigation drawer.
    private NavigationDrawerFragment mNavigationDrawerFragment;

    // Used to store the last screen title. For use in {@link #restoreActionBar()}.
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, WebViewFragment.newInstance(position + 1))
                .commit();
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class WebViewFragment extends Fragment {
        //The fragment argument representing the section number for this
        // fragment.
        private static final String ARG_SECTION_NUMBER = "section_number";

        private WebView mWebView;

        //Returns a new instance of this fragment for the given section
        //number.
        public static WebViewFragment newInstance(int sectionNumber) {
            WebViewFragment fragment = new WebViewFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public WebViewFragment() {
        }

        @Override
        public void onActivityCreated(Bundle savedInstantceState) {
            super.onActivityCreated(savedInstantceState);

            mWebView = (WebView) getActivity().findViewById(R.id.fragment_main_webview);
            WebViewClientImpl webViewClient = new WebViewClientImpl(getActivity());
            mWebView.setWebViewClient(webViewClient);
            mWebView.loadUrl("http://curry.eplt.washington.edu:8009/seattle/food/?hybrid=true");

            switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
                case 1:
                    getActivity().setTitle(getString(R.string.title_section1));
                    mWebView.loadUrl(getString(R.string.url_1));
                    break;
                case 2:
                    getActivity().setTitle(getString(R.string.title_section2));
                    mWebView.loadUrl(getString(R.string.url_2));
                    break;
                case 3:
                    getActivity().setTitle(getString(R.string.title_section3));
                    mWebView.loadUrl(getString(R.string.url_3));
                    break;
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            return inflater.inflate(R.layout.fragment_main, container, false);
        }

    }

}
