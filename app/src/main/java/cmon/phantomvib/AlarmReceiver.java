package cmon.phantomvib;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Welcome on 10/4/2015.
 */
public class AlarmReceiver extends BroadcastReceiver {

    final public static int REQUEST_CODE = 12345;

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i =  new Intent(context ,VibrationService.class);
        context.startService(i);


    }
}
