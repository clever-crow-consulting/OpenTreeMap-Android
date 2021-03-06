package org.azavea.otm.ui;

import org.azavea.otm.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class AboutDisplay extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.about_activity, container, false);
        WebView wv = (WebView) view.findViewById(R.id.about_webview);
        wv.loadUrl("file:///android_asset/about_content.html");

        return view;
    }
}
