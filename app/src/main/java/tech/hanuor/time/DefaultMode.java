package tech.hanuor.time;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;

/**
 * Created by Shantanu Johri on 11-04-2015.
 */
public class DefaultMode extends BroadcastReceiver{
    AudioManager jk;
    MyService m = new MyService();
    //SilentMode k = new SilentMode();

     @Override
    public void onReceive(Context context, Intent intent) {
         int x = m.NOTIFICATION_ID;
        // int joker = k.NOTIFICATION_ID;
         jk = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
         jk.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
         NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
         manager.cancel(x);
         //manager.cancel(joker);
         /*if (x != 0) {
             manager.cancel(x);
         } else if (joker != 0) {
             manager.cancel(joker);
         }*/
     }
}
