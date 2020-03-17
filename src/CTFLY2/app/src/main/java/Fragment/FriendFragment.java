package Fragment;

import android.app.AlertDialog;
import android.content.Intent;
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
import Dialog.RemoveFriend;
import Dialog.UserNotFoundDialog;
import pro.team.ctfly.R;

public class FriendFragment extends Fragment {
    private static int REMOVE_CODE = 1;
    private static int ADD_CODE = 2;
    private static long itemid;
    private DbFriendsAdapter dbAdapt;
    private CursorFriendAdapter friendAdp;
    private ListView listView;
    private UserNotFoundDialog dialog = new UserNotFoundDialog();
    private AddFriendDialog addDialog = new AddFriendDialog();
    private RemoveFriend removeDialog = new RemoveFriend();

    public FriendFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.friends_fragment, container, false);
        dbAdapt = new DbFriendsAdapter(getActivity());
        dbAdapt.open();
        friendAdp = new CursorFriendAdapter(getActivity(), dbAdapt.getFriends());
        listView = rootView.findViewById(R.id.friendlist);
        listView.setAdapter(friendAdp);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView text = view.findViewById(R.id.usernamefriendlist);
                removeDialog.setTargetFragment(FriendFragment.this, REMOVE_CODE);
                Bundle bnl = new Bundle();
                bnl.putString("username", text.getText().toString());
                bnl.putLong("id", id);
                removeDialog.setArguments(bnl);
                removeDialog.show(getFragmentManager(), "RemovingFriend");
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
                addDialog.setCancelable(false);
                addDialog.setTargetFragment(FriendFragment.this, ADD_CODE);
                addDialog.show(getFragmentManager(), "AddNewFriend");
            }
        });
        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==REMOVE_CODE) {
            if(resultCode==1) {
                Bundle bnlResult = data.getExtras();
                boolean check = bnlResult.getBoolean("check");
                if(check) {
                    dbAdapt.removeFriend(bnlResult.getLong("id"));
                    friendAdp.swapCursor(dbAdapt.getFriends());
                }
            }
        } else if (requestCode==ADD_CODE) {
            if(resultCode==1) {
                friendAdp.swapCursor(dbAdapt.getFriends());
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        dbAdapt.removeAllFriend();
    }

}
