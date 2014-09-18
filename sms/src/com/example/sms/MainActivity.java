package com.example.sms;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	private Button btnSend;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnSend = (Button) findViewById(R.id.button1);
		btnSend.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				EditText phone = (EditText) findViewById(R.id.editText1);
				EditText content = (EditText) findViewById(R.id.editText2);
				String sp = phone.getText().toString();
				String sc = content.getText().toString();
				if (sp.equals("") || sc.equals("")) {
					showAlert(getString(R.string.sms_empty));
				} else
					sendSMS(phone.getText().toString(), content.getText()
							.toString());
			}
		});
	}

	public void sendSMS(String phoneNumber, String message) {
		SmsManager smsManager = SmsManager.getDefault();
		smsManager.sendTextMessage(phoneNumber, null, message, null, null);
	}

	private void showAlert(String msg) {
		AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
		alert.setTitle("Alert DIalog");
		alert.setMessage(msg);
		alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				finish();
			}
		});
		AlertDialog alertDialog = alert.create();
		alertDialog.show();
	}

}
