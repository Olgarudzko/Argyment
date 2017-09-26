package by.argyment.gymapp.domain.interactions;

import javax.inject.Inject;

import by.argyment.gymapp.data.entity.NewsData;
import by.argyment.gymapp.data.net.RestService;
import by.argyment.gymapp.domain.entity.News;
import by.argyment.gymapp.domain.interactions.base.UseCase;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * @author Olga Rudzko
 */

public class AddNewsUseCase extends UseCase<News, News> {

    RestService rest;

    @Inject
    public AddNewsUseCase(RestService rest) {
        this.rest = rest;
    }

    @Override
    protected Observable<News> buildUseCase(News param) {
        NewsData data=new NewsData();
        data.setTitle(param.getTitle());
        data.setPicture(param.getPicture());
        data.setText(param.getText());
        data.setTime(System.currentTimeMillis());
        return rest.addNews(data).map(new Function<NewsData, News>() {
            @Override
            public News apply(@NonNull NewsData newsData) throws Exception {
                News news = new News();
                news.setPicture(newsData.getPicture());
                news.setText(newsData.getText());
                news.setObjectId(newsData.getObjectId());
                news.setTime(newsData.getTime());
                news.setTitle(newsData.getTitle());
                return news;
            }
        });
    }
}
