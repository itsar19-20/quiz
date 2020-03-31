package pro.team.ctfly.funzionalitaDiConnesione;
import java.util.List;

import pro.team.ctfly.model.Challenge;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MyApplicationEndPointInterface {

   @GET("/cc")
    Call<List<Challenge>> getChallenges();

}
