package com.appsplanet.appslockerapp.password;

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
import android.widget.TextView;
import android.widget.Toast;

import com.appsplanet.appslockerapp.AppLockerPreference;
import com.appsplanet.appslockerapp.R;
import com.appsplanet.appslockerapp.utils.Constants;
import com.appsplanet.appslockerapp.utils.FireToast;

public class ActivityPasswordChange extends Activity implements OnClickListener {
	private Button btn_passwd1, btn_passwd2, btn_passwd3, btn_passwd4;
	private Button btn_num1, btn_num2, btn_num3, btn_num4, btn_num5, btn_num6,
			btn_num7, btn_num8, btn_num9, btn_num0, btn_hint, btn_del, btn_hax,
			btn_doller, btn_star;
	boolean galleryFlag = false;
	ArrayList<String> pathList = new ArrayList<String>();
	String num1 = "", num2 = "", num3 = "", num4 = "";

	private TextView txtPleaseEnterPassword;

	private enum CHAGEPASSWORD_TYPE {
		OLD, NEW, CONFIRM
	};

	private String oldPassword, newPassword, confirmPassword;

	private CHAGEPASSWORD_TYPE currentChangePasswordType;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
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

		txtPleaseEnterPassword = (TextView) findViewById(R.id.txtPleaseEnterPassword);
		txtPleaseEnterPassword.setText("Enter old password");

		btn_num0.setOnClickListener(this);
		btn_num1.setOnClickListener(this);
		btn_num2.setOnClickListener(this);
		btn_num3.setOnClickListener(this);
		btn_num4.setOnClickListener(this);
		btn_num5.setOnClickListener(this);
		btn_num6.setOnClickListener(this);
		btn_num7.setOnClickListener(this);
		btn_num8.setOnClickListener(this);
		btn_num9.setOnClickListener(this);
		btn_hint.setOnClickListener(this);
		btn_del.setOnClickListener(this);
		btn_hax.setOnClickListener(this);
		btn_doller.setOnClickListener(this);
		btn_star.setOnClickListener(this);
		Log.d("test", "GalleryFlagL: " + galleryFlag);

		currentChangePasswordType = CHAGEPASSWORD_TYPE.OLD;
	}

	@Override
	public void onClick(View v) {

		try {
			if (AppLockerPreference.getInstance(ActivityPasswordChange.this)
					.isVibrate()) {
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
			FireToast.makeToast(ActivityPasswordChange.this,
					"Initial password is 0000");
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

			String pass1 = num1;
			String pass2 = num2;
			String pass3 = num3;
			String pass4 = num4;

			String pass = pass1 + pass2 + pass3 + pass4;
			AppLockerPreference appLockerPreference = new AppLockerPreference(
					ActivityPasswordChange.this);

			if (currentChangePasswordType == CHAGEPASSWORD_TYPE.OLD
					&& pass.equalsIgnoreCase(appLockerPreference.getPassword())) { // OLD
				// PASSWORD
				// AND
				// NEW
				// PASSWORD
				// ARE
				// MATCH

				btn_passwd1.setText("");
				btn_passwd2.setText("");
				btn_passwd3.setText("");
				btn_passwd4.setText("");

				num1 = "";
				num2 = "";
				num3 = "";
				num4 = "";

				oldPassword = pass;

				txtPleaseEnterPassword.setText("Enter new password");

				currentChangePasswordType = CHAGEPASSWORD_TYPE.NEW;

			} else if (currentChangePasswordType == CHAGEPASSWORD_TYPE.NEW) {
				btn_passwd1.setText("");
				btn_passwd2.setText("");
				btn_passwd3.setText("");
				btn_passwd4.setText("");

				num1 = "";
				num2 = "";
				num3 = "";
				num4 = "";

				newPassword = pass;

				txtPleaseEnterPassword.setText("Confirm password");

				currentChangePasswordType = CHAGEPASSWORD_TYPE.CONFIRM;

			} else if (currentChangePasswordType == CHAGEPASSWORD_TYPE.CONFIRM) {
				confirmPassword = pass;
				if (newPassword.equalsIgnoreCase(confirmPassword)) {
					Toast.makeText(getApplicationContext(), "Password changed!",
							Toast.LENGTH_LONG).show();
					appLockerPreference.savePassword(pass);
					
					finish();
				}else{
						Toast.makeText(getApplicationContext(), "New and Confirm not matched!",
								Toast.LENGTH_LONG).show();
				}
			}else{
				Toast.makeText(getApplicationContext(), "Password not matched!",
						Toast.LENGTH_LONG).show();
			}
		}
	}

}
