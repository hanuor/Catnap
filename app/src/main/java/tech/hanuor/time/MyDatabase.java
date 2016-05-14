package tech.hanuor.time;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Shantanu Johri on 02-04-2015.
 */
public class MyDatabase extends SQLiteOpenHelper {
    public final static String DB_NAME = "id_database.db";
    public final static int DB_VERSION = 1;
    public final static String TABLE_NAME = "idtable";
    public final static String TABLE_ID = "id";
    public final static String CREATE_NAME = "create table "+TABLE_NAME+" ("+TABLE_ID+" INTEGER)";
    public MyDatabase(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_NAME);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int newv, int oldv) {
        db.execSQL("drop table if exists "+TABLE_NAME);
    }


}
