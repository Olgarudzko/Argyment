package by.argyment.gymapp.data.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

import by.argyment.gymapp.data.entity.Image;
import by.argyment.gymapp.data.entity.Profile;
import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Olga Rudzko
 */

public class RestService {

    private static final RestService instance = new RestService();
    private RestApi api;
    private RestService(){
        init();
    }
    public static RestService getInstance(){
        return instance;
    }
        private void init(){

            HttpLoggingInterceptor logging=new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client= new OkHttpClient.Builder().readTimeout(10, TimeUnit.SECONDS)
                    .connectTimeout(10, TimeUnit.SECONDS).addInterceptor(logging).build();

            Gson gson = new GsonBuilder().create();

            Retrofit retrofit=new Retrofit.Builder()
                    .baseUrl("https://api.backendless.com/FCBFF78E-1D57-5C7C-FF9A-7A8C1078C400/175DDE14-033B-C914-FFF8-D66210C89700/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson)).client(client).build();
            api=retrofit.create(RestApi.class);
        }

    public Observable<List<Profile>> getProfiles(){
      return api.getProfiles();
    }

    public Observable<List<Image>> getImages(){
        return api.getImages();
    }

    public Observable<Void> addImage (Image image) {
        return api.addImage(image);
    }

    public Observable<Void> addProfile (Profile profile){
        return api.addProfile(profile);
    }

    public Observable<List<Profile>> getProfile(String email) {
        return api.getProfile(email);
    }

//
//    public Observable<Profile> getProfile(String email) {
//        return api.getProfile(email);
//    }

    public Observable<Void> updateProfile(Profile profile, String id){
        return api.updateProfile(profile, id);
    }

    public Observable <Void> deleteImage (String id){
        return api.deleteImage(id);
    }
}
