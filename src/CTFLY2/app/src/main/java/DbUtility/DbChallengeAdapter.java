package DbUtility;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.List;

import Model.ChallengeAppSide;

public class DbChallengeAdapter {
    private Context context;
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public DbChallengeAdapter(Context context) { this.context=context; }

    public DbChallengeAdapter open() {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() { dbHelper.close(); }

    public void insertFast(int insertCount, List<ChallengeAppSide> challenges) {
        String sql = "INSERT INTO " + DatabaseHelper.CHALLENGE_TABLE_NAME + " ( " +
                DatabaseHelper.CHALLENGE_COLUMN_TITOLO + ", " +
                DatabaseHelper.CHALLENGE_COLUMN_DESC + ", " +
                DatabaseHelper.CHALLENGE_COLUMN_CREATORE + ", " +
                DatabaseHelper.CHALLENGE_COLUMN_CATEGORIA + ", " +
                DatabaseHelper.CHALLENGE_COLUMN_RATING + ", " +
                DatabaseHelper.CHALLENGE_COLUMN_PUNTEGGIO + ", " +
                DatabaseHelper.CHALLENGE_COLUMN_DATA + ") VALUES ( ?, ?, ?, ?, ?, ?, ? )";

        db.beginTransactionNonExclusive();

        SQLiteStatement stmt = db.compileStatement(sql);
        for(int x=0; x<insertCount; x++) {
            stmt.bindString(1, challenges.get(x).getTitolo());
            stmt.bindString(2, challenges.get(x).getDescrizione());
            stmt.bindString(3, challenges.get(x).getUsernameCreatore());
            stmt.bindString(4, challenges.get(x).getCategoria());
            stmt.bindString(5, challenges.get(x).getRating());
            stmt.bindString(6, challenges.get(x).getPunteggio());
            stmt.bindString(7, challenges.get(x).getData());

            stmt.execute();
            stmt.clearBindings();
        }
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public Cursor getChallengeByCategoria(String categoria) {
        Cursor c = db.query(DatabaseHelper.CHALLENGE_TABLE_NAME, new String[]{DatabaseHelper.CHALLENGE_COLUMN_ID, DatabaseHelper.CHALLENGE_COLUMN_TITOLO, DatabaseHelper.CHALLENGE_COLUMN_DESC, DatabaseHelper.CHALLENGE_COLUMN_CREATORE, DatabaseHelper.CHALLENGE_COLUMN_CATEGORIA, DatabaseHelper.CHALLENGE_COLUMN_RATING, DatabaseHelper.CHALLENGE_COLUMN_PUNTEGGIO, DatabaseHelper.CHALLENGE_COLUMN_DATA},
                DatabaseHelper.CHALLENGE_COLUMN_CATEGORIA + " = '" + categoria + "'", null, null, null, null);
        c.moveToFirst();
        return c;
    }

    public Cursor getSingleChallenge(String titolo) {
        return db.query(DatabaseHelper.CHALLENGE_TABLE_NAME, new String[]{DatabaseHelper.CHALLENGE_COLUMN_ID, DatabaseHelper.CHALLENGE_COLUMN_TITOLO, DatabaseHelper.CHALLENGE_COLUMN_DESC, DatabaseHelper.CHALLENGE_COLUMN_CREATORE, DatabaseHelper.CHALLENGE_COLUMN_CATEGORIA, DatabaseHelper.CHALLENGE_COLUMN_RATING, DatabaseHelper.CHALLENGE_COLUMN_PUNTEGGIO, DatabaseHelper.CHALLENGE_COLUMN_DATA},
                DatabaseHelper.CHALLENGE_COLUMN_TITOLO + " = '" + titolo + "'", null, null, null, null);
    }

    public boolean deleteAllChallenge() {
        return db.delete(DatabaseHelper.CHALLENGE_TABLE_NAME, null, null)>0;
    }
}
