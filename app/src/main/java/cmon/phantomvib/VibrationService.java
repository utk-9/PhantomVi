package cmon.phantomvib;

import android.app.IntentService;
import android.app.KeyguardManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
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
        KeyguardManager myKM = (KeyguardManager) getApplicationContext().getSystemService(Context.KEYGUARD_SERVICE);
        if( myKM.inKeyguardRestrictedInputMode()) {
            AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            switch (am.getRingerMode()) {
                case AudioManager.RINGER_MODE_VIBRATE:
                    Log.i("MyApp", "Vibrate mode");
                    makeVibration();
                    break;
                case AudioManager.RINGER_MODE_NORMAL:
                    Log.i("MyApp", "Normal mode");
                    makeSound();
                    break;
            }
        } else {
            Log.i("VibrationService","Not Locked.");
        }
        return 1;
    }
    public void makeSound() {
        notifications = new ArrayList<Ringtone>(); // contains all valid ringtones
        RingtoneManager mgr = new RingtoneManager(getApplicationContext());
        mgr.setType(RingtoneManager.TYPE_NOTIFICATION);
        for (int validNotif = 0; validNotif < NOTIFS.length; validNotif++) { //adds each of the included notification sounds, if they exist, to the list
            for (int i = 0; i < mgr.getCursor().getCount(); i++) {
                if (mgr.getRingtone(i).getTitle(getApplicationContext()).equals(NOTIFS[validNotif])) {
                    notifications.add(mgr.getRingtone(i));
                    break;
                }
            }
        }
        notifications.add(RingtoneManager.getRingtone(getApplicationContext(), RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))); // Adds default notification
        try {
            getRandomRingTone().play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void makeVibration() {
        Vibrator v = (Vibrator) this.getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(500);
    }

    // generate random ringtone from list
    public Ringtone getRandomRingTone() {
        Random rand = new Random();
        return notifications.get(rand.nextInt(notifications.size()));
    }







}
