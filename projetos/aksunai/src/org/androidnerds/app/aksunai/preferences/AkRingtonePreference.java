/*
 * Copyright (C) 2009  AndroidNerds.org
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.androidnerds.app.aksunai.preferences;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.preference.RingtonePreference;
import android.util.AttributeSet;
import android.util.Log;

public class AkRingtonePreference extends RingtonePreference {

	private long mProviderId;
	
	public AkRingtonePreference(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		Intent intent = ((Activity) context).getIntent();
		mProviderId = intent.getLongExtra("providerId", -1);
		
		if (mProviderId < 0) {
			Log.e("Aksunai", "RingtonePreference needs a provider id.");
		}
	}
	
	protected Uri onRestoreRingtone() {
		String ringtone = this.getSharedPreferences().getString("notification-ringtone", "");
		Uri result = Uri.parse(ringtone);
		
		return result;
	}
	
	protected void onSaveRingtone(Uri ringtone) {
		getEditor().putString("notification-ringtone", ringtone.toString()).commit();
	}
}
