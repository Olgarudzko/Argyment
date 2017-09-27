package by.argyment.gymapp.profile;

import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import java.util.Collections;
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
 * View model for VlogFragment
 * @see VlogFragment
 */

public class VlogHandler implements BaseFragmentHandler {

    @Inject
    GetVideoUseCase getVideo;
    @Inject
    AddVideoUseCase addVideo;

    VlogFragment fragment;

    public ObservableBoolean isAdding = new ObservableBoolean(false);

    public VlogHandler(VlogFragment fragment) {
        this.fragment = fragment;
        GymApplication.appComponent.injectVlogFragment(this);
    }

    public VlogAdapter adapter;
    private  List<Video> list;

    @Override
    public void init() {
        adapter = new VlogAdapter(fragment);
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
                Toast.makeText(fragment.getContext(), R.string.unavailable, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onComplete() {
            }
        });
    }

    @Override
    public void viewCreated() {
        fragment.binding.vlogFeed.setLayoutManager(new LinearLayoutManager(fragment.getContext()));
        fragment.binding.vlogFeed.setAdapter(adapter);
    }

    public void addNewVideo(View view) {
        isAdding.set(true);
    }

    public void uploadVideo(View view) {
        String title = fragment.binding.videotitle.getText().toString();
        String link = fragment.binding.videolink.getText().toString();
        if (!(title.equals(Strings.EMPTY)) && !(link.equals(Strings.EMPTY))) {
            if (title.matches(Strings.TITLE_REGEX)) {
                if (link.matches(Strings.YOUTU_BE) || link.matches(Strings.YOUTUBE_COM)) {
                    Video video = new Video();
                    video.setUrl(link);
                    video.setTitle(title);
                    addVideo.makeRequest(video, new DisposableObserver<Video>() {
                                @Override
                                public void onNext(@NonNull Video video) {
                                    fragment.binding.videotitle.setText(Strings.EMPTY);
                                    fragment.binding.videolink.setText(Strings.EMPTY);
                                    isAdding.set(false);
                                    Toast.makeText(fragment.getContext(), R.string.video_added, Toast.LENGTH_SHORT).show();
                                    Collections.reverse(list);
                                    list.add(video);
                                    Collections.reverse(list);
                                    adapter.setItems(list);
                                }

                                @Override
                                public void onError(@NonNull Throwable e) {
                                    Toast.makeText(fragment.getContext(), R.string.unavailable, Toast.LENGTH_LONG).show();
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
        fragment.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(Strings.YOUTUBE_CHANNEL)));
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
