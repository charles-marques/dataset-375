package com.softcocoa.base;

import android.os.Bundle;

public class LinkWebViewActivity extends WebViewActivity {

	public static String EXTRA_URL = "EXTRA_URL";
	
	protected String url;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		webView.getSettings().setLoadWithOverviewMode(true);
		webView.getSettings().setUseWideViewPort(true);
		url = getIntent().getStringExtra(EXTRA_URL);
		if (url != null)
			webView.loadUrl(url);
	}
	
}