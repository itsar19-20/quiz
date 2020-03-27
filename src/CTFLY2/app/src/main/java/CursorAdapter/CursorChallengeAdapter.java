package CursorAdapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import DbUtility.DatabaseHelper;
import pro.team.ctfly.R;

public class CursorChallengeAdapter extends CursorAdapter {
    public CursorChallengeAdapter(Context context, Cursor c) {super(context, c);}

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View retview = inflater.inflate(R.layout.list_challenge_layout, parent, false);
        return retview;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView titolo = view.findViewById(R.id.titoloChallengeList);
        TextView autore = view.findViewById(R.id.autoreChallengeList);
        TextView punteggio = view.findViewById(R.id.punteggioChallengeList);
        titolo.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.CHALLENGE_COLUMN_TITOLO)));
        autore.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.CHALLENGE_COLUMN_CREATORE)));
        punteggio.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.CHALLENGE_COLUMN_PUNTEGGIO)));
    }
}
