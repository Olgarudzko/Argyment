package by.argyment.gymapp.dagger_injection;

import javax.inject.Singleton;

import by.argyment.gymapp.greeting.GreetModel;
import by.argyment.gymapp.profile.MyPageHandler;
import by.argyment.gymapp.profile.ProfileModel;
import by.argyment.gymapp.profile.SearchHandler;
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
}