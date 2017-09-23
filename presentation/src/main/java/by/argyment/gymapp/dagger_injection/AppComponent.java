package by.argyment.gymapp.dagger_injection;

import javax.inject.Singleton;

import by.argyment.gymapp.greeting.GreetModel;
import by.argyment.gymapp.profile.MyPageHandler;
import by.argyment.gymapp.profile.NewsHandler;
import by.argyment.gymapp.profile.ProfileModel;
import by.argyment.gymapp.profile.SearchHandler;
import by.argyment.gymapp.profile.VlogHandler;
import dagger.Component;

/**
 * @author Olga Rudzko
 */


@Component (modules={AppModule.class})
@Singleton
public interface AppComponent {
    void injectGreetModel(GreetModel model);
    void injectProfileModel (ProfileModel model);
    void injectMyPageHandler(MyPageHandler handler);
    void injectSearchHandler(SearchHandler handler);
    void injectNewsHandler(NewsHandler handler);
    void injectVlogFragment(VlogHandler handler);
}
