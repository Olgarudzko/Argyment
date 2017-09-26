package by.argyment.gymapp.domain.interactions;

import javax.inject.Inject;

import by.argyment.gymapp.data.entity.VideoData;
import by.argyment.gymapp.data.net.RestService;
import by.argyment.gymapp.domain.entity.Video;
import by.argyment.gymapp.domain.interactions.base.UseCase;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * @author Olga Rudzko
 */

public class AddVideoUseCase extends UseCase<Video, Video>{

    RestService rest;

    @Inject
    public AddVideoUseCase(RestService rest) {
        this.rest = rest;
    }

    @Override
    protected Observable<Video> buildUseCase(Video param) {
        VideoData video=new VideoData();
        video.setTitle(param.getTitle());
        video.setUrl(param.getUrl());
        video.setAddedAt(System.currentTimeMillis());
        return rest.addVideo(video).map(new Function<VideoData, Video>() {
            @Override
            public Video apply(@NonNull VideoData videoData) throws Exception {
                Video video = new Video();
                video.setObjectId(videoData.getObjectId());
                video.setTitle(videoData.getTitle());
                video.setUrl(videoData.getUrl());
                video.setAddedAt(videoData.getAddedAt());
                return video;
            }
        });
    }
}
