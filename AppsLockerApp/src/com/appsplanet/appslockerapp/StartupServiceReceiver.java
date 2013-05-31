package com.appsplanet.appslockerapp;

import com.appsplanet.appslockerapp.service.DetectorService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class StartupServiceReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d("Detector",
				"Auto Start "
						+ AppLockerPreference.getInstance(context)
								.isAutoStart());
		Log.d("Detector",
				"ACTION_BOOT_COMPLETED "
						+ intent.getAction().equals(
								Intent.ACTION_BOOT_COMPLETED));
		
		if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
			if (AppLockerPreference.getInstance(context).isAutoStart()) {
				if (AppLockerPreference.getInstance(context).isServiceEnabled()) {
					context.startService(new Intent(context,
							DetectorService.class));
				} else {
					AppLockerPreference.getInstance(context)
							.saveServiceEnabled(false);
				}
			}
			return;
		} else if (AppLockerPreference.getInstance(context).isServiceEnabled()) {
			context.startService(new Intent(context, DetectorService.class));
		}
	}
}