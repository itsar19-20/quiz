package webService;
import android.app.Application;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;




public class AndroidWebService extends Application {
    public static final String BASE_URL = "http://localhost.127.0.0.1:8080";
    public static Retrofit retrofit;


    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
