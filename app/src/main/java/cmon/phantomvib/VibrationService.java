package cmon.phantomvib;

import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Vibrator;
import android.support.annotation.Nullable;

import android.os.Vibrator;

public class VibrationService extends IntentService {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public VibrationService(){
        super("VibrationService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Vibrator v = (Vibrator) this.getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        v.vibrate(500);

    }
    /**
    @Override

    public int onStartCommand(Intent intent, int flags, int startId) {
        //return super.onStartCommand(intent, flags, startId);

        Vibrator v = (Vibrator) this.getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
         v.vibrate(500);
        return 1;
    }
     */

}
