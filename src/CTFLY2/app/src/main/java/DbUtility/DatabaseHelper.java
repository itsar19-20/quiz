package DbUtility;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ctfly-database.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "loggedUser";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_IMMAGINE = "immagine";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_PUNTEGGIO = "punteggio";

    public static final String FRIEND_TABLE_NAME = "friend";
    public static final String FRIEND_COLUMN_ID = "_id";
    public static final String FRIEND_COLUMN_USERNAME = "username";
    public static final String FRIEND_COLUMN_PUNTEGGIO = "punteggio";

    public static final String CHALLENGE_TABLE_NAME = "challenge";
    public static final String CHALLENGE_COLUMN_ID = "_id";
    public static final String CHALLENGE_COLUMN_TITOLO = "titolo";
    public static final String CHALLENGE_COLUMN_DESC = "descrizione";
    public static final String CHALLENGE_COLUMN_CREATORE = "creatore";
    public static final String CHALLENGE_COLUMN_CATEGORIA = "categoria";
    public static final String CHALLENGE_COLUMN_RATING = "rating";
    public static final String CHALLENGE_COLUMN_PUNTEGGIO = "punteggio";
    public static final String CHALLENGE_COLUMN_DATA = "data";

    private static final String CHALLENGE_TABLE_CREATE = "create table " + CHALLENGE_TABLE_NAME + " (" + CHALLENGE_COLUMN_ID + " integer primary key autoincrement, " +
            CHALLENGE_COLUMN_TITOLO + " text not null, " +
            CHALLENGE_COLUMN_DESC + " text not null, " +
            CHALLENGE_COLUMN_CREATORE + " text not null, " +
            CHALLENGE_COLUMN_CATEGORIA + " text not null, " +
            CHALLENGE_COLUMN_RATING + " text, " +
            CHALLENGE_COLUMN_PUNTEGGIO + " text, " +
            CHALLENGE_COLUMN_DATA + " text);";

    private static final String FRIEND_TABLE_CREATE = "create table " + FRIEND_TABLE_NAME + " (" + FRIEND_COLUMN_ID + " integer primary key autoincrement, " +
            FRIEND_COLUMN_USERNAME + " text not null, " +
            FRIEND_COLUMN_PUNTEGGIO + " integer);";

    private static final String TABLE_CREATE = "create table " + TABLE_NAME + " (" + COLUMN_ID + " integer primary key autoincrement, " +
            COLUMN_USERNAME + " text not null, " +
            COLUMN_PASSWORD + " text not null, " +
            COLUMN_EMAIL + " text not null, " +
            COLUMN_PUNTEGGIO + " integer, " +
            COLUMN_IMMAGINE + " text);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        db.execSQL(FRIEND_TABLE_CREATE);
        db.execSQL(CHALLENGE_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    
}
