package webService;
import java.util.List;

import Model.Utente;
import retrofit2.Call;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MyApiEndpointInterface {
    @GET("/uc")
    Call<Utente> getUser(@Query("username") String username);

    @FormUrlEncoded
    @POST("/uc")
    Call<List<Utente>> getAllUser(@Field("action")String search);
    @FormUrlEncoded
    @POST("/addfriend")
    Call<Boolean> addFriend(@Field("username") String username, @Field("usernameFriend") String usernameFriend);

    @FormUrlEncoded
    @POST("/deletefriend")
    Call<Boolean> deleteFriend(@Field("username") String username, @Field("usernameFriend") String usernameFriend);

    @FormUrlEncoded
    @POST("/friendlist")
    Call<List<Utente>> getFriends(@Field("username") String username);

    @FormUrlEncoded
    @POST("/loginApp")
    Call<Utente> getUserLogin(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("signUp")
    Call<Utente> addUser(@Field("username") String username, @Field("email") String email, @Field("password") String password);
}
