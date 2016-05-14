package tech.hanuor.time;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.RandomTransitionGenerator;

import java.util.ArrayList;
import java.util.Calendar;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    int storeR;
    int storeM;
    int storeRfirst;
    int journey;
    int storeMfirst;
    Toolbar toolbar;
    String refHour , refMin;
    int storeR2;
    int storeM2;
    int storeRfirst2;
    int storeMfirst2;
    int checkstate = 0;
    ImageButton overflow;
    int changeAction = 0;
    SharedPreferences Rdata;
    SharedPreferences Mdata;
    SharedPreferences Rfirstdata;
    SharedPreferences Mfirstdata;

    public static String right = "Save Hour";
    public static String left = "Save Minute";
    public static String north = "Save Uhr";
    public static String south = "Save Minuten";
    SharedPreferences Rdata2;
    SharedPreferences Mdata2;
    /*SharedPreferences Rfirstdata2;
    SharedPreferences Mfirstdata2;*/
   /* public static String right2 = "Save Hour";
    public static String left2 = "Save Minute";
    public static String north2 = "Save Uhr";
    public static String south2 = "Save Minuten";*/
    static final int TIME_DIALOG_ID = 9457556;
    static final int TIME_DIALOG_IDI = 8057514;
    //static final int TIME_DIALOG_ID2 = 94584148;
    //static final int TIME_DIALOG_IDI2 = 8126767;
    int hour , hour2;
    int zhour;
    int zminute;
    int Uhr;
    int minuten;
    int minute , minute2;
    PendingIntent pi;
    Button start;
    Button end;
    Button begin;
   // Button endall;
    //Button begin = (Button) findViewById(R.id.button3);
    MyDatabase md = new MyDatabase(this) ;
    int checkbutton2;
    AudioManager alm;
    AlarmManager can;
    Button start2;
    Button end2;
    //Button begin2;
    //ImageButton overflow2;
    int checkendbutton;
    int id;
    AlarmManager alarm;
    ImageButton menuover;
    KenBurnsView kbv;

   // @Override


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cards);
        kbv = (KenBurnsView) findViewById(R.id.imageCover);
        AccelerateDecelerateInterpolator ACCELERATE_DECELERATE = new AccelerateDecelerateInterpolator();
        RandomTransitionGenerator generator = new RandomTransitionGenerator(10000, ACCELERATE_DECELERATE);
        kbv.setTransitionGenerator(generator); //set new transition on kbv
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        checkendbutton = 0;
        checkbutton2 = 0;
        alm = (AudioManager) this
                .getSystemService(Context.AUDIO_SERVICE);
        can= (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        start = (Button) findViewById(R.id.button1);
        end = (Button) findViewById(R.id.button2);
      //  endall = (Button) findViewById(R.id.buttonend);
        begin = (Button) findViewById(R.id.button3);
        overflow = (ImageButton) findViewById(R.id.overflow);

        //start2 = (Button) findViewById(R.id.button12);
        //end2 = (Button) findViewById(R.id.button22);
        //begin2 = (Button) findViewById(R.id.button32);
        //overflow2 = (ImageButton) findViewById(R.id.overflow2);
        Rdata = getSharedPreferences(right, 0);
        Mdata = getSharedPreferences(left, 0);
        Rfirstdata = getSharedPreferences(north, 0);
        Mfirstdata = getSharedPreferences(south, 0);

                //Button b=(Button)findViewById(R.id.textFade);



        int returnedsecond = Rdata.getInt("shared", 0);
        int returnedsecondi = Mdata.getInt("sharedi", 0);
        int returnedfirst = Rfirstdata.getInt("sharedfirst", 0);
        int returnedfirsti = Mfirstdata.getInt("sharedfirsti", 0);
        String king = pad(returnedfirst);
        String queen = pad(returnedfirsti);
        String elephant  = pad(returnedsecond);
        String pawn = pad(returnedsecondi);
        start.setText(king + ":" + queen);
        end.setText(elephant + ":" + pawn);

       /* Rdata2 = getSharedPreferences(right2, 0);
        Mdata2 = getSharedPreferences(left2, 0);
        Rfirstdata2 = getSharedPreferences(north2, 0);
        Mfirstdata2 = getSharedPreferences(south2, 0);
        int returnedsecond2 = Rdata2.getInt("shared2", 0);
        int returnedsecondi2 = Mdata2.getInt("sharedi2", 0);
        int returnedfirst2 = Rfirstdata2.getInt("sharedfirst2", 0);
        int returnedfirsti2 = Mfirstdata2.getInt("sharedfirsti2", 0);
        start2.setText(returnedfirst2 + ":" + returnedfirsti2);
        end2.setText(returnedsecond2 + ":" + returnedsecondi2);*/
        //
       // menuover.setOnClickListener(this);
        start.setOnClickListener(this);
        end.setOnClickListener(this);
        begin.setOnClickListener(this);
        overflow.setOnClickListener(this);
      /*  start2.setOnClickListener(this);
        end2.setOnClickListener(this);
        begin2.setOnClickListener(this);
        overflow2.setOnClickListener(this);
        endall.setOnClickListener(this);
*/
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case TIME_DIALOG_ID:
                return new TimePickerDialog(this, timePickerListener, hour, minute,
                        false);

            case TIME_DIALOG_IDI:
                // dialog.setTitle("Set End Time");
                return new TimePickerDialog(this, timePickerListeneri, zhour,
                        zminute, false);
            /*case TIME_DIALOG_ID2:
                return new TimePickerDialog(this, timePickerListener2, hour, minute,
                        false);
*/
           /* case TIME_DIALOG_IDI2:
                // dialog.setTitle("Set End Time");
                return new TimePickerDialog(this, timePickerListeneri2, zhour,
                        zminute, false);*/

        }
        return null;
    }
    private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int selectedHour,
                              int selectedMinute) {
            /* MyService noti = new MyService();
            int getNotiId = noti.NOTIFICATION_ID;
            NotificationManager Notimanager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
            Notimanager.cancel(getNotiId);*/
            hour = selectedHour;
            minute = selectedMinute;
            storeR = hour;
            storeM= minute;

            refHour =  pad(hour);
            refMin = pad(minute);
            end.setText(refHour + ":" + refMin);

            SharedPreferences.Editor editor = Rdata.edit();
            editor.putInt("shared", storeR);
            editor.commit();
            SharedPreferences.Editor editori = Mdata.edit();
            editori.putInt("sharedi", storeM);
            editori.commit();

        }
    };
  /* private TimePickerDialog.OnTimeSetListener timePickerListener2 = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int selectedHour,
                              int selectedMinute) {

            hour2 = selectedHour;
            minute2 = selectedMinute;
            storeR2= hour2;
            storeM2 = minute2;
            SharedPreferences.Editor editor2 = Rdata2.edit();
            editor2.putInt("shared2", storeR2);
            editor2.commit();
            end2.setText(hour2 + ":" + minute2);
            SharedPreferences.Editor editori2 = Mdata2.edit();
            editori2.putInt("sharedi2", storeM2);
            editori2.commit();

        }
    };*/
   /* private TimePickerDialog.OnTimeSetListener timePickerListeneri2 = new TimePickerDialog.OnTimeSetListener() {

        public void onTimeSet(TimePicker view, int zselectedHour,
                              int zselectedMinute) {

            Uhr2 = zselectedHour;
            minuten2 = zselectedMinute;

            storeRfirst2 = Uhr2;
            storeMfirst2 = minuten2;
            start2.setText(Uhr2 + ":" + minuten2);
            SharedPreferences.Editor editorfirst2 = Rfirstdata2.edit();
            editorfirst2.putInt("sharedfirst2", storeRfirst2);
            editorfirst2.commit();

            SharedPreferences.Editor editorfirsti2 = Mfirstdata2.edit();
            editorfirsti2.putInt("sharedfirsti2", storeMfirst2);
            editorfirsti2.commit();
        }
    };*/

    private TimePickerDialog.OnTimeSetListener timePickerListeneri = new TimePickerDialog.OnTimeSetListener() {

        public void onTimeSet(TimePicker view, int zselectedHour,
                              int zselectedMinute) {
            journey = 0;
            Uhr = zselectedHour;
            minuten = zselectedMinute;
            checkendbutton = 1;
            storeRfirst = Uhr;
            storeMfirst = minuten;
            start.setText(Uhr + ":" + minuten);
            SharedPreferences.Editor editorfirst = Rfirstdata.edit();
            editorfirst.putInt("sharedfirst", storeRfirst);
            editorfirst.commit();

            SharedPreferences.Editor editorfirsti = Mfirstdata.edit();
            editorfirsti.putInt("sharedfirsti", storeMfirst);
            editorfirsti.commit();
        }
    };

    @SuppressLint("NewApi")
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
          /*  case R.id.menu:




                                    //Toast.makeText(this,"afasdasdas",Toast.LENGTH_SHORT).show();
                                    PopupMenu popupp = new PopupMenu(MainActivity.this, menuover);
                                    popupp.getMenuInflater().inflate(R.menu.mainover, popupp.getMenu());
                                    popupp.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                        public boolean onMenuItemClick(MenuItem item) {
                                            switch (item.getItemId()) {
                                                case R.id.one:
                                                    //PAss Intent
                                                    Intent guide = new Intent(getApplicationContext(), GuideLines.class);
                                                    startActivity(guide);
                                                    break;
                                                case R.id.two:
                                                    Intent abt = new Intent(getApplicationContext(), About.class);
                                                    startActivity(abt);
                                                    break;


                                            }
                                            return true;
                                        }

                                        });

                                    popupp.show();

                                    break;*/

            case R.id.button3:
                if (checkendbutton == 0) {

                    Toast.makeText(this, "You forgot to enter the end time!", Toast.LENGTH_SHORT).show();
                    break;
                } else {
                    //Delete the existing alarms
                    //md = new MyDatabase(this);
                    //AlarmManager cannw= (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
                    //SQLiteDatabase sdnw ;
                    checkstate = 1;
                    SQLiteDatabase sdata = md.getWritableDatabase();
                    sdata.delete(md.TABLE_NAME, null, null);
                    sdata.close();
                    //Insert the new alarm!
                    // md = new MyDatabase(this);
                    SQLiteDatabase sd = md.getWritableDatabase();
                    Calendar cal = Calendar.getInstance();
                    cal.set(Calendar.HOUR_OF_DAY, Uhr);
                    cal.set(Calendar.MINUTE, minuten);
                    id = (int) System.currentTimeMillis();
                    ContentValues cv = new ContentValues();
                    cv.put(md.TABLE_ID, id);
                    sd.insert(md.TABLE_NAME, md.TABLE_ID, cv);
                    cv.clear();
                    sd.close();
                    md.close();
                    if(journey == 0) {
                        try {
                            Intent intent = new Intent(this, MyService.class);

                            PendingIntent pintent = PendingIntent.getBroadcast(this, id, intent,
                                    PendingIntent.FLAG_UPDATE_CURRENT);
                            alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                            alarm.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), alarm.INTERVAL_DAY,
                                    pintent);


                            //This is the part for the second button function
                            //This is the part where i kiss you!
                            Calendar endc = Calendar.getInstance();
                            endc.set(Calendar.HOUR_OF_DAY, hour);
                            endc.set(Calendar.MINUTE, minute);
                            Intent i = new Intent(this, DefaultMode.class);
                            //HEre
                            pi = PendingIntent.getBroadcast(this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
                            AlarmManager ala = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                            ala.setRepeating(AlarmManager.RTC_WAKEUP, endc.getTimeInMillis(), ala.INTERVAL_DAY, pi);
                            Toast.makeText(this, "Session set!", Toast.LENGTH_LONG).show();
                            break;
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        Intent intent = new Intent(this, DefaultMode.class);

                        PendingIntent pintent = PendingIntent.getBroadcast(this, id, intent,
                                PendingIntent.FLAG_UPDATE_CURRENT);
                        alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                        alarm.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), alarm.INTERVAL_DAY,
                                pintent);
                    }
                }


            case R.id.button2:
                showDialog(TIME_DIALOG_ID);
                break;
            case R.id.button1:
                showDialog(TIME_DIALOG_IDI);
                break;
            case R.id.overflow:
                PopupMenu popup = new PopupMenu(MainActivity.this, overflow);
                popup.getMenuInflater().inflate(R.menu.popup, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.two:
                                if (checkstate == 1) {
                                    MyService notiob = new MyService();
                                    int noi = notiob.NOTIFICATION_ID;
                                    journey = 1;
                                    alarm.cancel(pi);
                                    Calendar cal = Calendar.getInstance();
                                    cal.set(Calendar.HOUR_OF_DAY, Uhr);
                                    cal.set(Calendar.MINUTE, minuten);
                                    AudioManager jk = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
                                    jk.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                                    SQLiteDatabase sd = md.getReadableDatabase();
                                    Cursor c = sd.query(md.TABLE_NAME, null, null, null, null, null, null);
                                    ArrayList<Integer> idList = new ArrayList<Integer>();

                                    while (c.moveToNext()) {
                                        idList.add(c.getInt(0));
                                    }
                                    c.close();
                                    sd.close();
                                    for (int i = 0; i < idList.size(); i++) {

                                        Intent intent = new Intent();
                                        intent.setClass(getApplicationContext(), MyService.class);
                                        PendingIntent pintent = PendingIntent.getService(getApplicationContext(),
                                                idList.get(i), intent, PendingIntent.FLAG_UPDATE_CURRENT);
                                        can.cancel(pintent);
                                    }
                                    sd = md.getWritableDatabase();
                                    sd.delete(md.TABLE_NAME, null, null);
                                    sd.close();
                                    NotificationManager manager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
                                    manager.cancel(noi);
                                    Intent intent = new Intent(getApplicationContext(), DefaultMode.class);

                                    PendingIntent pintent = PendingIntent.getBroadcast(getApplicationContext(), id, intent,
                                            PendingIntent.FLAG_UPDATE_CURRENT);
                                    alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                                    alarm.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), alarm.INTERVAL_DAY,
                                            pintent);
                                    Toast.makeText(getApplicationContext(),"Session cancelled successfully",Toast.LENGTH_SHORT).show();
                                /*Toast.makeText(getApplicationContext(),"lO",Toast.LENGTH_SHORT).show();
                                md = new MyDatabase(getApplicationContext());
                                //MyDatabase mdew = new MyDatabase(getApplicationContext());
                                SQLiteDatabase sdnw = md.getReadableDatabase();
                               //


                                Cursor c = sdnw.query(md.TABLE_NAME, null, null, null, null, null, null);
                                ArrayList<Integer> idList = new ArrayList<Integer>();

                                while (c.moveToNext()) {
                                    idList.add(c.getInt(0));
                                }
                                c.close();
                                sdnw.close();
                                //can = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
                                for (int i = 0; i < idList.size(); i++) {

                                    Intent intent = new Intent();
                                    intent.setClass(getApplicationContext(), MyService.class);
                                    PendingIntent pintent = PendingIntent.getService(getApplicationContext(),
                                            idList.get(i), intent, PendingIntent.FLAG_UPDATE_CURRENT);
                                    can.cancel(pintent);
                                }
                                sdnw =md.getWritableDatabase();
                                sdnw.delete(md.TABLE_NAME, null, null);
                                sdnw.close();
                                MyService noti = new MyService();
                                int getNotiId = noti.NOTIFICATION_ID;
                                NotificationManager Notimanager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
                                Notimanager.cancel(getNotiId);*/
                                }
                                else{
                                    Toast.makeText(getApplicationContext(),"Please enter the session.",Toast.LENGTH_SHORT).show();
                                }
                                }
                                return true;

                        }

                });
                popup.show();
                break;
        }
    }
// Button Two Functioning

/* FUTURE UPDATE!
    final Dialog dialog = new Dialog(MainActivity.this);
    // Include dialog.xml file
    dialog.setContentView(R.layout.action);
    // Set dialog title
    dialog.setTitle("Choose your action");

    // set values for custom dialog components - text, image and button
    ImageButton text = (ImageButton) dialog.findViewById(R.id.one);

    ImageButton image = (ImageButton) dialog.findViewById(R.id.two);
    text.setOnClickListener(MainActivity.this);
    image.setOnClickListener(MainActivity.this);

    dialog.show();
    text.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            changeAction = 1;
            Toast.makeText(getApplicationContext(), "Silent mode selected.Please restart the app", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        }
    });
    image.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            changeAction = 2;
            Toast.makeText(getApplicationContext(),"Vibrate mode selected.Please restart the app",Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        }
    });
*/
public void cancelAllIds() {
      //  md = new MyDatabase(this);

        alm.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
       /* SQLiteDatabase sd = md.getReadableDatabase();
        Cursor c = sd.query(md.TABLE_NAME, null, null, null, null, null, null);
        ArrayList<Integer> idList = new ArrayList<Integer>();

        while (c.moveToNext()) {
            idList.add(c.getInt(0));
        }
        c.close();
        sd.close();
        for (int i = 0; i < idList.size(); i++) {

            Intent intent = new Intent();
            intent.setClass(this, MyService.class);
            PendingIntent pintent = PendingIntent.getService(this,
                    idList.get(i), intent, PendingIntent.FLAG_UPDATE_CURRENT);
            can.cancel(pintent);
        }
        sd = md.getWritableDatabase();
        sd.delete(md.TABLE_NAME, null, null);
        sd.close();*/
    }

    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }
}