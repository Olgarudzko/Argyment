package by.argyment.gymapp.base;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * @author Olga Rudzko
 */

public abstract class BaseFragment extends Fragment {

    @BindingAdapter({"bind:main_image"})
    public static void setMainPhoto(ImageView view, ObservableField<String> url) {
        if (!(url.get()==null && url.get().equals(""))) {
            Glide.with(view.getContext()).load(url.get()).into(view);
        }
    }

    @BindingAdapter({"bind:item_image"})
    public static void loadImg(ImageView view, String url) {
        if (!url.equals("")) {
            Glide.with(view.getContext()).load(url)
                    .bitmapTransform(new CenterCrop(view.getContext()),
                            new RoundedCornersTransformation(view.getContext(), 30, 0)).into(view);
        }
    }
}
