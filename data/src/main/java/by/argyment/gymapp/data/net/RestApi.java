package by.argyment.gymapp.data.net;

import java.io.File;
import java.util.List;

import by.argyment.gymapp.data.entity.Image;
import by.argyment.gymapp.data.entity.NewsData;
import by.argyment.gymapp.data.entity.Profile;
import by.argyment.gymapp.data.entity.SlonData;
import by.argyment.gymapp.data.entity.VideoData;
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
    Observable<Profile> addProfile(@Body Profile profile);

    @POST(Strings.IMAGES)
    Observable<Image> addImage(@Body Image image);

    @GET(Strings.PROFILES)
    Observable<List<Profile>> getProfile(@Query(Strings.WHERE) String data);

    @PUT(Strings.UPDATE_PROFILES)
    Observable<Profile> updateProfile(@Body Profile profile, @Path(Strings.ID) String id);

    @DELETE(Strings.DELETE_IMAGE)
    Observable<Long> deleteImage(@Path(Strings.ID) String id);

    @GET(Strings.NEWS_100_PAGES)
    Observable <List<NewsData>> getNews();

    @POST(Strings.NEWS)
    Observable<NewsData> addNews(@Body NewsData news);

    @GET(Strings.ELEPHANTS)
    Observable<List<SlonData>> getFreeElephants(@Query(Strings.WHERE) String data);

    @POST(Strings.ELEPHANTS)
    Observable<SlonData> addSlon(@Body SlonData slon);

    @PUT(Strings.UPDATE_SLON)
    Observable<SlonData> setWinner(@Body SlonData slon, @Path(Strings.ID) String id);

    @GET(Strings.VLOG_100_PAGES)
    Observable<List<VideoData>> getVideo();

    @POST(Strings.VLOG)
    Observable<VideoData> addVideo(@Body VideoData video);
}
