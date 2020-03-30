package DbUtility;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.List;

import Model.UserPunteggio;

public class DbUserPunteggioAdapter {
    private Context context;
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public DbUserPunteggioAdapter(Context context) {
        this.context = context;
    }

    public DbUserPunteggioAdapter open() throws SQLException {
        dbHelper=new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }
    public  void close(){
        dbHelper.close();
    }

    private ContentValues createContentValues(String username, String punteggio) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.USER_COLUMN_USERNAME, username);
        cv.put(DatabaseHelper.USER_COLUMN_PUNTEGGIO, punteggio);
        return cv;
    }

    public void insert(int insertCount, List<UserPunteggio> user) {
        String query = "INSERT INTO " + DatabaseHelper.USER_TABLE_NAME
                + " ( " + DatabaseHelper.USER_COLUMN_USERNAME
                + ", " + DatabaseHelper.USER_COLUMN_PUNTEGGIO + ") VALUES (?, ?)";
        db.beginTransactionNonExclusive();
        SQLiteStatement statement=db.compileStatement(query);
        for (int i=0; i<insertCount;i++){
            statement.bindString(1,user.get(i).getUsername());
            statement.bindString(2,user.get(i).getPunteggio());
           statement.execute();
           statement.clearBindings();

        }
        db.setTransactionSuccessful();
        db.endTransaction();


    }
    public Cursor getUsers(){
        String query="SELECT * FROM " + DatabaseHelper.USER_TABLE_NAME +" ORDER BY " + DatabaseHelper.USER_COLUMN_PUNTEGGIO +" DESC";
       return db.rawQuery(query,null);
    }
    public boolean removeAllUsers(){
        return db.delete(DatabaseHelper.USER_TABLE_NAME,null,null)>0;
    }
}
