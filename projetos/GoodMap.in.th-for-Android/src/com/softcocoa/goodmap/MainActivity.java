package com.softcocoa.goodmap;

import greendroid.app.GDMapActivity;
import greendroid.graphics.drawable.MapPinDrawable;
import greendroid.widget.ActionBar.Type;
import greendroid.widget.ActionBarItem;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.ci.geo.route.MapRouteOverlay;
import org.ci.geo.route.Road;
import org.ci.geo.route.RoadProvider;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.OverlayItem;
import com.readystatesoftware.mapviewballoons.BalloonOverlayView.OnDirectionButtonPressedListener;
import com.softcocoa.db.DatabaseHelper;
import com.softcocoa.location.HLocation;
import com.softcocoa.map.AutoUpdateMyLocationOverlay;
import com.softcocoa.ui.BaseDialog;
import com.softcocoa.ui.BaseDialog.OnDismissListener;
import com.softcocoa.ui.SearchDialog;

public class MainActivity extends GDMapActivity implements OnDirectionButtonPressedListener, OnDismissListener {
	
	public static final String EXTRA_LOCATION_ID = "EXTRA_LOCATION_ID";
	
	protected MapView mapView;
	protected MyLocationOverlay myLocationOverlay;
	protected TextView filterTxt;
	protected String filter;
	
	protected ProgressBar progressBar;
	
	protected SQLiteDatabase db;
	
	public static final int DIALOG_SEARCH = 0;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setActionBarContentView(R.layout.main);
        
        getActionBar().setType(Type.Dashboard);
        addActionBarItem(ActionBarItem.Type.LocateMyself, R.id.actionbar_locatemyself);
        addActionBarItem(ActionBarItem.Type.Info, R.id.actionbar_help);
        addActionBarItem(ActionBarItem.Type.Search, R.id.actionbar_search);
        
        DatabaseHelper dbh = new DatabaseHelper(this, C.PACKAGE_NAME, C.DB_NAME);
        try {
			dbh.createDataBase();
        } catch (IOException e) {
        	e.printStackTrace();
        } finally {
        	if (dbh != null);
        	dbh.close();
        }
        
        mapView = (MapView) findViewById(R.id.mapview);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        filterTxt = (TextView) findViewById(R.id.filter_txt);
        
        mapView.setBuiltInZoomControls(true);
        mapView.getController().setZoom(C.MAP_ZOOM_LEVEL_DEFAULT);
        
        myLocationOverlay = new AutoUpdateMyLocationOverlay(this, mapView, false);
        mapView.getOverlays().add(myLocationOverlay);
        
        filter = null;
        mapView.getController().animateTo(HLocation.createGeoPoint(13.75f, 100.56f));
        populatePois(null);
    }
    
	@Override
	protected void onPause() {
		myLocationOverlay.disableMyLocation();
		myLocationOverlay.disableCompass();
		super.onPause();
	}

	@Override
	protected void onResume() {
		myLocationOverlay.enableMyLocation();
		myLocationOverlay.enableCompass();
		super.onResume();
	}

	@Override
	public boolean onHandleActionBarItemClick(ActionBarItem item, int position) {
		switch (item.getItemId()) {
			case R.id.actionbar_locatemyself:
				GeoPoint point = myLocationOverlay.getMyLocation();
				if (point != null)
					mapView.getController().animateTo(point);
				break;
				
			case R.id.actionbar_help:
				startActivity(new Intent(MainActivity.this, AboutActivity.class));
				break;
				
			case R.id.actionbar_search:
				SearchDialog dialog = new SearchDialog(this, DIALOG_SEARCH, this);
				dialog.titleText = getString(R.string.dialog_search_title);
				dialog.icon = getResources().getDrawable(R.drawable.gd_action_bar_search);
				dialog.show();
				break;
	
			default:
				break;
		}
		return super.onHandleActionBarItemClick(item, position);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		if (!isDirectionShowed())
			menu.findItem(R.id.menu_clear_direction).setEnabled(false);
		else
			menu.findItem(R.id.menu_clear_direction).setEnabled(true);
		
		if (filter == null)
			menu.findItem(R.id.menu_clear_filter).setEnabled(false);
		else
			menu.findItem(R.id.menu_clear_filter).setEnabled(true);
		
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.menu_clear_direction:
				clearDirection();
				break;
			
			case R.id.menu_clear_filter:
				filterTxt.setVisibility(View.INVISIBLE);
				filter = null;
				clearPois();
				populatePois(null);
				break;
				
			default:
				break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onNewIntent(Intent newIntent) {
		super.onNewIntent(newIntent);
		if (newIntent.getStringExtra(EXTRA_LOCATION_ID) == null)
			return;
		Log.d(C.TAG, "onNewIntent: "+newIntent.getStringExtra(EXTRA_LOCATION_ID));
		DatabaseHelper dbh = new DatabaseHelper(this, C.PACKAGE_NAME, C.DB_NAME);
		try {
			dbh.openDataBase();
			GeoPoint point = dbh.getLocationPoint(newIntent.getStringExtra(EXTRA_LOCATION_ID));
			new MapRouteTask().execute(new GeoPoint[]{point});
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (dbh != null)
				dbh.close();
		}
	}

	protected void populatePois(String filter) {
		DatabaseHelper dbh = new DatabaseHelper(this, C.PACKAGE_NAME, C.DB_NAME);
		dbh.openDataBase();
		Cursor c = dbh.getAllPositions(filter);
		
		if(c == null) {
			dbh.close();
			return;
		}
		
		MyItemizedOverlay itemizedOverlay = new MyItemizedOverlay(
				new MapPinDrawable(getResources(), getResources().getColorStateList(R.color.mappin_pin), getResources().getColorStateList(R.color.mappin_dot)), mapView, this);
		itemizedOverlay.setBalloonBottomOffset(30);
		
		int idCol = c.getColumnIndex("id");
		int latitudeCol = c.getColumnIndex("latitude");
		int longitudeCol = c.getColumnIndex("longitude");
		int detailCol = c.getColumnIndex("detail");
		
		do {
			OverlayItem item = new OverlayItem(
					HLocation.createGeoPoint( Double.parseDouble(c.getString(latitudeCol)), Double.parseDouble(c.getString(longitudeCol)) ),
					c.getString(detailCol), getString(R.string.map_balloon_more_details)+c.getInt(idCol));	
			itemizedOverlay.addOverlay(item);
		} while (c.moveToNext());
		
		c.close();
		dbh.close();
		
		mapView.getOverlays().add(0, itemizedOverlay);
		mapView.invalidate();
	}
	
	@Override
	public void onDialogDismissed(BaseDialog dialog) {
		if (!dialog.DidAccept)
			return;
		
		switch (dialog.RequestCode) {
			case DIALOG_SEARCH:
				SearchDialog searchDialog = (SearchDialog) dialog;
				clearPois();
				filter = searchDialog.text;
				populatePois(filter);
				filterTxt.setText("Filter: "+filter);
				filterTxt.setVisibility(View.VISIBLE);
				break;
	
			default:
				break;
		}
	}
	
	protected void clearPois() {
		mapView.getOverlays().clear();
		mapView.getOverlays().add(myLocationOverlay);
		mapView.invalidate();
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	
	protected boolean isDirectionShowed() {
		return mapView.getOverlays().get(0).getClass() == MapRouteOverlay.class;
	}
	
	protected void clearDirection() {
		if (isDirectionShowed()) {
			mapView.getOverlays().remove(0);
			mapView.invalidate();
		}
	}

	@Override
	public void onDirectionButtonPressed(OverlayItem item) {
		Log.i(C.TAG, "direction!");
		new MapRouteTask().execute(new GeoPoint[]{item.getPoint()});
	}
	
	public class MapRouteTask extends AsyncTask<GeoPoint, Void, Void> {

		private Road mRoad;
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			Toast.makeText(MainActivity.this, R.string.toast_direction_request, Toast.LENGTH_SHORT).show();
			progressBar.setVisibility(View.VISIBLE);
		}

		@Override
		protected Void doInBackground(GeoPoint... points) {
			if (myLocationOverlay.getLastFix() == null) {
				mRoad = null;
				return null;
			}
			
			double fromLat = myLocationOverlay.getLastFix().getLatitude(), fromLon = myLocationOverlay.getLastFix().getLongitude();
			double toLat = HLocation.convertFromGeoPointE6(points[0].getLatitudeE6()), toLon = HLocation.convertFromGeoPointE6(points[0].getLongitudeE6());
			String url = RoadProvider
					.getUrl(fromLat, fromLon, toLat, toLon);
			InputStream is = getConnection(url);
			mRoad = RoadProvider.getRoute(is);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			if (mRoad == null || mRoad.mPoints.length == 0) {
				new AlertDialog.Builder(MainActivity.this)
					.setTitle(R.string.dialog_title_error)
					.setIcon(android.R.drawable.ic_dialog_alert)
					.setMessage(R.string.error_no_direction)
					.setPositiveButton(R.string.dialog_button_ok, null)
					.show();
				progressBar.setVisibility(View.INVISIBLE);
				return;
			}
			
			clearDirection();
			
			MapRouteOverlay routeOverlay = new MapRouteOverlay(mRoad, mapView);
			mapView.getOverlays().add(0, routeOverlay);
			mapView.invalidate();
			progressBar.setVisibility(View.INVISIBLE);
		}
		
		private InputStream getConnection(String url) {
			InputStream is = null;
			try {
				URLConnection conn = new URL(url).openConnection();
				is = conn.getInputStream();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return is;
		}
		
	}

}