package tech.hanuor.time;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.support.v4.app.NotificationCompat;

/**
 * Created by Shantanu Johri on 02-04-2015.
 */
public class MyService extends BroadcastReceiver{
    AudioManager am;
    final static int  NOTIFICATION_ID = 8755;
    Context con;
 private void notification() {
        try {
            String ns = Context.NOTIFICATION_SERVICE;
            NotificationManager notificationManager =
                    (NotificationManager) con.getSystemService(ns);
            Intent contentIntent = new Intent(con, MainActivity.class);
            PendingIntent contentpendingIntent = PendingIntent.getActivity(con, 0, contentIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//Create an Intent for the BroadcastReceiver
            Intent buttonIntent = new Intent(con, NotificationButtonListener.class);
            buttonIntent.putExtra("notificationId", NOTIFICATION_ID);

//Create the PendingIntent
            PendingIntent btPendingIntent = PendingIntent.getBroadcast(con, 0, buttonIntent, 0);

//Pass this PendingIntent to addAction method of Intent Builder
            NotificationCompat.Builder mb = new NotificationCompat.Builder(con);
            mb.setSmallIcon(R.drawable.ic_stat_catnap);
            mb.setContentTitle("Catnap");
            mb.setContentText("Your device is set to vibrate mode");
            mb.setContentIntent(contentpendingIntent);
            //mb.setPriority(NotificationCompat.PRIORITY_LOW);
            mb.setOngoing(true);
            mb.addAction(R.drawable.ic_stat_catnapcancel, "Cancel the current session!", btPendingIntent);
            notificationManager.notify(NOTIFICATION_ID, mb.build());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        con = context;
        try {
            am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            am.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
            notification();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}