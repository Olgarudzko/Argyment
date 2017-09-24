package by.argyment.gymapp.domain.interactions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

public class GetNewsUseCase extends UseCase<Void, List<News>> {

    RestService rest;

    @Inject
    public GetNewsUseCase(RestService rest) {
        this.rest = rest;
    }

    @Override
    protected Observable<List<News>> buildUseCase(Void param) {
        return rest.getNews().map(new Function<List<NewsData>, List<News>>() {
            @Override
            public List<News> apply(@NonNull List<NewsData> newsDatas) throws Exception {
                List<News> list= new ArrayList<>();
                for (NewsData data: newsDatas) {
                    News news=new News();
                    news.setObjectId(data.getObjectId());
                    news.setPicture(data.getPicture());
                    news.setTitle(data.getTitle());
                    news.setText(data.getText());
                    news.setTime(data.getTime());
                    list.add(news);
                }
                Collections.sort(list, new Comparator<News>() {
                    @Override
                    public int compare(News news, News t1) {
                        return (news.getTime()>t1.getTime()) ? -1 : (news.getTime()<t1.getTime()) ? 1 : 0;
                    }
                });
                return list;
            }
        });
    }
}
