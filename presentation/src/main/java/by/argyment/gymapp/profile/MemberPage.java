package by.argyment.gymapp.profile;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.view.View;

import by.argyment.gymapp.extra.Strings;

/**
 * @author Olga Rudzko
 */

public class MemberPage{

    public ObservableField<String> username = new ObservableField<>(Strings.EMPTY);
    public ObservableBoolean isTrainer = new ObservableBoolean(false);
    public ObservableInt status = new ObservableInt(0);
    public ObservableBoolean visibility = new ObservableBoolean(false);
    public ObservableField<String> userpic = new ObservableField<>(Strings.EMPTY);
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
