package by.argyment.gymapp.profile;

import android.databinding.ObservableField;

import by.argyment.gymapp.base.BaseItemViewModel;
import by.argyment.gymapp.domain.entity.UserImage;
import by.argyment.gymapp.extra.Strings;

/**
 * @author Olga Rudzko
 */

public class MyPageImgItemViewModel extends BaseItemViewModel<UserImage>{

    public ObservableField<String> picture=new ObservableField<>(Strings.EMPTY);

    @Override
    public void setItem(UserImage item, int position) {
        picture.set(item.getLink());
    }
}
