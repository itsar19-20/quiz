package Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import pro.team.ctfly.R;

public class ProfileImageFragment extends Fragment {
Button btnGallery;
Button btnCamera;
Button btnDelete;
Button btnBack;
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.activity_profile_image_fragment, container, false);
        btnGallery=rootView.findViewById(R.id.btnGallery);
        btnCamera=rootView.findViewById(R.id.btnCamera);
        btnDelete=rootView.findViewById(R.id.btnDelete);
        btnBack=rootView.findViewById(R.id.btnBack);
        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Scegli dalla galleria", Toast.LENGTH_SHORT).show();
            }
        });
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Scegli dalla fotocamera", Toast.LENGTH_SHORT).show();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Rimosso", Toast.LENGTH_SHORT).show();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"back", Toast.LENGTH_SHORT).show();
                FragmentTransaction transaction=getFragmentManager().beginTransaction();
                transaction.replace(R.id.containerfragment,new ProfileFragment()).commit();
            }
        });
        return  rootView;
    }

}
