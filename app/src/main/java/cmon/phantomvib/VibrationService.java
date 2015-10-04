package cmon.phantomvib;

import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.os.Vibrator;
import android.support.annotation.Nullable;

import android.os.Vibrator;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

   
public class VibrationService extends IntentService {
    @Nullable


    private final String[] NOTIFS = {"Facebook Pop", "Whistle"};
    List<Ringtone> notifications;
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

    @Override

    public int onStartCommand(Intent intent, int flags, int startId) {
        //return super.onStartCommand(intent, flags, startId);
        notifications = new ArrayList<Ringtone>(); // contains all valid ringtones
        RingtoneManager mgr = new RingtoneManager(getApplicationContext());
        mgr.setType(RingtoneManager.TYPE_NOTIFICATION);
        int n = mgr.getCursor().getCount();
        for (int validNotif = 0; validNotif < NOTIFS.length; validNotif++) { //adds each of the included notification sounds, if they exist, to the list
            for (int i = 0; i < n; i++) {
                if (mgr.getRingtone(i).getTitle(getApplicationContext()).equals(NOTIFS[validNotif])) {
                    notifications.add(mgr.getRingtone(i));
                    Log.i("RingtoneName", mgr.getRingtone(i).getTitle(getApplicationContext()));
                    break;
                }
            }
        }
        notifications.add(RingtoneManager.getRingtone(getApplicationContext(),RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)));

        try {
            getRandomRingTone().play();
        } catch (Exception e) {
            e.printStackTrace();
        }



        Vibrator v = (Vibrator) this.getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
         v.vibrate(500);
        return 1;
    }
    public Ringtone getRandomRingTone() {
        Random rand = new Random();
        return notifications.get(rand.nextInt(notifications.size()));
    }




}
