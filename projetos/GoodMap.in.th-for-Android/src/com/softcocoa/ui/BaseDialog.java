package com.softcocoa.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;

import com.softcocoa.goodmap.R;

public abstract class BaseDialog implements DialogInterface.OnClickListener {
	
	public interface OnDismissListener {
		public void onDialogDismissed(BaseDialog dialog);
	}

	protected AlertDialog.Builder m_Builder;
	protected Context m_Context;
	protected OnDismissListener m_DismissListener;

	public int RequestCode = 0;
	public String titleText = "";
	public String acceptText = "OK";
	public String cancelText = "Cancel";
	public Drawable icon = null;
	public boolean DidAccept = false;

	protected BaseDialog(Context context, int requestCode,
			OnDismissListener dismissListener) {
		this.m_Builder = new AlertDialog.Builder(context);
		this.m_Context = context;
		this.m_DismissListener = dismissListener;
		this.RequestCode = requestCode;
	}

	protected void prepareDialog() {
		this.acceptText = this.m_Context.getString(R.string.dialog_button_ok);
		this.cancelText = this.m_Context.getString(R.string.dialog_button_cancel);
		
		this.m_Builder.setTitle(this.titleText);
		if (icon != null)
			this.m_Builder.setIcon(icon	);
		this.m_Builder.setPositiveButton(acceptText, this);
		this.m_Builder.setNegativeButton(cancelText, this);
	}

	public void show() {
		this.prepareDialog();
		AlertDialog dialog = this.m_Builder.create();
		this.onCreate(dialog);
		dialog.show();
	}

	protected void onCreate(AlertDialog dialog) {
	}

	@Override
	public void onClick(DialogInterface dialog, int buttonId) {
		switch (buttonId) {
			case DialogInterface.BUTTON_POSITIVE:
				this.DidAccept = true;
			case DialogInterface.BUTTON_NEGATIVE: {
				dialog.dismiss();
				if (this.m_DismissListener != null) {
					this.m_DismissListener.onDialogDismissed(this);
				}
			}
				break;
			default:
				break;
		}
	}
}
