package by.argyment.gymapp.data.net;

import java.util.List;

import by.argyment.gymapp.data.entity.Image;
import by.argyment.gymapp.data.entity.Profile;
import by.argyment.gymapp.data.extra.Strings;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author Olga Rudzko
 */

public interface RestApi {

    @GET(Strings.PROFILE_100_PAGES)
    Observable<List<Profile>> getProfiles();

    @GET(Strings.IMAGES_100_PAGE)
    Observable<List<Image>> getImages();

    @GET(Strings.IMAGES)
    Observable<Image> getImages(@Query(Strings.WHERE) String email);

    @POST(Strings.PROFILES)
    Observable<Void> addProfile(@Body Profile profile);

    @POST(Strings.IMAGES)
    Observable<Void> addImage(@Body Image image);

    @GET(Strings.PROFILES)
    Observable<List<Profile>> getProfile(@Query(Strings.WHERE) String data);

    @PUT(Strings.UPDATE_PROFILES)
    Observable<Void> updateProfile(@Body Profile profile, @Path("id") String id);

    @DELETE("data/images/{id}")
    Observable<Void> deleteImage(@Path("id") String id);
}
