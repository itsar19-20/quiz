package pro.team.ctfly;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import DbUtility.DbFriendsAdapter;

public class FriendFragment extends Fragment {
    public FriendFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.friends_fragment, container, false);
        DbFriendsAdapter dbAdapt = new DbFriendsAdapter(getParentFragment().getContext());
        dbAdapt.open();
        CursorFriendAdapter friendAdp = new CursorFriendAdapter(getParentFragment().getContext(), dbAdapt.getFriends());
        ListView listView = rootView.findViewById(R.id.friendlist);
        listView.setAdapter(friendAdp);

        return rootView;
    }
}
