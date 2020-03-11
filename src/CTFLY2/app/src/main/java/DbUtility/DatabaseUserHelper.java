package DbUtility;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseUserHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ctfly-database.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "loggedUser";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_IMMAGINE = "immagine";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_PUNTEGGIO = "punteggio";

    private static final String TABLE_CREATE = "create table " + TABLE_NAME + " (" + COLUMN_ID + " integer primary key autoincrement, " +
            COLUMN_USERNAME + " text not null, " +
            COLUMN_PASSWORD + " text not null, " +
            COLUMN_EMAIL + " text not null, " +
            COLUMN_PUNTEGGIO + " integer, " +
            COLUMN_IMMAGINE + " text);";

    public DatabaseUserHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
