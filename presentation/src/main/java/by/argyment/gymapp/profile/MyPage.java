package by.argyment.gymapp.profile;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import by.argyment.gymapp.extra.Strings;

/**
 * @author Olga Rudzko
 */

public class MyPage {

    public ObservableField<String> username = new ObservableField<>(Strings.EMPTY);
    public ObservableBoolean isAdmin = new ObservableBoolean(false);
    public ObservableBoolean isTrainer = new ObservableBoolean(false);
    public ObservableInt status = new ObservableInt(0);
    public ObservableField<String> userpic = new ObservableField<>(Strings.EMPTY);
    public ObservableField<String> slon = new ObservableField<>(Strings.NO);
    private String password;
    private String email;
    private String objectId;
    private long timeCheckin;

    private static final MyPage instance = new MyPage();

    private MyPage() {
    }

    public static MyPage getInstance() {
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
}
