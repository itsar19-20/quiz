package webService;
import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;




public class AndroidWebService {
    private static final String BASE_URL = "http://192.168.1.5:8080";
    private static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

}
