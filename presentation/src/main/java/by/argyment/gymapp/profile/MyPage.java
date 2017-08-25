package by.argyment.gymapp.profile;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.graphics.drawable.Drawable;

import java.util.List;

import by.argyment.gymapp.base.BasePojo;

/**
 * @author Olga Rudzko
 */

public class MyPage implements BasePojo {

    public ObservableField<String> username;
    public ObservableBoolean isAdmin;
    public ObservableBoolean isTrainer;
    public ObservableInt status;
    public ObservableInt stars;
    public List<Drawable> myPics;
    public ObservableField<Drawable> userpic;
    public ObservableBoolean gotSlon;
    private String password;
    private String email;

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
}
