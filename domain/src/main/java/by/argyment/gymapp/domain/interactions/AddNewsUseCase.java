package by.argyment.gymapp.domain.interactions;

import javax.inject.Inject;

import by.argyment.gymapp.data.entity.NewsData;
import by.argyment.gymapp.data.net.RestService;
import by.argyment.gymapp.domain.entity.News;
import by.argyment.gymapp.domain.interactions.base.UseCase;
import io.reactivex.Observable;

/**
 * @author Olga Rudzko
 */

public class AddNewsUseCase extends UseCase<News, Void> {

    RestService rest;

    @Inject
    public AddNewsUseCase(RestService rest) {
        this.rest = rest;
    }

    @Override
    protected Observable<Void> buildUseCase(News param) {
        NewsData data=new NewsData();
        data.setTitle(param.getTitle());
        data.setPicture(param.getPicture());
        data.setText(param.getText());
        data.setTime(System.currentTimeMillis());
        return rest.addNews(data);
    }
}
