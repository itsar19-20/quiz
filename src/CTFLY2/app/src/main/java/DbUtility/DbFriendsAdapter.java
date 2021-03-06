package DbUtility;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.List;

import Model.Friend;

public class DbFriendsAdapter {
    private Context context;
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public DbFriendsAdapter(Context context) {this.context=context;}

    public DbFriendsAdapter open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    private ContentValues createCV(String username, String punteggio) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.FRIEND_COLUMN_USERNAME, username);
        cv.put(DatabaseHelper.FRIEND_COLUMN_PUNTEGGIO, punteggio);
        return cv;
    }

    public long addFriend(String username, String punteggio) {
        ContentValues initCV = createCV(username, punteggio);
        return db.insertOrThrow(DatabaseHelper.FRIEND_TABLE_NAME, null, initCV);
    }

    public void insertFast(int insertCount, List<Friend> friends) {
        String sql = "INSERT INTO " + DatabaseHelper.FRIEND_TABLE_NAME + " ( "
                + DatabaseHelper.FRIEND_COLUMN_USERNAME + ", "
                + DatabaseHelper.FRIEND_COLUMN_PUNTEGGIO + ") VALUES ( ?, ? )";

        db.beginTransactionNonExclusive();

        SQLiteStatement stmt = db.compileStatement(sql);
        for(int x=0; x<insertCount; x++) {
            stmt.bindString(1, friends.get(x).getUsername());
            stmt.bindString(2, friends.get(x).getPunteggio());

            stmt.execute();
            stmt.clearBindings();
        }

        db.setTransactionSuccessful();
        db.endTransaction();

    }

    public Cursor getFriends() {
        String buildSQL = "SELECT * FROM " + DatabaseHelper.FRIEND_TABLE_NAME + " ORDER BY " + DatabaseHelper.FRIEND_COLUMN_PUNTEGGIO + " DESC";
        Cursor c = db.rawQuery(buildSQL, null);
        if (c.moveToFirst()) {
            Log.d("FIND: ", "Ho trovato entry nel dbLite");
            return c;
        } else {
            Log.d("ERROR: ", "Non ho trovato nulla nel dbLite");
        }
        return c;
    }

    public Cursor getSingleFriend(String username) {
        Cursor friend = db.query(DatabaseHelper.FRIEND_TABLE_NAME, new String[]{DatabaseHelper.FRIEND_COLUMN_ID, DatabaseHelper.FRIEND_COLUMN_USERNAME, DatabaseHelper.FRIEND_COLUMN_PUNTEGGIO},
                DatabaseHelper.FRIEND_COLUMN_USERNAME + " = '" + username + "'", null, null, null, null);
        return friend;
    }

    public boolean removeFriend(long noteID) {
        return db.delete(DatabaseHelper.FRIEND_TABLE_NAME, DatabaseHelper.FRIEND_COLUMN_ID + "=" + noteID, null)>0;
    }

    public boolean removeAllFriend() {
        return db.delete(DatabaseHelper.FRIEND_TABLE_NAME, null , null)>0;
    }

}
