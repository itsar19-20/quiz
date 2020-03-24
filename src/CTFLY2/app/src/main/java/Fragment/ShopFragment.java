package Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import Dialog.DonateDialog;
import pro.team.ctfly.R;

public class ShopFragment extends Fragment {
    private DonateDialog dialog = new DonateDialog();
    public ShopFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.shop_fragment, container, false);
        Button shopBtn = rootView.findViewById(R.id.DonaBtn);
        shopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show(getFragmentManager(), "Donate");
            }
        });
        return rootView;
    }
}
