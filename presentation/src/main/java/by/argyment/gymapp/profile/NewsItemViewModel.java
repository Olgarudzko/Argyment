package by.argyment.gymapp.profile;

import android.databinding.ObservableField;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import by.argyment.gymapp.base.BaseItemViewModel;
import by.argyment.gymapp.domain.entity.News;
import by.argyment.gymapp.extra.Strings;

/**
 * @author Olga Rudzko
 */

public class NewsItemViewModel extends BaseItemViewModel<News>{

    public ObservableField<String> title=new ObservableField<String>("");
    public ObservableField<String> picture=new ObservableField<String>("");
    public ObservableField<String>  text=new ObservableField<String>("");
    public ObservableField<String> date=new ObservableField<>("");

    @Override
    public void setItem(News item, int position) {
        title.set(item.getTitle());
        picture.set(item.getPicture());
        text.set(item.getText());
        Date created=new Date(item.getTime());
        SimpleDateFormat format=new SimpleDateFormat(Strings.DATE, Locale.ENGLISH);
        date.set(format.format(created));
    }
}
