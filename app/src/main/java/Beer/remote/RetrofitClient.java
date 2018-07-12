package Beer.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static Beer.remote.ControlBeerList.BASE_URL;

public class RetrofitClient {

    private static Retrofit retrofit = null;

    private static final String BASE_URL = "https://api.punkapi.com/v2/";



    public static Retrofit getRetrofitClient(){
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
    }










//    public static Retrofit getClient(String baseUrl) {
//        if (retrofit==null) {
////            Gson gson = new GsonBuilder()
////                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
////                    .create();
//
//            Retrofit retrofit = new Retrofit.Builder()
//                    .baseUrl(baseUrl)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//        }
//        return retrofit;
