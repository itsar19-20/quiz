package webService;
import java.util.List;

import Model.Utente;
import retrofit2.Call;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MyApiEndpointInterface {
    @GET("/uc")
    Call<List<Utente>> getUsers();

    @FormUrlEncoded
    @POST("/loginApp")
    Call<Utente> getUser(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("signUp")
    Call<Utente> addUser(@Field("username") String username, @Field("email") String email, @Field("password") String password);
}
