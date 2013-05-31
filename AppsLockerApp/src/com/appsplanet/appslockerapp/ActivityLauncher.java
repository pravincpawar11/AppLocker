package com.appsplanet.appslockerapp;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class ActivityLauncher extends Activity implements OnClickListener {

	
	private Context context;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);
		context = this;
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imgLock:
			startActivity(new Intent(context, ActivityMainHome.class));
			break;
		case R.id.btnHome:
			finish();
			break;
		default:
			break;
		}
	}

}
