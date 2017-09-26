package by.argyment.gymapp.profile;

import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import by.argyment.gymapp.GymApplication;
import by.argyment.gymapp.R;
import by.argyment.gymapp.base.BaseFragmentHandler;
import by.argyment.gymapp.domain.entity.Video;
import by.argyment.gymapp.domain.interactions.AddVideoUseCase;
import by.argyment.gymapp.domain.interactions.GetVideoUseCase;
import by.argyment.gymapp.extra.Strings;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

/**
 * @author Olga Rudzko
 */

public class VlogHandler implements BaseFragmentHandler {

    @Inject
    GetVideoUseCase getVideo;
    @Inject
    AddVideoUseCase addVideo;

    VlogFragment vlog;

    public ObservableBoolean isAdding = new ObservableBoolean(false);

    public VlogHandler(VlogFragment vlog) {
        this.vlog = vlog;
        GymApplication.appComponent.injectVlogFragment(this);
    }

    public VlogAdapter adapter;
    List<Video> list;

    @Override
    public void init() {
        adapter = new VlogAdapter(vlog);
    }

    @Override
    public void resume() {
        getVideo.makeRequest(null, new DisposableObserver<List<Video>>() {
            @Override
            public void onNext(@NonNull List<Video> videos) {
                list=videos;
                adapter.setItems(list);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("!!!VlogHand/getVideo", e.toString());
            }

            @Override
            public void onComplete() {
            }
        });
    }

    @Override
    public void viewCreated() {
        vlog.binding.vlogFeed.setLayoutManager(new LinearLayoutManager(vlog.getContext()));
        vlog.binding.vlogFeed.setAdapter(adapter);
    }

    public void addNewVideo(View view) {
        isAdding.set(true);
    }

    public void uploadVideo(View view) {
        String title = vlog.binding.videotitle.getText().toString();
        String link = vlog.binding.videolink.getText().toString();
        if (!(title.equals(Strings.EMPTY)) && !(link.equals(Strings.EMPTY))) {
            if (title.matches(Strings.TITLE_REGEX)) {
                if (link.matches(Strings.YOUTU_BE) || link.matches(Strings.YOUTUBE_COM)) {
                    Video video = new Video();
                    video.setUrl(link);
                    video.setTitle(title);
                    addVideo.makeRequest(video, new DisposableObserver<Video>() {
                                @Override
                                public void onNext(@NonNull Video video) {
                                    vlog.binding.videotitle.setText(Strings.EMPTY);
                                    vlog.binding.videolink.setText(Strings.EMPTY);
                                    isAdding.set(false);
                                    Toast.makeText(vlog.getContext(), R.string.video_added, Toast.LENGTH_SHORT).show();
                                    list.add(video);
                                    adapter.setItems(list);
                                }

                                @Override
                                public void onError(@NonNull Throwable e) {
                                    Log.e("!!!VlogHand/addVideo", e.toString());
                                }

                                @Override
                                public void onComplete() {

                                }
                            });

                } else {
                    Toast.makeText(view.getContext(), R.string.wrong_link, Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(view.getContext(), R.string.wrong_title, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(view.getContext(), R.string.fill_fields, Toast.LENGTH_SHORT).show();
        }
    }

    public void closeAdding(View view) {
        isAdding.set(false);
    }

    public void goToChannel(View view) {
        vlog.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(Strings.YOUTUBE_CHANNEL)));
    }

    @Override
    public void pause() {

    }

    @Override
    public void release() {
        addVideo.dispose();
        getVideo.dispose();
    }

    @Override
    public void activityResult(int requestCode, int resultCode, Intent data) {

    }
}
