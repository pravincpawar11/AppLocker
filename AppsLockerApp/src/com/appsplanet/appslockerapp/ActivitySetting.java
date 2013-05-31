package com.appsplanet.appslockerapp;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class ActivitySetting extends PreferenceActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.addPreferencesFromResource(R.xml.preference_setting);
		PreferenceManager.getDefaultSharedPreferences(this)
				.registerOnSharedPreferenceChangeListener(
						serviceEnabledListener);
	}

	OnSharedPreferenceChangeListener serviceEnabledListener = new OnSharedPreferenceChangeListener() {
		public void onSharedPreferenceChanged(
				SharedPreferences sharedPreferences, String key) {
			if (key.equals("start_service_after_boot")) {
				if (sharedPreferences.getBoolean(key, false)) {
					 AppLockerPreference.getInstance(ActivitySetting.this).saveAutoStart(true);
				} else {
					 AppLockerPreference.getInstance(ActivitySetting.this).saveAutoStart(false);

				}
			}
		}
	};
}
