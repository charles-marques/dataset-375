package com.softcocoa.goodmap;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;

import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import com.readystatesoftware.mapviewballoons.BalloonItemizedOverlay;
import com.readystatesoftware.mapviewballoons.BalloonOverlayView.OnDirectionButtonPressedListener;

public class MyItemizedOverlay extends BalloonItemizedOverlay<OverlayItem> {

	private ArrayList<OverlayItem> m_overlays = new ArrayList<OverlayItem>();
	private Context c;

	public MyItemizedOverlay(Drawable defaultMarker, MapView mapView, OnDirectionButtonPressedListener listener) {
		super(boundCenterBottom(defaultMarker), mapView, listener);
		c = mapView.getContext();
	}

	public void addOverlay(OverlayItem overlay) {
		m_overlays.add(overlay);
		populate();
	}

	@Override
	protected OverlayItem createItem(int i) {
		return m_overlays.get(i);
	}

	@Override
	public int size() {
		return m_overlays.size();
	}

	@Override
	protected boolean onBalloonTap(int index, OverlayItem item) {
		Intent intent = new Intent(c, LocationDetailsActivity.class);
		intent.putExtra(LocationDetailsActivity.EXTRA_URL,
				"http://goodmap.in.th/map/showorgdetail.php?oid="+item.getSnippet().substring(item.getSnippet().indexOf(".")+1));
		c.startActivity(intent);
		return true;
	}

}