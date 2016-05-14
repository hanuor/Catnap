package tech.hanuor.time;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;

import java.util.ArrayList;

/**
 * Created by Shantanu Johri on 02-04-2015.
 */
public class NotificationButtonListener extends BroadcastReceiver{
    MyDatabase md;
    AudioManager alm;
    AlarmManager can;
    int re;
    @Override
    public void onReceive(Context context, Intent intent) {
        int notificationId = intent.getIntExtra("notificationId", 0);
        md = new MyDatabase(context);
        alm = (AudioManager) context
                .getSystemService(Context.AUDIO_SERVICE);
        can= (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alm.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        SQLiteDatabase sd = md.getReadableDatabase();
        Cursor c = sd.query(md.TABLE_NAME, null, null, null, null, null, null);
        ArrayList<Integer> idList = new ArrayList<Integer>();

        while (c.moveToNext()) {
            idList.add(c.getInt(0));
        }
        c.close();
        sd.close();
        for (int i = 0; i < idList.size(); i++) {

           // Intent intent = new Intent();
            intent.setClass(context, MyService.class);
            PendingIntent pintent = PendingIntent.getService(context,
                    idList.get(i), intent, PendingIntent.FLAG_UPDATE_CURRENT);
            can.cancel(pintent);
        }
        sd = md.getWritableDatabase();
        sd.delete(md.TABLE_NAME, null, null);
        sd.close();
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancel(notificationId);
    }
}
