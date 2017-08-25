package by.argyment.gymapp.profile;

import android.databinding.ObservableField;

import by.argyment.gymapp.base.BaseItemViewModel;
import by.argyment.gymapp.domain.entity.UserProfile;


/**
 * @author Olga Rudzko
 */

public class SearchItemViewModel extends BaseItemViewModel<UserProfile> {

    public ObservableField<String> name=new ObservableField<>("");

    @Override
    public void setItem(UserProfile item, int position) {
//        name.set(item.getName());
    }
//
//    @Override
//    public String toString() {
//        return "Item ["+name.get()+"]";
//    }

    @Override
    public void init() {
        super.init();
    }

}
