package pro.team.ctfly;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DbUserLoginAdapter {

    private Context context;
    private SQLiteDatabase db;
    private DatabaseUserHelper dbHelper;

    public DbUserLoginAdapter(Context context) {
        this.context = context;
    }

    public DbUserLoginAdapter open() throws SQLException {
        dbHelper = new DatabaseUserHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    private ContentValues createCV(String username, String password, String email, String immagine, int punteggio) {
        ContentValues values = new ContentValues();
        values.put(DatabaseUserHelper.COLUMN_USERNAME, username);
        values.put(DatabaseUserHelper.COLUMN_PASSWORD, password);
        values.put(DatabaseUserHelper.COLUMN_EMAIL, email);
        values.put(DatabaseUserHelper.COLUMN_IMMAGINE, immagine);
        values.put(DatabaseUserHelper.COLUMN_PUNTEGGIO, punteggio);
        return values;
    }

    public long createUserLogged(String username, String password, String email, String immagine, int punteggio) {
        Log.d("Err: ", username + password + email + immagine + punteggio + DatabaseUserHelper.TABLE_NAME);
        ContentValues initialV = createCV(username, password, email, immagine, punteggio);
        Log.d("ErrCV: ", initialV.toString());
        return db.insertOrThrow(DatabaseUserHelper.TABLE_NAME, null, initialV);
    }

    public boolean deleteLoggedUser(long contactID) {
        return db.delete(DatabaseUserHelper.TABLE_NAME, DatabaseUserHelper.COLUMN_ID + "=" + contactID, null) > 0;
    }

    public Cursor getLoggedUser() {
        String buildSQL = "SELECT * FROM " + DatabaseUserHelper.TABLE_NAME;
        return db.rawQuery(buildSQL, null);
    }
}
