package Beer.remote;
//ROUTES
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import Beer.Beer;
import retrofit2.http.Query;

public interface PunkAPIService {

    @GET("beers?per_page=10")
    Call<List<Beer>> getListBeer(@Query("page") int page);


    @GET("beers/{id}/")
    Call<List<Beer>> getBeer(@Path("id") int id);

    @GET("beers?&per_page=10")
    Call<List<Beer>> getBeerByName(@Query("beer_name") String beer_name,
                                   @Query("page") int page);

    @GET("beers?per_page=10")
    Call<List<Beer>> getBeerByAbv_Gt(@Query("abv_gt") int abv_gt,
                                     @Query("page") int page);

    @GET("beers?per_page=10")
    Call<List<Beer>> getBeerByNameAndAbv_Gt(@Query("beer_name") String beer_name,
                                            @Query("abv_gt") int abv_gt,
                                            @Query("page") int page);

    @GET("beers")
    Call<List<Beer>> getAllBeer();


}