package by.argyment.gymapp.data.net;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import by.argyment.gymapp.data.entity.Image;
import by.argyment.gymapp.data.entity.NewsData;
import by.argyment.gymapp.data.entity.Profile;
import by.argyment.gymapp.data.entity.SlonData;
import by.argyment.gymapp.data.entity.VideoData;
import by.argyment.gymapp.data.extra.Strings;
import io.reactivex.Observable;

/**
 * @author Olga Rudzko
 */
@Singleton
public class RestService {

    private RestApi api;

    @Inject
    public RestService(RestApi api) {
        this.api = api;
    }

    public Observable<List<Profile>> getProfiles(){
      return api.getProfiles();
    }

    public Observable<List<Image>> getImages(String email){
        return api.getImages(email);
    }

    public Observable<Image> addImage (Image image) {
        return api.addImage(image);
    }

    public Observable<Profile> addProfile (Profile profile){
        return api.addProfile(profile);
    }

    public Observable <List<Profile>> getProfile(String email) {
        return api.getProfile(email);
    }

    public Observable<Profile> updateProfile(Profile profile, String id){
        return api.updateProfile(profile, id);
    }

    public Observable <Long> deleteImage (String id){
        return api.deleteImage(id);
    }

    public Observable<List<NewsData>> getNews() {
        return api.getNews();
    }

    public Observable<List<SlonData>> getFreeElephants() {
        return api.getFreeElephants(Strings.FREESLON);
    }

    public Observable<NewsData> addNews(NewsData news) {
        return api.addNews(news);
    }

    public Observable<SlonData> addSlon(SlonData slon){
        return api.addSlon(slon);
    }

    public Observable<List<VideoData>> getVideo() { return api.getVideo(); }

    public Observable<VideoData> addVideo(VideoData video){ return  api.addVideo(video); }

    public Observable<SlonData> setWinner (SlonData slon, String id) { return api.setWinner(slon, id); }
}
