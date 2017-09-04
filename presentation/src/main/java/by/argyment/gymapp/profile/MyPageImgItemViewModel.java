package by.argyment.gymapp.profile;

import by.argyment.gymapp.base.BaseItemViewModel;
import by.argyment.gymapp.domain.entity.UserImage;

/**
 * @author Olga Rudzko
 */

public class MyPageImgItemViewModel extends BaseItemViewModel<UserImage>{

    public String picture;

    @Override
    public void setItem(UserImage item, int position) {
        picture=item.getLink();
    }
}
