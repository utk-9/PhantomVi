package cmon.phantomvib;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import java.util.Random;



public class SettingsActivity extends Activity {

    int timeChoice;
    RadioButton rb1;
    RadioButton rb2;
    RadioButton rb3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_layout);

        rb1 = (RadioButton) findViewById(R.id.radioButtonMinutely);
        rb2 = (RadioButton) findViewById(R.id.radioButtonHourley);
        rb3 = (RadioButton) findViewById(R.id.radioButtonDailey);

        Button goButton = (Button) findViewById(R.id.goButton);
        goButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                /*
                Intent intent = new Intent(getApplicationContext(), VibrationService.class);
                startService(intent);
                */
                scheduleAlarm();
            }
        });

    }

    public void scheduleAlarm (){
        Intent intent = new Intent (getApplicationContext(), AlarmReceiver.class);

        final PendingIntent pendingIntent = PendingIntent.getBroadcast(this,
                AlarmReceiver.REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        long firstMillis = System.currentTimeMillis();
        AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        if (rb1.isChecked()) alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstMillis,
                60000, pendingIntent);
        else if (rb2.isChecked())alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstMillis,
                (3600000), pendingIntent);
        else if (rb3.isChecked()) alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstMillis,
                (24*3600000), pendingIntent);
        else alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstMillis,
                    (3000), pendingIntent);

    }


}
