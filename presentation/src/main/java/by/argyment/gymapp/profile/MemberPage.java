package by.argyment.gymapp.profile;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.view.View;

import java.util.List;

import by.argyment.gymapp.domain.entity.UserImage;

/**
 * @author Olga Rudzko
 */

public class MemberPage{

    public ObservableField<String> username = new ObservableField<>("");
    public ObservableBoolean isTrainer = new ObservableBoolean(false);
    public ObservableInt status = new ObservableInt(0);
    public ObservableInt stars = new ObservableInt(0);
    public ObservableBoolean visibility = new ObservableBoolean(false);
    public List<UserImage> memberPics;
    public ObservableField<String> userpic = new ObservableField<>("");
    private String objectId;

    private static final MemberPage instance = new MemberPage();

    private MemberPage(){}

    public static MemberPage getInstance(){
        return instance;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public void showSearch(View view){
        visibility.set(false);
    }
}
