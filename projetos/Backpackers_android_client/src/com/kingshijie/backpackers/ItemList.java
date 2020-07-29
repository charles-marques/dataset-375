package com.kingshijie.backpackers;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.kingshijie.backpackers.bean.Place;
import com.kingshijie.backpackers.map.Map;
import com.kingshijie.backpackers.util.SearchResultAdapter;

public class ItemList extends ListActivity {

	private Button show_in_map;
	private ArrayList<Place> places;
	private String _controller;
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.itemlist);
		Bundle bdl = getIntent().getExtras();
		places = bdl.getParcelableArrayList("GPOINT");
		_controller = bdl.getString("ctrl");

		show_in_map = (Button) findViewById(R.id.show_in_map);
		show_in_map.setOnClickListener(new goMap());

		// 使用自定义的adapter
		ListAdapter adapter = new SearchResultAdapter(this,
				android.R.layout.two_line_list_item, places);

		setListAdapter(adapter);

		//设置单击监听
		ListView lv = getListView();

		lv.setOnItemClickListener(new clickItem());

	}

	/**
	 * 单击某个条目的事件
	 * @author aaron
	 *
	 */
	private class clickItem implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			long itemId = places.get(position).getId();
			Intent intent = new Intent(ItemList.this, ItemActivity.class);
			Bundle bdl = new Bundle();
			bdl.putString("ctrl", _controller);
			bdl.putLong("item_id", itemId);
			intent.putExtras(bdl);
			startActivity(intent);
		}
		
	}
	/**
	 * 单击进入地图察看
	 * @author aaron
	 *
	 */
	private class goMap implements OnClickListener {

		@Override
		public void onClick(View v) {
			Intent intent = new Intent(ItemList.this, Map.class);
			intent.putExtras(getIntent().getExtras());
			startActivity(intent);
		}

	}

}
