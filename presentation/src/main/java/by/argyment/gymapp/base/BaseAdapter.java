package by.argyment.gymapp.base;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;

import java.util.ArrayList;
import java.util.List;

import by.argyment.gymapp.extra.Strings;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * @author Olga Rudzko
 */

public abstract class BaseAdapter<Model,
        ViewModel extends BaseItemViewModel<Model>>
        extends RecyclerView.Adapter<BaseItemViewHolder<Model, ViewModel, ?>> {


    protected List<Model> items = new ArrayList<>();

    public BaseAdapter() {}

    public void setItems(List<Model> newItems) {
        items.clear();
        items.addAll(newItems);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(BaseItemViewHolder<Model, ViewModel, ?> holder, int position) {
        Model item = items.get(position);
        holder.bindTo(item, position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @BindingAdapter({Strings.BIND_MAIN_IMAGE})
    public static void setMainPhoto(ImageView view, String url) {
        if (url!=null && !url.isEmpty()) {
            Glide.with(view.getContext()).load(url).into(view);
        }
    }

    @BindingAdapter({Strings.BIND_SET_IMAGE})
    public static void setImg(ImageView view, String url) {
        if (url!=null && !url.isEmpty()) {
            Glide.with(view.getContext()).load(url)
                    .bitmapTransform(new CenterCrop(view.getContext()),
                            new RoundedCornersTransformation(view.getContext(), 10, 0)).into(view);
        }
    }

    @BindingAdapter({Strings.BIND_ROUND})
    public static void setRoundImg(ImageView view, String url){
        if (url!=null && !url.isEmpty()) {
            Glide.with(view.getContext()).load(url)
                    .bitmapTransform(new CropCircleTransformation(view.getContext())).into(view);
        }
    }
}
