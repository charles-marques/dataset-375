package cc.rainwave.android;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import cc.rainwave.android.listeners.HexadecimalKeyListener;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

public class RainwavePreferenceActivity extends PreferenceActivity {

		public void onCreate(Bundle icicle) {
			super.onCreate(icicle);
			addPreferencesFromResource(R.xml.preferences);
			setupUI();
		}
		
		private void setupUI() {
		    EditTextPreference key = (EditTextPreference) findPreference(Rainwave.PREFS_KEY);
		    EditText field = key.getEditText();
		    field.setKeyListener(new HexadecimalKeyListener());
		    
		    Preference qr = findPreference(Rainwave.PREF_IMPORT);
		    qr.setOnPreferenceClickListener(new OnPreferenceClickListener() {
				@Override
				public boolean onPreferenceClick(Preference p) {
					IntentIntegrator.initiateScan(RainwavePreferenceActivity.this);
					return true;
				}
		    });
		    
		    Preference clear = findPreference(Rainwave.PREF_CLEAR_PREFERENCES);
		    clear.setOnPreferenceClickListener(new OnPreferenceClickListener() {
				@Override
				public boolean onPreferenceClick(Preference p) {
					Rainwave.clearPreferences(RainwavePreferenceActivity.this);
					return true;
				}
		    });
		}
		
		public void onActivityResult(int request, int result, Intent data) {
			IntentResult ir = IntentIntegrator.parseActivityResult(request, result, data);
			if(ir == null) return;
			
			String raw = ir.getContents();
			if(raw == null) return;
			
			Uri uri = Uri.parse(raw);
			boolean ok = Rainwave.setPreferencesFromUri(this, uri);
			
			if(!ok) {
				Rainwave.showError(this, R.string.msg_invalidUrl);
			}
		}
	
		public void onListItemClick(ListView list, View v, int position, long id) {
			Log.d("PreferencesActivity", "onListItemClick()");
		}
}
