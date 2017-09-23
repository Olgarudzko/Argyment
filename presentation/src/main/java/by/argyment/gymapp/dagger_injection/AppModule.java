package by.argyment.gymapp.dagger_injection;

import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import by.argyment.gymapp.data.extra.Strings;
import by.argyment.gymapp.data.net.RestApi;
import by.argyment.gymapp.data.net.RestService;
import by.argyment.gymapp.domain.interactions.AddBitmapUseCase;
import by.argyment.gymapp.domain.interactions.AddElephantUseCase;
import by.argyment.gymapp.domain.interactions.AddImageUseCase;
import by.argyment.gymapp.domain.interactions.AddNewsUseCase;
import by.argyment.gymapp.domain.interactions.AddUserUseCase;
import by.argyment.gymapp.domain.interactions.AddVideoUseCase;
import by.argyment.gymapp.domain.interactions.DeleteImageUseCase;
import by.argyment.gymapp.domain.interactions.GetFreeElephantsUseCase;
import by.argyment.gymapp.domain.interactions.GetImageListUseCase;
import by.argyment.gymapp.domain.interactions.GetNewsUseCase;
import by.argyment.gymapp.domain.interactions.GetProfileListUseCase;
import by.argyment.gymapp.domain.interactions.GetProfileUseCase;
import by.argyment.gymapp.domain.interactions.GetVideoUseCase;
import by.argyment.gymapp.domain.interactions.UpdateProfileUseCase;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Olga Rudzko
 */
@Module
public class AppModule {
    @Provides
    @Singleton
    public RestService getRest(RestApi api){ return new RestService(api); }

    @Provides
    public RestApi getRestApi(Retrofit retrofit){
        return retrofit.create(RestApi.class);
    }

    @Provides
    public Retrofit getRetrofit(OkHttpClient client){
        return new Retrofit.Builder()
                .baseUrl(Strings.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .client(client).build();
    }

    @Provides
    public OkHttpClient getClient(HttpLoggingInterceptor logging){
        return new OkHttpClient.Builder().readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS).addInterceptor(logging).build();
    }

    @Provides
    public HttpLoggingInterceptor getLogging(){
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    public GetProfileListUseCase getProfileList(RestService rest){
        return new  GetProfileListUseCase(rest);
    }

    @Provides
    public GetProfileUseCase getProfile(RestService rest){ return new GetProfileUseCase(rest); }

    @Provides
    public AddUserUseCase addUser(RestService rest){
        return new AddUserUseCase(rest);
    }

    @Provides
    public GetImageListUseCase getImages(RestService rest){
        return new GetImageListUseCase(rest);
    }

    @Provides
    public UpdateProfileUseCase updateUser(RestService rest){
        return new UpdateProfileUseCase(rest);
    }

    @Provides
    public AddImageUseCase addImg(RestService rest){
        return new AddImageUseCase(rest);
    }

    @Provides
    public AddBitmapUseCase addBitmap(RestService rest){
        return new AddBitmapUseCase(rest);
    }

    @Provides
    public DeleteImageUseCase removeImg(RestService rest){
        return new DeleteImageUseCase(rest);
    }

    @Provides
    public GetNewsUseCase getNews(RestService rest){ return new GetNewsUseCase(rest); }

    @Provides
    public GetFreeElephantsUseCase getElephants(RestService rest){ return new GetFreeElephantsUseCase(rest); }

    @Provides
    public AddNewsUseCase addNewsUseCase(RestService rest){
        return new AddNewsUseCase(rest);
    }

    @Provides
    public AddElephantUseCase addSlon(RestService rest){
        return new AddElephantUseCase(rest);
    }

    @Provides
    public GetVideoUseCase getVideo(RestService rest){ return new GetVideoUseCase(rest); }

    @Provides
    public AddVideoUseCase addVideo(RestService rest) { return new AddVideoUseCase(rest); }
}
