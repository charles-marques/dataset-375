package com.softcocoa.goodmap;

import greendroid.app.GDActivity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class AboutActivity extends GDActivity {
	
	protected Button aboutGoodMapBtn;
	protected Button shareBtn;
	protected Button rateBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActionBarContentView(R.layout.about);

		aboutGoodMapBtn = (Button) findViewById(R.id.about_goodmap_btn);
		shareBtn = (Button) findViewById(R.id.about_share_btn);
		rateBtn = (Button) findViewById(R.id.about_rate_btn);
		
		aboutGoodMapBtn.setOnClickListener(onViewClickListener);
		shareBtn.setOnClickListener(onViewClickListener);
		rateBtn.setOnClickListener(onViewClickListener);
	}
	
	protected OnClickListener onViewClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent = null;
			switch (v.getId()) {
				case R.id.about_goodmap_btn:
					intent = new Intent(v.getContext(), AboutGoodMapActivity.class);
					startActivity(intent);
					break;
					
				case R.id.about_share_btn:
					intent = new Intent(Intent.ACTION_SEND);
					intent.setType("text/plain");
					intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.share_app_title));
				    intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_app_title)+" https://market.android.com/details?id="+v.getContext().getPackageName());
			    	startActivity(Intent.createChooser(intent, getString(R.string.dialog_share_app_title)));
					break;
					
				case R.id.about_rate_btn:
					intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+v.getContext().getPackageName()));
					try {
						startActivity(intent);
					} catch (ActivityNotFoundException e) {
						Toast.makeText(v.getContext(), R.string.error_rate, Toast.LENGTH_SHORT).show();
						e.printStackTrace();
					}
					break;
	
				default:
					break;
			}
		}
	};

}
