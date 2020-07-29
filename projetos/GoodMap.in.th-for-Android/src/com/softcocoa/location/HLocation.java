package com.softcocoa.location;

import android.location.Location;

import com.google.android.maps.GeoPoint;

public class HLocation {
	
	public static GeoPoint createGeoPoint(Location location) {
		double latitude = location.getLatitude()*1E6;
		double longitude = location.getLongitude()*1E6;
		return new GeoPoint((int) latitude, (int) longitude);
	}

	public static GeoPoint createGeoPoint(double latitude, double longitude) {
		latitude *= 1E6;
		longitude *= 1E6;
		return new GeoPoint((int) latitude, (int) longitude);
	}
	
	public static double convertFromGeoPointE6(int e6Value) {
		return e6Value/1000000F;
	}
}
