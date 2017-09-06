package by.argyment.gymapp.profile;

import android.databinding.ObservableField;

import by.argyment.gymapp.base.BaseItemViewModel;
import by.argyment.gymapp.domain.entity.UserImage;

/**
 * @author Olga Rudzko
 */

public class MyPageImgItemViewModel extends BaseItemViewModel<UserImage>{

    public ObservableField<String> picture=new ObservableField<>("");

    @Override
    public void setItem(UserImage item, int position) {
        picture.set(item.getLink());
    }
}
