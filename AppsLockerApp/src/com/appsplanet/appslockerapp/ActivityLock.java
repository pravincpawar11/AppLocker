package com.appsplanet.appslockerapp;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ActivityLock extends Activity implements OnClickListener {

	public static final String BlockedPackageName = "locked package name";
	public static final String BlockedActivityName = "locked activity name";
	public static final String ACTION_APPLICATION_PASSED = "com.appsplanet.appslockerapp.applicationpassedtest";
	public static final String EXTRA_PACKAGE_NAME = "com.appsplanet.appslockerapp.extra.package.name";

	private Button btn_passwd1, btn_passwd2, btn_passwd3, btn_passwd4;
	private Button btn_num1, btn_num2, btn_num3, btn_num4, btn_num5, btn_num6,
			btn_num7, btn_num8, btn_num9, btn_num0, btn_hint, btn_del, btn_hax,
			btn_doller, btn_star;
	boolean galleryFlag = false;
	ArrayList<String> pathList = new ArrayList<String>();
	String num1 = "", num2 = "", num3 = "", num4 = "";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lock_screen);

		btn_passwd1 = (Button) findViewById(R.id.btn_passwd1);
		btn_passwd2 = (Button) findViewById(R.id.btn_passwd2);
		btn_passwd3 = (Button) findViewById(R.id.btn_passwd3);
		btn_passwd4 = (Button) findViewById(R.id.btn_passwd4);

		btn_num0 = (Button) findViewById(R.id.btn_num0);
		btn_num1 = (Button) findViewById(R.id.btn_num1);
		btn_num2 = (Button) findViewById(R.id.btn_num2);
		btn_num3 = (Button) findViewById(R.id.btn_num3);
		btn_num4 = (Button) findViewById(R.id.btn_num4);
		btn_num5 = (Button) findViewById(R.id.btn_num5);
		btn_num6 = (Button) findViewById(R.id.btn_num6);
		btn_num7 = (Button) findViewById(R.id.btn_num7);
		btn_num8 = (Button) findViewById(R.id.btn_num8);
		btn_num9 = (Button) findViewById(R.id.btn_num9);
		btn_hax = (Button) findViewById(R.id.btn_hax);
		btn_doller = (Button) findViewById(R.id.btn_doller);
		btn_star = (Button) findViewById(R.id.btn_star);

		btn_hint = (Button) findViewById(R.id.btn_hint);
		btn_del = (Button) findViewById(R.id.btn_del);

		btn_num0.setOnClickListener(ActivityLock.this);
		btn_num1.setOnClickListener(ActivityLock.this);
		btn_num2.setOnClickListener(ActivityLock.this);
		btn_num3.setOnClickListener(ActivityLock.this);
		btn_num4.setOnClickListener(ActivityLock.this);
		btn_num5.setOnClickListener(ActivityLock.this);
		btn_num6.setOnClickListener(ActivityLock.this);
		btn_num7.setOnClickListener(ActivityLock.this);
		btn_num8.setOnClickListener(ActivityLock.this);
		btn_num9.setOnClickListener(ActivityLock.this);
		btn_hint.setOnClickListener(ActivityLock.this);
		btn_del.setOnClickListener(ActivityLock.this);
		btn_hax.setOnClickListener(this);
		btn_doller.setOnClickListener(this);
		btn_star.setOnClickListener(this);

		Log.d("test", "GalleryFlagL: " + galleryFlag);

	}

	@Override
	public void onClick(View v) {

		try {
			if (AppLockerPreference.getInstance(ActivityLock.this).isVibrate()) {
				Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
				vib.vibrate(500);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		switch (v.getId()) {

		case R.id.btn_num0:

			setPassword(btn_num0.getText().toString());

			break;

		case R.id.btn_num1:
			setPassword(btn_num1.getText().toString());
			break;

		case R.id.btn_num2:
			setPassword(btn_num2.getText().toString());
			break;

		case R.id.btn_num3:
			setPassword(btn_num3.getText().toString());
			break;

		case R.id.btn_num4:
			setPassword(btn_num4.getText().toString());
			break;

		case R.id.btn_num5:
			setPassword(btn_num5.getText().toString());
			break;

		case R.id.btn_num6:
			setPassword(btn_num6.getText().toString());
			break;

		case R.id.btn_num7:
			setPassword(btn_num7.getText().toString());
			break;

		case R.id.btn_num8:
			setPassword(btn_num8.getText().toString());
			break;

		case R.id.btn_num9:
			setPassword(btn_num9.getText().toString());
			break;

		case R.id.btn_hax:
			setPassword(btn_hax.getText().toString());
			break;
		case R.id.btn_doller:
			setPassword(btn_doller.getText().toString());
			break;
		case R.id.btn_star:
			setPassword(btn_star.getText().toString());
			break;
		case R.id.btn_hint:
			Toast.makeText(ActivityLock.this, "Initial password is 0000",
					Toast.LENGTH_LONG).show();
			break;

		case R.id.btn_del:
			if (!btn_passwd4.getText().toString().equalsIgnoreCase("")) {
				btn_passwd4.setText("");
			} else if (!btn_passwd3.getText().toString().equalsIgnoreCase("")) {
				btn_passwd3.setText("");
			} else if (!btn_passwd2.getText().toString().equalsIgnoreCase("")) {
				btn_passwd2.setText("");
			} else if (!btn_passwd1.getText().toString().equalsIgnoreCase("")) {
				btn_passwd1.setText("");
			}
			break;

		default:
			break;
		}

	}

	void setPassword(String num) {
		if (btn_passwd1.getText().toString().equalsIgnoreCase("")) {
			num1 = num;
			btn_passwd1.setText("*");
		} else if (btn_passwd2.getText().toString().equalsIgnoreCase("")) {
			num2 = num;
			btn_passwd2.setText("*");
		} else if (btn_passwd3.getText().toString().equalsIgnoreCase("")) {
			num3 = num;
			btn_passwd3.setText("*");
		} else if (btn_passwd4.getText().toString().equalsIgnoreCase("")) {
			num4 = num;
			btn_passwd4.setText("*");
		}

		if (!btn_passwd4.getText().toString().equalsIgnoreCase("")) {
			Log.d("test", "Password");
			// String pass1 = btn_passwd1.getText().toString();
			// String pass2 = btn_passwd2.getText().toString();
			// String pass3 = btn_passwd3.getText().toString();
			// String pass4 = btn_passwd4.getText().toString();

			String pass1 = num1;
			String pass2 = num2;
			String pass3 = num3;
			String pass4 = num4;

			String pass = pass1 + pass2 + pass3 + pass4;

			String password = AppLockerPreference
					.getInstance(ActivityLock.this).getPassword();

			if (pass.equalsIgnoreCase(password)) {

				// Intent intent = new Intent(ActivityLock.this,
				// ActivityHome.class)
				// .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				// finish();
				// startActivity(intent);

				test_passed();

			} else {
				Toast.makeText(ActivityLock.this, "Wrong Password!",
						Toast.LENGTH_SHORT).show();
				btn_passwd1.setText("");
				btn_passwd2.setText("");
				btn_passwd3.setText("");
				btn_passwd4.setText("");

			}
		}

	}

	private void test_passed() {
		this.sendBroadcast(new Intent().setAction(ACTION_APPLICATION_PASSED)
				.putExtra(EXTRA_PACKAGE_NAME,
						getIntent().getStringExtra(BlockedPackageName)));
		finish();
	}

	@Override
	public void onBackPressed() {
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_HOME)
				.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		finish();
	}

}