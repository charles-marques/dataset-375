package com.softcocoa.goodmap;

import android.os.Bundle;

import com.softcocoa.base.WebViewActivity;

public class AboutGoodMapActivity extends WebViewActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		webView.getSettings().setLoadWithOverviewMode(true);
		webView.getSettings().setUseWideViewPort(true);
		
		String html = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">";
		html += "<head>" +
				"<style type='text/css'>body{margin:0; padding:0;}</style>" +
				"<meta name='viewport' content='width=564, minimum-scale=0.57, maximum-scale=1' />" +
				"</head>";
		html += "<body>";
		html += "<img src='file:///android_asset/what1.jpg' /><br/>";
		html += "<img src='file:///android_asset/what2.jpg' /><br/>";
		html += "<img src='file:///android_asset/what3.jpg' /><br/>";
		html += "<img src='file:///android_asset/what4.jpg' /><br/>";
		html += "<img src='file:///android_asset/what5.jpg' /><br/>";
		html += "<img src='file:///android_asset/what6.jpg' />";
		html += "<body></html>";
		
		webView.loadDataWithBaseURL(null, html, "text/html", "utf-8", "about:blank");
	}

}
