package by.argyment.gymapp.domain.interactions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

public class GetVideoUseCase extends UseCase<Void, List<Video>>{

    RestService rest;

    @Inject
    public GetVideoUseCase(RestService rest) {
        this.rest = rest;
    }

    @Override
    protected Observable<List<Video>> buildUseCase(Void param) {
        return rest.getVideo().map(new Function<List<VideoData>, List<Video>>() {
            @Override
            public List<Video> apply(@NonNull List<VideoData> videoDatas) throws Exception {
                List <Video> vlog=new ArrayList<>();
                for (VideoData data: videoDatas) {
                    Video video=new Video();
                    video.setUrl(data.getUrl());
                    video.setTitle(data.getTitle());
                    video.setObjectId(data.getObjectId());
                    video.setAddedAt(data.getAddedAt());
                    vlog.add(video);
                }
                Collections.sort(vlog, new Comparator<Video>() {
                    @Override
                    public int compare(Video video, Video t1) {
                        return (video.getAddedAt()>t1.getAddedAt()) ? -1 :
                                (video.getAddedAt()<t1.getAddedAt()) ? 1 : 0;
                    }
                });
                return vlog;
            }
        });
    }
}
