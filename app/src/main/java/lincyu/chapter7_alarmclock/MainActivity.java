package lincyu.chapter7_alarmclock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends ActionBarActivity {

	PendingIntent pendingintent;
	AlarmManager am;

	EditText hour;
	EditText minute;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button btn = (Button)findViewById(R.id.btn_start);
		btn.setOnClickListener(startlistener);
		btn = (Button)findViewById(R.id.btn_stop);
		btn.setOnClickListener(stoplistener);
		
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, AlarmReceiver.class);
		
		pendingintent = PendingIntent.getBroadcast(
				MainActivity.this, 1, intent, 0);

		am = (AlarmManager)getSystemService(ALARM_SERVICE);

		hour = findViewById(R.id.editText);
		minute = findViewById(R.id.editText2);
	}

	private OnClickListener startlistener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			startReport();
		}
	};

	private OnClickListener stoplistener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			stopReport();
		}
	};

	private void startReport() {
		Log.e("get Edittext",hour.getText().toString()+":"+minute.getText().toString());

		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(System.currentTimeMillis());
		Log.e("now time",String.valueOf(c.getTimeInMillis()));

//		c.set(Calendar.HOUR,Integer.parseInt(hour.getText().toString()));
		c.set(Calendar.MINUTE,Integer.parseInt(minute.getText().toString()));
//		Log.e("timetest", String.valueOf(Calendar.HOUR_OF_DAY));
		c.set(Calendar.HOUR_OF_DAY,13);
		c.set(Calendar.SECOND,0);
		c.set(Calendar.MILLISECOND,0);

		Log.e("set time",String.valueOf(c.getTimeInMillis()));


		am.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(),pendingintent);

		Toast.makeText(MainActivity.this, "整點報時開始",
				Toast.LENGTH_SHORT).show();
	}

	private void stopReport() {
		
		am.cancel(pendingintent);
		Toast.makeText(MainActivity.this, "停止整點報時",
				Toast.LENGTH_SHORT).show();
	}
}
