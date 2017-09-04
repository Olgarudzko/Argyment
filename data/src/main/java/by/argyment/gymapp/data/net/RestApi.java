package by.argyment.gymapp.data.net;

import java.util.List;

import by.argyment.gymapp.data.entity.Image;
import by.argyment.gymapp.data.entity.Profile;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * @author Olga Rudzko
 */

public interface RestApi {

    @GET("data/profiles?pageSize=100")
    Observable<List<Profile>> getProfiles();

    @GET("data/images?pageSize=100")
    Observable<List<Image>> getImages();

//   @GET("data/images?email={email}")
//    Observable<Image> getImages(@Path("email") String email);

    @POST("data/profiles")
    Observable<Void> addProfile(@Body Profile profile);

    @POST("data/images")
    Observable<Void> addImage(@Body Image image);

    @GET("data/profile?email={email}")
    Observable<List<Profile>> getProfile(@Path("email") String email);

//    @GET("data/profile?email={email}")
//    Observable<Profile> getProfile(@Path("email") String email);

    @PUT("data/profile/{id}")
    Observable<Void> updateProfile(@Body Profile profile, @Path("id") String id);

    @DELETE("data/images/{id}")
    Observable<Void> deleteImage(@Path("id") String id);
}
