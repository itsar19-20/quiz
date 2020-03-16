package Fragment;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import DbUtility.DbFriendsAdapter;
import CursorAdapter.CursorFriendAdapter;
import Dialog.AddFriendDialog;
import Dialog.UserNotFoundDialog;
import pro.team.ctfly.R;

public class FriendFragment extends Fragment {
    private static long itemid;
    private DbFriendsAdapter dbAdapt;
    private UserNotFoundDialog dialog = new UserNotFoundDialog();
    private AddFriendDialog addDialog = new AddFriendDialog();

    public FriendFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.friends_fragment, container, false);
        dbAdapt = new DbFriendsAdapter(getActivity());
        dbAdapt.open();
        CursorFriendAdapter friendAdp = new CursorFriendAdapter(getActivity(), dbAdapt.getFriends());
        ListView listView = rootView.findViewById(R.id.friendlist);
        listView.setAdapter(friendAdp);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dbAdapt.removeFriend(id);
                friendAdp.swapCursor(dbAdapt.getFriends());
            }
        });
        ImageButton searchBtn = rootView.findViewById(R.id.searchbtnfriendlist);
        TextView filter = rootView.findViewById(R.id.searchfriendlist);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c = dbAdapt.getSingleFriend(filter.getText().toString());
                if(c.moveToFirst()) {
                    friendAdp.swapCursor(c);
                } else {
                    dialog.show(getFragmentManager(), "UserNotFound");
                    filter.setText("");
                }
            }
        });
        ImageButton addBtn = rootView.findViewById(R.id.addfriend);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDialog.show(getFragmentManager(), "AddNewFriend");
            }
        });
        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
        dbAdapt.removeAllFriend();
    }

}
