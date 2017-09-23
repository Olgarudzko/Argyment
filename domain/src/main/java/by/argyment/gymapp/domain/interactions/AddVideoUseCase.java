package by.argyment.gymapp.domain.interactions;

import javax.inject.Inject;

import by.argyment.gymapp.data.entity.VideoData;
import by.argyment.gymapp.data.net.RestService;
import by.argyment.gymapp.domain.entity.Video;
import by.argyment.gymapp.domain.interactions.base.UseCase;
import io.reactivex.Observable;

/**
 * @author Olga Rudzko
 */

public class AddVideoUseCase extends UseCase<Video, Void>{

    RestService rest;

    @Inject
    public AddVideoUseCase(RestService rest) {
        this.rest = rest;
    }

    @Override
    protected Observable<Void> buildUseCase(Video param) {
        VideoData video=new VideoData();
        video.setTitle(param.getTitle());
        video.setUrl(param.getUrl());
        video.setAddedAt(System.currentTimeMillis());
        return rest.addVideo(video);
    }
}
