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

public class CursorFriendAdapter extends CursorAdapter {
    public CursorFriendAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View retView = inflater.inflate(R.layout.list_friend_layout, parent, false);
        return retView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView numero = view.findViewById(R.id.numberfriendlist);
        Integer count = cursor.getPosition()+1;
        numero.setText(count.toString());
        TextView username = view.findViewById(R.id.usernamefriendlist);
        username.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.FRIEND_COLUMN_USERNAME)));
        TextView punteggio = view.findViewById(R.id.punteggiofriendlist);
        punteggio.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.FRIEND_COLUMN_PUNTEGGIO)));
    }
}
