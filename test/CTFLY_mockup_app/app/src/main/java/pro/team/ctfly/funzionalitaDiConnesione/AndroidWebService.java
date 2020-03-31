package pro.team.ctfly.funzionalitaDiConnesione;

import android.app.Application;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AndroidWebService extends Application {

    //RICORDATEVI CHE PER FAR FUNZIONARE IL TUTTO DOVE METTERE IL TELEFONO IN MODALITÀ AEREO C
    //COL WIFI ACCESSO

    public static final  String  IP_CANDA ="192.168.0.107";
    public static final String IP_MATTEO="";
    public static final String IP_DAVIDE="";
    public static Retrofit retrofit;
     public static int flag;
    @Override
    public void onCreate(){
        super.onCreate();

        if(flag ==1) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://"+IP_CANDA+":8080")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        if(flag ==2) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://"+IP_MATTEO+":80.80")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        if(flag ==3) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://"+IP_DAVIDE+":80.80")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }




    }



}
