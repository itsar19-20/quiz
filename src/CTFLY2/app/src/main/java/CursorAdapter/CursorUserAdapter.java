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

public class CursorUserAdapter extends CursorAdapter {
    public CursorUserAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View v=inflater.inflate(R.layout.list_layout_user_points,parent,false);
        return  v;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView number=view.findViewById(R.id.numero);
        Integer count = cursor.getPosition()+1;
        number.setText(count.toString());
        TextView username= view.findViewById(R.id.userList);
        username.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_COLUMN_USERNAME)));
        TextView punteggio= view.findViewById(R.id.userPunteggio);
        punteggio.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_COLUMN_PUNTEGGIO)));

    }
}
