package by.argyment.gymapp.data.net;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import by.argyment.gymapp.data.entity.DataBitmap;
import by.argyment.gymapp.data.entity.Image;
import by.argyment.gymapp.data.entity.NewsData;
import by.argyment.gymapp.data.entity.Profile;
import by.argyment.gymapp.data.entity.SlonData;
import by.argyment.gymapp.data.entity.VideoData;
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

    public Observable<Void> addImage (Image image) {
        return api.addImage(image);
    }

    public Observable<Void> addProfile (Profile profile){
        return api.addProfile(profile);
    }

    public Observable <List<Profile>> getProfile(String email) {
        return api.getProfile(email);
    }

    public Observable<Void> updateProfile(Profile profile, String id){
        return api.updateProfile(profile, id);
    }

    public Observable <Void> deleteImage (String id){
        return api.deleteImage(id);
    }

    public Observable<Void> addBitmap (DataBitmap bitmap){
        return api.addBitmap(bitmap.getFile(), bitmap.getName());
    }
    public Observable<List<NewsData>> getNews() {
        return api.getNews();
    }

    public Observable<List<SlonData>> getFreeElephants() {
        return api.getFreeElephants();
    }

    public Observable<Void> addNews(NewsData news) {
        return api.addNews(news);
    }

    public Observable<Void> addSlon(SlonData slon){
        return api.addSlon(slon);
    }

    public Observable<List<VideoData>> getVideo() { return api.getVideo(); }

    public Observable<Void> addVideo(VideoData video){ return  api.addVideo(video); }
}
