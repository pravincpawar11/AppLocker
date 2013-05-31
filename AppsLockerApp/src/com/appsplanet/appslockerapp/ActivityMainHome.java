package com.appsplanet.appslockerapp;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ToggleButton;

import com.appsplanet.appslockerapp.GridViewAdapter.GridViewAdapter;
import com.appsplanet.appslockerapp.password.ActivityPasswordChange;
import com.appsplanet.appslockerapp.service.DetectorService;
import com.appsplanet.appslockerapp.utils.Constants;
import com.appsplanet.appslockerapp.utils.FireToast;

public class ActivityMainHome extends Activity implements OnClickListener,
		OnItemClickListener, OnCheckedChangeListener {

	private Button btnSettings;
	private Context context;
	private ProgressDialog loading;

	private ToggleButton tglLockOnOff;

	private GridView grdLockApplication;
	private GridView grdUnlockApplication;

	private ArrayList<ResolveInfo> lockApplication = new ArrayList<ResolveInfo>();
	private ArrayList<ResolveInfo> unlockApplication = new ArrayList<ResolveInfo>();

	private GridViewAdapter adapterUnlockApplication;
	private GridViewAdapter adapterLockApplication;

	LoadApplicationTask loadApplicationTask = null;
	private List<ResolveInfo> listAllApplication = null;
	private String[] listPrefApplication;

	private String TAG = "AppsLocker";
	EditText edtoldpwd, edtnewpwd, edtconfirmpwd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_main);
		context = this;
		initViews();
	}

	private void initViews() {
		btnSettings = (Button) findViewById(R.id.btnSettings);
		btnSettings.setVisibility(View.VISIBLE);

		grdLockApplication = (GridView) findViewById(R.id.grdviewlockApp);
		grdUnlockApplication = (GridView) findViewById(R.id.grdviewUnlockApp);

		grdLockApplication.setOnItemClickListener(this);
		grdUnlockApplication.setOnItemClickListener(this);

		adapterUnlockApplication = new GridViewAdapter(getApplicationContext());
		adapterLockApplication = new GridViewAdapter(getApplicationContext());

		tglLockOnOff = (ToggleButton) findViewById(R.id.tglLockOnOff);
		tglLockOnOff.setOnCheckedChangeListener(this);

		loadUnlockApplication();
		checkServiceEnadbled();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		checkServiceEnadbled();

	}

	public void checkServiceEnadbled() {
		Log.d(TAG,
				"checkServiceEnadbled "
						+ AppLockerPreference.getInstance(context)
								.isServiceEnabled());
		if (AppLockerPreference.getInstance(context).isServiceEnabled()) {

			tglLockOnOff.setChecked(true);
		} else {
			tglLockOnOff.setChecked(false);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnHome:
			finish();
			break;
		case R.id.btnSetPassword:
//			startActivity(new Intent(ActivityMainHome.this,
//					ActivityChangePassword.class));
			
			startActivity(new Intent(ActivityMainHome.this,
					ActivityPasswordChange.class));
			
			break;

		case R.id.btnSettings:
			startActivity(new Intent(ActivityMainHome.this,
					ActivitySetting.class));
		default:
			break;
		}
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		Log.d(TAG, "Tag The toggle is enabled " + isChecked);
		if (isChecked) {
			// The toggle is enabled
			Log.d(TAG, "Tag The toggle is enabled " + isChecked);

			AppLockerPreference.getInstance(context).saveServiceEnabled(
					isChecked);
			Intent startService = new Intent(this, DetectorService.class);
			this.startService(startService);
		} else {
			// The toggle is disabled
			Log.d(TAG, "Tag The toggle is enabled " + isChecked);
			AppLockerPreference.getInstance(context).saveServiceEnabled(
					isChecked);
			this.stopService(new Intent(this, DetectorService.class));
		}
	}

	public class LoadApplicationTask extends AsyncTask<Void, Void, Boolean> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			loading = new ProgressDialog(ActivityMainHome.this);
			loading.setTitle("Please wait");
			loading.setMessage("Gathering application... ");
			loading.show();
		}

		protected Boolean doInBackground(Void... params) {

			Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
			mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

			Boolean included = false;

			listAllApplication = getPackageManager().queryIntentActivities(
					mainIntent, 0);
			int length = listAllApplication.size();

			listPrefApplication = AppLockerPreference.getInstance(
					ActivityMainHome.this).getApplicationList();
			int len = listPrefApplication.length;

			Log.d(TAG, "async len " + len);

			for (int i = 0; i < length; i++) {

				ResolveInfo info = listAllApplication.get(i);
				String strChkApp = info.activityInfo.packageName;

				for (int j = 0; j < len; j++) {

					if (strChkApp.equals(listPrefApplication[j])
					// || strChkApp
					// .equals(Constants.DEFAULT_SETTING_APPLICATION_NAME)
					// || strChkApp
					// .equals(Constants.DEFAULT_APPSLOCKER_APPLICATION_NAME)
					) {

						included = true;
						break;
					}

				}
				if (included) {
					lockApplication.add(info);
				} else {
					unlockApplication.add(info);
				}
				included = false;
			}

			return true;
		}

		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);

			if (loading.isShowing()) {
				loading.dismiss();
			}
			if (result) {
				
				if (unlockApplication != null && unlockApplication.size() > 0) {
					adapterUnlockApplication.setData(unlockApplication);
					grdUnlockApplication.setAdapter(adapterUnlockApplication);
				}

				if (lockApplication != null && lockApplication.size() > 0) {
					adapterLockApplication.setData(lockApplication);
					grdLockApplication.setAdapter(adapterLockApplication);
				}
			}

		}

	}

	private void loadUnlockApplication() {

		unlockApplication.clear();
		lockApplication.clear();

		loadApplicationTask = new LoadApplicationTask();
		loadApplicationTask.execute();

	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view,
			int position, long arg3) {

		switch (adapterView.getId()) {

		case R.id.grdviewUnlockApp:

			try {

				String[] dontblockApps = getResources().getStringArray(
						R.array.dont_block_app_list);

				String[] listPrefApplicationNew = AppLockerPreference
						.getInstance(ActivityMainHome.this)
						.getApplicationList();

				ResolveInfo info = unlockApplication.get(position);

				String selAppForLock = info.activityInfo.packageName;

				Boolean dontBlock = false;

				for (String s : dontblockApps) {

					if (selAppForLock.equals(s)) {

						dontBlock = true;
						break;
					}
				}

				if (dontBlock) {
					FireToast
							.makeToast(ActivityMainHome.this,
									"Sorry..!!! You can't Block this application in this version");
					break;
				}

				int len;

				// if (listPrefApplicationNew.length == 1) {
				// len = listPrefApplicationNew.length + 2;
				// } else {
				// len = listPrefApplicationNew.length + 1;
				// }

				if (listPrefApplicationNew.length == 1) {
					len = listPrefApplicationNew.length+1;
				} else {
					len = listPrefApplicationNew.length + 1;
				}

				Log.d("Appslocker", "unlock listPrefApplicationNew len " + len);

				String[] arr = new String[len];
				Log.d("Appslocker", "unlock arr.length " + arr.length);

				// arr[0] = Constants.DEFAULT_SETTING_APPLICATION_NAME;
				// arr[1] = Constants.DEFAULT_APPSLOCKER_APPLICATION_NAME;
				// for (int i = 2; i < len - 1; i++) {

				for (int i = 0; i < len - 1; i++) {
					arr[i] = listPrefApplicationNew[i];
				}

				arr[len - 1] = selAppForLock;

				Log.d("Appslocker", "selected unlock app packageName "
						+ info.activityInfo.packageName);
				Log.d("Appslocker", "selected unlock app Name"
						+ info.activityInfo.name);

				// arr[len - 1] =
				// info.loadLabel(getPackageManager()).toString();

				AppLockerPreference.getInstance(this).saveApplicationList(arr);

				len = 0;
				arr = null;
				listPrefApplicationNew = null;

				loadUnlockApplication();

			} catch (Exception e) {
				e.printStackTrace();
			}

			break;

		case R.id.grdviewlockApp:

			try {

				String[] listPrefApplicationNew = AppLockerPreference
						.getInstance(ActivityMainHome.this)
						.getApplicationList();

				ResolveInfo info = lockApplication.get(position);

				List<String> arrayList = new ArrayList<String>(
						listPrefApplicationNew.length);

				String strChkApp = info.activityInfo.packageName;

				Log.d("Appslocker", "selected lock app packageName "
						+ info.activityInfo.packageName);
				Log.d("Appslocker", "selected lock app Name"
						+ info.activityInfo.name);

				Log.d("Appslocker", "listPrefApplicationNew len "
						+ listPrefApplicationNew.length);

				// if (strChkApp
				// .equals(Constants.DEFAULT_SETTING_APPLICATION_NAME)
				// || strChkApp
				// .equals(Constants.DEFAULT_APPSLOCKER_APPLICATION_NAME)) {
				//
				// FireToast.makeToast(ActivityMainHome.this,
				// "Sorry..!!! You can't unlock this application");
				// arrayList = null;
				// listPrefApplicationNew = null;
				// break;
				// }

				for (String s : listPrefApplicationNew) {
					Log.d("Appslocker", "app name " + s);
					if (!strChkApp.equals(s))
						arrayList.add(s);
				}
				Log.d("Appslocker", "arrayList len " + arrayList.size());

				AppLockerPreference.getInstance(this).saveApplicationList(
						arrayList.toArray(new String[0]));

				arrayList = null;
				listPrefApplicationNew = null;

				loadUnlockApplication();

			} catch (Exception e) {
				e.printStackTrace();
			}

			break;

		default:
			break;
		}

	}

}
