package by.argyment.gymapp.greeting;

import by.argyment.gymapp.base.BaseViewModel;
import by.argyment.gymapp.domain.entity.UserProfile;
import by.argyment.gymapp.domain.interactions.AddUserUseCase;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

/**
 * @author Olga Rudzko
 */

public class GreetModel implements BaseViewModel {

    public ObservableField<String> name = new ObservableField<>();
    ObservableField<String> email = new ObservableField<>();
    private ObservableField<String> password = new ObservableField<>();
    public ObservableBoolean done = new ObservableBoolean(false);
    private AddUserUseCase addUser = new AddUserUseCase();

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
        if (name.get()==null) done.set(false);
    }

    @Override
    public void resume() {

    }

    @Override
    public void release() {

    }

    @Override
    public void pause() {

    }

    public void addUser() {
        UserProfile newUser = new UserProfile();
        newUser.setUsername(name.get());
        newUser.setEmail(email.get());
        newUser.setPassword(password.get());
        newUser.setTrainer(false);
        newUser.setAdmin(false);
        newUser.setUserpic("https://goo.gl/hTFuC4");
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

    public void findUser() {
//TODO makeRequest, onComplete -> done=true
    }
}
