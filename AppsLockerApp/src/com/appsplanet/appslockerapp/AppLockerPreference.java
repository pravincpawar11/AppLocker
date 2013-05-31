package com.appsplanet.appslockerapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.PreferenceManager;

public class AppLockerPreference implements OnSharedPreferenceChangeListener {

	private static final String PREF_SERVICE_ENABLED = "service_enabled";
	private static final String PREF_APPLICATION_LIST = "application_list";
	private static final String PREF_AUTO_START = "start_service_after_boot";
	private static final String PREF_PASSWORD = "password";
	public static String SHREAD_PREF_VIBRATE = "vibrate";
	public static final String DEFAULT_PASSWORD = "0000";
	private SharedPreferences mPref;
	private static AppLockerPreference mInstance;
	private boolean mServiceEnabled, mAutoStart, mVibrate;
	private String[] mApplicationList;
	private String mPassword;
	private int mRelockTimeout;

	/**
	 * Section for singleton pattern
	 */

	public AppLockerPreference(Context context) {
		mPref = PreferenceManager.getDefaultSharedPreferences(context);
		mPref.registerOnSharedPreferenceChangeListener(this);
		reloadPreferences();
	}

	private void reloadPreferences() {

		mServiceEnabled = mPref.getBoolean(PREF_SERVICE_ENABLED, false);
		mApplicationList = mPref.getString(PREF_APPLICATION_LIST, "")
				.split(";");
		mAutoStart = mPref.getBoolean(PREF_AUTO_START, false);
		mPassword = mPref.getString(PREF_PASSWORD, DEFAULT_PASSWORD);
		mVibrate = mPref.getBoolean(SHREAD_PREF_VIBRATE, true);
		if (mPref.getBoolean("relock_policy", true)) {
			try {
				mRelockTimeout = Integer.parseInt(mPref.getString(
						"relock_timeout", "-1"));
			} catch (Exception e) {
				mRelockTimeout = -1;
			}
		} else {
			mRelockTimeout = -1;
		}
	}

	public boolean isAutoStart() {
		return mAutoStart;
	}

	public void saveAutoStart(Boolean autoStart) {
		mAutoStart = autoStart;
		mPref.edit().putBoolean(PREF_AUTO_START, mAutoStart).commit();
	}

	public boolean isServiceEnabled() {
		return mServiceEnabled;
	}

	public Boolean isVibrate() {
		return mVibrate;
	}

	public void saveVibrate(Boolean vibrate) {
		mVibrate = vibrate;
		mPref.edit().putBoolean(SHREAD_PREF_VIBRATE, mVibrate).commit();
	}

	public void saveServiceEnabled(boolean serviceEnabled) {
		mServiceEnabled = serviceEnabled;
		mPref.edit().putBoolean(PREF_SERVICE_ENABLED, mServiceEnabled).commit();
	}

	public String[] getApplicationList() {
		return mApplicationList;
	}

	public void saveApplicationList(String[] applicationList) {
		mApplicationList = applicationList;
		String combined = "";
		for (int i = 0; i < mApplicationList.length; i++) {
			combined = combined + mApplicationList[i] + ";";
		}
		mPref.edit().putString(PREF_APPLICATION_LIST, combined).commit();
	}

	public static AppLockerPreference getInstance(Context context) {
		return mInstance == null ? (mInstance = new AppLockerPreference(context))
				: mInstance;
	}

	public int getRelockTimeout() {
		return mRelockTimeout;
	}

	public  String getPassword() {
		return mPassword;
	}

	public void savePassword(String password) {
		mPassword = password;
		mPref.edit().putString(PREF_PASSWORD, password).commit();
	}

	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		reloadPreferences();
	}
}