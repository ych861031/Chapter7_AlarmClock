package lincyu.chapter7_alarmclock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

public class AlarmReceiver extends BroadcastReceiver {
	
	@Override
	public void onReceive(Context context, Intent intent) {
		
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(System.currentTimeMillis());
		int hour = c.get(Calendar.HOUR_OF_DAY);
		
		Toast.makeText(context, "Time's up go to sleep",
				Toast.LENGTH_LONG).show();
		Log.e("time's up alarm","!!!");
	}
}
