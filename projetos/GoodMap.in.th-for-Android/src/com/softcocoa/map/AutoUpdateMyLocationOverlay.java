package com.softcocoa.map;

import android.content.Context;
import android.location.Location;

import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.softcocoa.location.HLocation;

public class AutoUpdateMyLocationOverlay extends MyLocationOverlay {

	protected MapView mapView;
	protected boolean animateOnFirstFix;

	public AutoUpdateMyLocationOverlay(Context context, MapView mapView, boolean animateOnFirstFix) {
		super(context, mapView);
		this.mapView = mapView;
		this.animateOnFirstFix = animateOnFirstFix;
	}

	@Override
	public synchronized void onLocationChanged(Location location) {
		super.onLocationChanged(location);
		if(animateOnFirstFix) {
			mapView.getController().animateTo(HLocation.createGeoPoint(location));
			animateOnFirstFix = false;
		}

	}

}
