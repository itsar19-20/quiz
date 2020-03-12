package DbUtility;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DbUserLoginAdapter {

    private Context context;
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public DbUserLoginAdapter(Context context) {
        this.context = context;
    }

    public DbUserLoginAdapter open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    private ContentValues createCV(String username, String password, String email, String immagine, int punteggio) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_USERNAME, username);
        values.put(DatabaseHelper.COLUMN_PASSWORD, password);
        values.put(DatabaseHelper.COLUMN_EMAIL, email);
        values.put(DatabaseHelper.COLUMN_IMMAGINE, immagine);
        values.put(DatabaseHelper.COLUMN_PUNTEGGIO, punteggio);
        return values;
    }

    public long createUserLogged(String username, String password, String email, String immagine, int punteggio) {
        Log.d("Err: ", username + password + email + immagine + punteggio + DatabaseHelper.TABLE_NAME);
        ContentValues initialV = createCV(username, password, email, immagine, punteggio);
        Log.d("ErrCV: ", initialV.toString());
        return db.insertOrThrow(DatabaseHelper.TABLE_NAME, null, initialV);
    }

    public boolean deleteLoggedUser(long contactID) {
        return db.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper.COLUMN_ID + "=" + contactID, null) > 0;
    }

    public Cursor getLoggedUser() {
        String buildSQL = "SELECT * FROM " + DatabaseHelper.TABLE_NAME;
        return db.rawQuery(buildSQL, null);
    }
}
