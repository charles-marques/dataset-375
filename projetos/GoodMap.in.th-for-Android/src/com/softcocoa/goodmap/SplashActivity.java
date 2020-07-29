package com.softcocoa.goodmap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {
	
	protected Handler handler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		handler = new Handler();
	}

	@Override
	protected void onStart() {
		handler.postDelayed(timer, 1750);
		super.onStart();
	}
	
	@Override
	protected void onStop() {
		handler.removeCallbacks(timer);
		super.onStop();
	}
	
	private Runnable timer = new Runnable() {
		@Override
		public void run() {
			startActivity(new Intent(SplashActivity.this, MainActivity.class));
			finish();
		}
	};
	
}
