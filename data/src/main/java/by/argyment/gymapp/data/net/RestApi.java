package by.argyment.gymapp.data.net;

import java.util.List;

import by.argyment.gymapp.data.entity.Profile;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * @author Olga Rudzko
 */

public interface RestApi {

    @GET("data/profile")
    Observable<List<Profile>> getProfiles();

    @POST("data/profile")
    Observable<Void> addProfile(@Body Profile profile);


//
//    @GET("data/sourceProfile?id={id}\"")
//    Observable<List<Profile>> getSourceProfiles(@Path("id") String id);
}
