package by.argyment.gymapp.greeting;

import by.argyment.gymapp.GymApplication;
import by.argyment.gymapp.base.BaseViewModel;
import by.argyment.gymapp.domain.entity.UserProfile;
import by.argyment.gymapp.domain.interactions.AddUserUseCase;
import by.argyment.gymapp.domain.interactions.GetProfileListUseCase;
import by.argyment.gymapp.extra.Strings;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

/**
 * @author Olga Rudzko
 */

public class GreetModel implements BaseViewModel {

    @Inject
    GetProfileListUseCase getProfileList;
    @Inject
    AddUserUseCase addUser;

    public ObservableField<String> name = new ObservableField<>();
    public ObservableBoolean done = new ObservableBoolean(false);
    public ObservableInt checkin=new ObservableInt();
    ObservableField<String> email = new ObservableField<>();
    private ObservableField<String> password = new ObservableField<>();
    HashMap<String, String> users=new HashMap<>();
    HashMap<String, Long> checkins=new HashMap<>();

    public GreetModel() {
        GymApplication.appComponent.injectGreetModel(this);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    @Override
    public void init() {

    }

    @Override
    public void resume() {
        if (name.get()==null) done.set(false);
        getProfileList.makeRequest(null, new DisposableObserver<List<UserProfile>>() {
            @Override
            public void onNext(@NonNull List<UserProfile> userProfiles) {
                for (UserProfile user: userProfiles) {
                    users.put(user.getEmail(), user.getPassword());
                    checkins.put(user.getEmail(), user.getTimeCheckin());
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
 @Override
    public void pause() {

    }

    @Override
    public void release() {
        addUser.dispose();
        getProfileList.dispose();
    }


    public void addUser() {
        UserProfile newUser = new UserProfile();
        newUser.setUsername(name.get());
        newUser.setEmail(email.get());
        newUser.setPassword(password.get());
        newUser.setTrainer(false);
        newUser.setAdmin(false);
        newUser.setUserpic(Strings.DEFAULT_IMG);
        newUser.setStatus(0);
        newUser.setStars(0);
        newUser.setSlon(null);
        addUser.makeRequest(newUser, new DisposableObserver<Void>() {
            @Override
            public void onNext(@NonNull Void aVoid) {
            }

            @Override
            public void onError(@NonNull Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });
    }
}
