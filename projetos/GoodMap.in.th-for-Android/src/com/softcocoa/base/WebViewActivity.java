package com.softcocoa.base;

import greendroid.app.GDActivity;
import greendroid.widget.ActionBar.Type;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ScrollView;

import com.softcocoa.goodmap.R;

public class WebViewActivity extends GDActivity{

	public static String EXTRA_START_AT_BOTTOM= "EXTRA_START_AT_BOTTOM";

	protected WebView webView;
	protected boolean startAtBottom;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActionBarContentView(R.layout.webview);
		
		getActionBar().setType(Type.Normal);
		
		webView = (WebView) findViewById(R.id.webview);
		WebSettings webSettings = webView.getSettings();
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
//        webSettings.setUseWideViewPort(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDefaultTextEncodingName("utf-8");
		webSettings.setJavaScriptEnabled(true);
		webSettings.setLoadsImagesAutomatically(true);
		webSettings.setRenderPriority(RenderPriority.HIGH);
		webSettings.setSupportMultipleWindows(false);
		webSettings.setGeolocationEnabled(true);
		webView.setWebViewClient(new MyWebViewClient());
		webView.setWebChromeClient(new MyWebChromeClient());
		webView.setScrollBarStyle(ScrollView.SCROLLBARS_INSIDE_OVERLAY);
		webView.setScrollbarFadingEnabled(true);
		webView.requestFocusFromTouch();
		
		startAtBottom = getIntent().getBooleanExtra(EXTRA_START_AT_BOTTOM, false);
	}
	
	public class MyWebViewClient extends WebViewClient{

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			return super.shouldOverrideUrlLoading(view, url);
		}

		@Override
		public void onPageFinished(WebView view, String url) {
			if (startAtBottom) {
				view.scrollBy(0, 100000);
				startAtBottom = false;	
			}
			super.onPageFinished(view, url);
		}

		@Override
		public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
			if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
				if (view.canGoBack()) {
					view.goBack();
					return true;
				}
			}
			return super.shouldOverrideKeyEvent(view, event);
		}
		
	}
	
	public class MyWebChromeClient extends WebChromeClient {

		@Override
		public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
			new AlertDialog.Builder(view.getContext())
				.setMessage(message)
				.setPositiveButton(R.string.dialog_button_ok,
						new OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								result.confirm();
							}
						})
				.setNegativeButton(R.string.dialog_button_cancel,
						new OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								result.cancel();
							}
						})
				.setCancelable(false)
				.create()
				.show();
			return true;
		}

		@Override
		public void onGeolocationPermissionsShowPrompt(String origin, Callback callback) {
			callback.invoke(origin, true, true);
		}

		/*@Override
		public void onReceivedTitle(WebView view, String title) {
			setTitle(title);
			super.onReceivedTitle(view, title);
		}*/
		
		
		
	}
	
}
