package by.argyment.gymapp;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import by.argyment.gymapp.dagger_injection.AppComponent;
import by.argyment.gymapp.dagger_injection.AppModule;
import by.argyment.gymapp.dagger_injection.DaggerAppComponent;

/**
 * @author Olga Rudzko
 */

public class GymApplication extends Application {

    public static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
        appComponent = DaggerAppComponent.builder().appModule(new AppModule()).build();
    }
}
