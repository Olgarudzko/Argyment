package by.argyment.gymapp.profile;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import by.argyment.gymapp.domain.entity.UserImage;
import by.argyment.gymapp.domain.entity.UserProfile;
import by.argyment.gymapp.domain.interactions.AddImageUseCase;
import by.argyment.gymapp.domain.interactions.UpdateProfileUseCase;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

/**
 * @author Olga Rudzko
 */

public class MyPage {

    public ObservableField<String> username=new ObservableField<>();
    public ObservableBoolean isAdmin=new ObservableBoolean();
    public ObservableBoolean isTrainer=new ObservableBoolean();
    public ObservableInt status=new ObservableInt();
    public ObservableInt stars=new ObservableInt();
    public ObservableField<String> userpic=new ObservableField<>();
    public ObservableField<String> slon=new ObservableField<>();
    private String password;
    private String email;
    private String objectId;
    private long timeCheckin;
    private long timeStar;

    private static final MyPage instance = new MyPage();

    private MyPage(){}

    public static MyPage getInstance(){
        return instance;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public long getTimeCheckin() {
        return timeCheckin;
    }

    public void setTimeCheckin(long timeCheckin) {
        this.timeCheckin = timeCheckin;
    }

    public long getTimeStar() {
        return timeStar;
    }

    public void setTimeStar(long timeStar) {
        this.timeStar = timeStar;
    }


}
