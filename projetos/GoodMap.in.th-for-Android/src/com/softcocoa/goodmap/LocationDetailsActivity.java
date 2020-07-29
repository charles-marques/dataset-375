package com.softcocoa.goodmap;

import greendroid.graphics.drawable.ActionBarDrawable;
import greendroid.widget.ActionBarItem;
import greendroid.widget.NormalActionBarItem;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.softcocoa.base.LinkWebViewActivity;
import com.softcocoa.db.DatabaseHelper;

public class LocationDetailsActivity extends LinkWebViewActivity {

	protected String locationId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addActionBarItem(
				getActionBar().newActionBarItem(NormalActionBarItem.class)
				.setDrawable(new ActionBarDrawable(this, R.drawable.ic_actionbar_directions)), R.id.actionbar_direction);
		addActionBarItem(ActionBarItem.Type.Share, R.id.actionbar_share);
		
		Log.i(C.TAG, "url:"+url);
		locationId = url.substring(url.indexOf("oid=")+4);
	}

	@Override
	public boolean onHandleActionBarItemClick(ActionBarItem item, int position) {
		Intent intent = null;
		switch (item.getItemId()) {
			case R.id.actionbar_direction:
				intent = new Intent(LocationDetailsActivity.this, MainActivity.class);
				intent.putExtra(MainActivity.EXTRA_LOCATION_ID, locationId);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				LocationDetailsActivity.this.startActivity(intent);
				break;
	
			case R.id.actionbar_share:
				DatabaseHelper dbh = new DatabaseHelper(LocationDetailsActivity.this, C.PACKAGE_NAME, C.DB_NAME);
				dbh.openDataBase();
				intent = new Intent(Intent.ACTION_SEND);
				intent.setType("text/plain");
				intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.location_share_title));
			    intent.putExtra(Intent.EXTRA_TEXT, dbh.getLocationName(locationId) + " - " +url);
		    	startActivity(Intent.createChooser(intent, getString(R.string.dialog_share_location_title)));
		    	dbh.close();
				break;
				
			default:
				break;
		}
		return super.onHandleActionBarItemClick(item, position);
	}

	
	
}
