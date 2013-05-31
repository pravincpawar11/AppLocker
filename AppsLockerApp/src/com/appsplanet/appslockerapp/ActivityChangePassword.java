package com.appsplanet.appslockerapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.appsplanet.appslockerapp.utils.FireToast;

public class ActivityChangePassword extends Activity implements OnClickListener {

	private TextView edtOldPassword, edtNewPassword, edtConfirmPassword;
	private Button btnOK, btnCancel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_change_password);

		edtOldPassword = (TextView) findViewById(R.id.edt_oldpassword);
		edtOldPassword.setOnClickListener(this);

		edtNewPassword = (TextView) findViewById(R.id.edt_newpassword);
		edtNewPassword.setOnClickListener(this);

		edtConfirmPassword = (TextView) findViewById(R.id.edt_confirmpassword);
		edtConfirmPassword.setOnClickListener(this);

		btnOK = (Button) findViewById(R.id.btn_ok);
		btnOK.setOnClickListener(this);

		btnCancel = (Button) findViewById(R.id.btn_cancel);
		btnCancel.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.edt_oldpassword:

			Intent intentOld = new Intent(ActivityChangePassword.this,
					ActivitySetPasswordScreen.class);
			intentOld.putExtra("resultCode", 1);
			startActivityForResult(intentOld, 1);
			break;

		case R.id.edt_newpassword:

			Intent intentNew = new Intent(ActivityChangePassword.this,
					ActivitySetPasswordScreen.class);
			intentNew.putExtra("resultCode", 2);
			startActivityForResult(intentNew, 2);

			break;
		case R.id.edt_confirmpassword:

			Intent intentConfire = new Intent(ActivityChangePassword.this,
					ActivitySetPasswordScreen.class);
			intentConfire.putExtra("resultCode", 3);
			startActivityForResult(intentConfire, 3);

			break;

		case R.id.btn_ok:
			if (edtOldPassword.getText().toString().length() != 4
					|| edtNewPassword.getText().toString().length() != 4
					|| edtConfirmPassword.getText().toString().length() != 4) {
				FireToast.makeToast(ActivityChangePassword.this,
						"Password length should be 4 digits!");
			}

			else {
				String password = AppLockerPreference.getInstance(
						ActivityChangePassword.this).getPassword();

				if (!edtOldPassword.getText().toString()
						.equalsIgnoreCase(password)) {

					edtOldPassword.setError("Wrong Old password!");

				} else if (edtNewPassword.getText().toString()
						.equalsIgnoreCase(password)) {
					edtNewPassword.setError("Please select another password!");
				} else if (edtNewPassword
						.getText()
						.toString()
						.equalsIgnoreCase(
								edtConfirmPassword.getText().toString())) {
					AppLockerPreference
							.getInstance(ActivityChangePassword.this)
							.savePassword(
									edtConfirmPassword.getText().toString());
					FireToast.makeToast(ActivityChangePassword.this,
							"Password saved.");
					finish();
				} else {
					FireToast.makeToast(ActivityChangePassword.this,
							"Password doesn't match.");
				}

			}
			break;
		case R.id.btn_cancel:
			break;

		default:
			break;
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == 1) {
			String strResult = data.getExtras().getString("result");
			edtOldPassword.setText(strResult);

		} else if (resultCode == 2) {
			String strResult = data.getExtras().getString("result");
			edtNewPassword.setText(strResult);

		} else if (resultCode == 3) {
			String strResult = data.getExtras().getString("result");
			edtConfirmPassword.setText(strResult);

		}
	}

}
