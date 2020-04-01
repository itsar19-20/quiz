package Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import CursorAdapter.CursorChallengeAdapter;
import DbUtility.DbChallengeAdapter;
import Model.ChallengeAppSide;
import Model.ChallengeServerSide;
import pro.team.ctfly.GameActivity;
import pro.team.ctfly.GeneraQuizActivity;
import pro.team.ctfly.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import webService.AndroidWebService;
import webService.MyApiEndpointInterface;
import com.google.android.material.tabs.TabLayout;

public class HomeFragment extends Fragment {
    private DbChallengeAdapter dbAdapter;
    private CursorChallengeAdapter cursorChallenge;

    public HomeFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_homepage, container, false);
        dbAdapter = new DbChallengeAdapter(getActivity());
        Button createChallenge = rootview.findViewById(R.id.createChallengeBtn);
        TabLayout tab = rootview.findViewById(R.id.tabChallengeLayout);
        ProgressBar loading = rootview.findViewById(R.id.challengeLoading);
        String categoriaCorrente = tab.getTabAt(tab.getSelectedTabPosition()).getText().toString();
        Log.d("TABSELECTED ", "" + categoriaCorrente);
        ListView list = rootview.findViewById(R.id.challengeListView);
        createChallenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), GeneraQuizActivity.class));
            }
        });
        MyApiEndpointInterface apiService = AndroidWebService.getRetrofit().create(MyApiEndpointInterface.class);
        Call<List<ChallengeServerSide>> call = apiService.getAllChallenge();
        call.enqueue(new Callback<List<ChallengeServerSide>>() {
            @Override
            public void onResponse(Call<List<ChallengeServerSide>> call, Response<List<ChallengeServerSide>> response) {
                List<ChallengeServerSide> challengesSS = response.body();
                List<ChallengeAppSide> challengesAS = new ArrayList<ChallengeAppSide>();
                for(int x=0; x<challengesSS.size(); x++) {
                    Integer rating = challengesSS.get(x).getRating();
                    Integer punteggio = challengesSS.get(x).getPunteggio();
                    ChallengeAppSide challenge = new ChallengeAppSide(challengesSS.get(x).getTitolo(), challengesSS.get(x).getDescrizione(), challengesSS.get(x).getCreatore().getUsername(), challengesSS.get(x).getCategoria(), rating.toString(), punteggio.toString(), challengesSS.get(x).getData());
                    challengesAS.add(challenge);
                }
                dbAdapter.open();
                dbAdapter.insertFast(challengesAS.size(), challengesAS);
                cursorChallenge = new CursorChallengeAdapter(getActivity(), dbAdapter.getChallengeByCategoria(categoriaCorrente));
                list.setAdapter(cursorChallenge);
                loading.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<List<ChallengeServerSide>> call, Throwable t) {
                Toast.makeText(getActivity(), "Collegamento al server fallito", Toast.LENGTH_LONG).show();
            }
        });

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String categoriaCorrente = tab.getText().toString();
                Log.d("TABSELECTED ", "" + categoriaCorrente);
                cursorChallenge.swapCursor(dbAdapter.getChallengeByCategoria(categoriaCorrente));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                String categoriaCorrente = tab.getText().toString();
                Log.d("TABSELECTED ", "" + categoriaCorrente);
                cursorChallenge.swapCursor(dbAdapter.getChallengeByCategoria(categoriaCorrente));
            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView titolo = view.findViewById(R.id.titoloChallengeList);
                Intent i = new Intent(getActivity(), GameActivity.class);
                i.putExtra("titolo", titolo.getText().toString());
                startActivity(i);
            }
        });
        return rootview;
    }

}
