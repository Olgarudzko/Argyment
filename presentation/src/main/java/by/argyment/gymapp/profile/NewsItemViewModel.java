package by.argyment.gymapp.profile;

import android.databinding.ObservableField;

import by.argyment.gymapp.base.BaseItemViewModel;
import by.argyment.gymapp.domain.entity.News;

/**
 * @author Olga Rudzko
 */

public class NewsItemViewModel extends BaseItemViewModel<News>{

    public ObservableField<String> title=new ObservableField<String>("");
    public ObservableField<String> picture=new ObservableField<String>("");
    public ObservableField<String>  text=new ObservableField<String>("");

    @Override
    public void setItem(News item, int position) {
        title.set(item.getTitle());
        picture.set(item.getPicture());
        text.set(item.getText());
    }
}
