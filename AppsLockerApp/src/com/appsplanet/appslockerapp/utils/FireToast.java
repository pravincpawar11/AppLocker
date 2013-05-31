package com.appsplanet.appslockerapp.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class FireToast {

	public static void makeToast(Context context,String text){
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
		
	}
	
}
