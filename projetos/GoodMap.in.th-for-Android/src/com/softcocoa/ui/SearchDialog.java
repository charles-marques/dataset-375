package com.softcocoa.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.softcocoa.goodmap.R;

public class SearchDialog extends BaseDialog {

	public String text;
	
	protected EditText ratingTxt;
	
	public SearchDialog(Context context, int requestCode, OnDismissListener dismissListener) {
		super(context, requestCode, dismissListener);
	}

	@Override
	protected void onCreate(AlertDialog dialog) {
		super.onCreate(dialog);
		LayoutInflater inflator = (LayoutInflater)this.m_Context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflator.inflate(R.layout.dialog_search, null);

		ratingTxt = (EditText)view.findViewById(R.id.search_txt);
//		ratingTxt.setOnFocusChangeListener(this);

		dialog.setView(view, 0, 0, 0, 0);
	}

	@Override
	public void onClick(DialogInterface dialog, int buttonId) {
		this.text = ratingTxt.getText().toString();
		super.onClick(dialog, buttonId);
	}
	
}
