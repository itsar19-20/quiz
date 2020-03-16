package webService;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface MyApiEndpointInterface {
@GET("/java/model/Utente")
    Call<List<Utente>>getUsers();

@GET("/java/model/Utente/{username}")
    Call<Utente> getUser(@Path("username") String username );









}
