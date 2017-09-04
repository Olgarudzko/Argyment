package by.argyment.gymapp.profile;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;

import by.argyment.gymapp.base.BaseItemViewModel;
import by.argyment.gymapp.domain.entity.UserProfile;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;


/**
 * @author Olga Rudzko
 */

public class SearchItemViewModel extends BaseItemViewModel<UserProfile> {

    public ObservableField<String> name=new ObservableField<>("");
    public String picture;

    @Override
    public void setItem(UserProfile item, int position) {
        name.set(item.getUsername());
        picture=item.getUserpic();
    }

    @Override
    public void init() {
        super.init();
    }

}
