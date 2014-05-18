package com.chaos.onekeyconnect;

import java.util.List;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		String mobile = "10001";
		String content = "xykdmm";

		SmsManager smsManager = SmsManager.getDefault();
		PendingIntent sentIntent = PendingIntent.getBroadcast(
				getApplicationContext(), 0, new Intent(), 0);

		if (content.length() >= 70) {
			// 短信字数大于70，自动分条
			List<String> ms = smsManager.divideMessage(content);

			for (String str : ms) {
				// 短信发送
				smsManager.sendTextMessage(mobile, null, str, sentIntent, null);
			}
		} else {
			smsManager.sendTextMessage(mobile, null, content, sentIntent, null);
		}

		Toast.makeText(getApplicationContext(), "发送成功！", Toast.LENGTH_LONG)
				.show();
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
