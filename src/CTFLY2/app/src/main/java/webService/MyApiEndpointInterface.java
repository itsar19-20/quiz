package webService;
import java.util.List;

import Model.Utente;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface MyApiEndpointInterface {
@GET("/java/model/Utente")
    Call<List<Utente>>getUsers();

@GET("/java/model/Utente/{username}")
    Call<Utente> getUser(@Path("username") String username );









}
