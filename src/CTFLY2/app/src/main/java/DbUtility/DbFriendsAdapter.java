package DbUtility;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

import Model.Friend;

public class DbFriendsAdapter {
    private Context context;
    private SQLiteDatabase db;
    private DatabaseFriendHelper dbHelper;

    public DbFriendsAdapter(Context context) {this.context=context;}

    public DbFriendsAdapter open() throws SQLException {
        dbHelper = new DatabaseFriendHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insertFast(int insertCount, List<Friend> friends) {
        String sql = "INSERT INTO " + DatabaseFriendHelper.TABLE_NAME + " ( " + DatabaseFriendHelper.COLUMN_USERNAME + ", " + DatabaseFriendHelper.COLUMN_PUNTEGGIO + ") VALUES ( ?, ? )";
        db = dbHelper.getWritableDatabase();

        db.beginTransactionNonExclusive();

        SQLiteStatement stmt = db.compileStatement(sql);
        for(int x=1; x<=insertCount; x++) {
            stmt.bindString(1, friends.get(x).getUsername());
            stmt.bindString(2, friends.get(x).getPunteggio());

            stmt.execute();
            stmt.clearBindings();
        }

        db.setTransactionSuccessful();
        db.endTransaction();

        db.close();
    }

    public Cursor getFriends() {
        String buildSQL = "SELECT * FROM " + DatabaseFriendHelper.TABLE_NAME + " ORDER BY " + DatabaseFriendHelper.COLUMN_PUNTEGGIO;
        return db.rawQuery(buildSQL, null);
    }

}
