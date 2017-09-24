package by.argyment.gymapp.profile;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import by.argyment.gymapp.base.BaseItemViewModel;
import by.argyment.gymapp.extra.Strings;
import by.argyment.gymapp.domain.entity.Video;

/**
 * @author Olga Rudzko
 */

public class VlogItemViewModel extends BaseItemViewModel<Video>{

    public ObservableField<String> url=new ObservableField<>(Strings.EMPTY);
    public ObservableField<String> title=new ObservableField<>(Strings.EMPTY);
    public ObservableInt random=new ObservableInt(0);
    @Override
    public void setItem(Video item, int position) {
        url.set(item.getUrl());
        title.set(item.getTitle());
        random.set(1+(int)(Math.random()*5));
    }
}
