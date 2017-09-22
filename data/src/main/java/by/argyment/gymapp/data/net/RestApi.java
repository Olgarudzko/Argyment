package by.argyment.gymapp.data.net;

import java.io.File;
import java.util.List;

import by.argyment.gymapp.data.entity.Image;
import by.argyment.gymapp.data.entity.NewsData;
import by.argyment.gymapp.data.entity.Profile;
import by.argyment.gymapp.data.entity.SlonData;
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

    @GET(Strings.IMAGES)
    Observable<List<Image>> getImages(@Query(Strings.WHERE) String email);

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

    @PUT("files/UsersImages/{name}")
    Observable<Void> addBitmap(@Body File file, @Path("name") String name );

    @GET("data/news")
    Observable <List<NewsData>> getNews();

    @POST("data/news")
    Observable<Void> addNews(@Body NewsData news);

    @GET("data/elephants?where=winner='no'")
    Observable<List<SlonData>> getFreeElephants();

    @POST("data/elephants")
    Observable<Void> addSlon(@Body SlonData slon);
}
