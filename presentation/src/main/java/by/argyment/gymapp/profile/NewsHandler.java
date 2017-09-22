package by.argyment.gymapp.profile;

import android.app.Activity;
import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import by.argyment.gymapp.GymApplication;
import by.argyment.gymapp.R;
import by.argyment.gymapp.base.BaseFragmentHandler;
import by.argyment.gymapp.domain.entity.News;
import by.argyment.gymapp.domain.entity.Slon;
import by.argyment.gymapp.domain.interactions.AddElephantUseCase;
import by.argyment.gymapp.domain.interactions.AddNewsUseCase;
import by.argyment.gymapp.domain.interactions.GetFreeElephantsUseCase;
import by.argyment.gymapp.domain.interactions.GetNewsUseCase;
import by.argyment.gymapp.extra.Strings;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

/**
 * @author Olga Rudzko
 */

public class NewsHandler implements BaseFragmentHandler {

    @Inject
    GetNewsUseCase getNews;
    @Inject
    GetFreeElephantsUseCase elephants;
    @Inject
    AddNewsUseCase addNewsUseCase;
    @Inject
    AddElephantUseCase addSloneUseCase;

    private static final int CHOOSE_IMAGE = 111;

    public ObservableField<String> slon1 = null;
    public ObservableField<String> slon2 = null;
    public ObservableField<String> slon3 = null;

    public ObservableBoolean isAdding = new ObservableBoolean(false);
    public ObservableBoolean isSlonAdding = new ObservableBoolean(false);

    Button currentAddSlon;
    Button currentSlon;

    private NewsFragment fragment;
    private News newsToAdd;
    public NewsAdapter adapter;

    public NewsHandler(NewsFragment fragment) {
        this.fragment = fragment;
        GymApplication.appComponent.injectNewsHandler(this);
    }

    @Override
    public void init() {
        adapter = new NewsAdapter();
    }

    @Override
    public void resume() {
        getNews.makeRequest(null, new DisposableObserver<List<News>>() {
            @Override
            public void onNext(@NonNull List<News> news) {
                adapter.setItems(news);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        elephants.makeRequest(null, new DisposableObserver<List<Slon>>() {
            @Override
            public void onNext(@NonNull List<Slon> slons) {
                if (!slons.isEmpty()) {
                    slon1.set(slons.get(0).getSlon());
                    if (slons.size() > 1) {
                        slon2.set(slons.get(1).getSlon());
                        if (slons.size() > 2) {
                            slon3.set(slons.get(2).getSlon());
                        }
                    }
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void viewCreated() {

    }


    public void showEditor(View view) {
        if (isAdding.get()) {
            isAdding.set(false);
        } else {
            isAdding.set(true);
        }
    }

    public void addSlon(View view) {
        switch (view.getId()) {
            case R.id.add_slon2:
                currentSlon = this.fragment.binding.slon2;
                currentAddSlon = this.fragment.binding.addSlon2;
                break;
            case R.id.add_slon3:
                currentSlon = this.fragment.binding.slon3;
                currentAddSlon = this.fragment.binding.addSlon3;
                break;
            default:
                currentSlon = this.fragment.binding.slon1;
                currentAddSlon = this.fragment.binding.addSlon1;
        }
        view.setVisibility(View.GONE);
        isSlonAdding.set(true);
    }

    public void addSlonContent(View view) {
        Slon slon = new Slon();
        EditText edit=this.fragment.binding.slonContent;
        if (!(edit.getText().toString().equals(Strings.EMPTY))) {
            String slonText;
            if ((slonText = edit.getText().toString()).matches("[а-яА-Я0-9 !,\\.-]{10,50}")) {
                slon.setSlon(slonText);
                slon.setTrainer(MyPage.getInstance().getEmail());
                addSloneUseCase.makeRequest(slon, new DisposableObserver<Void>() {
                    @Override
                    public void onNext(@NonNull Void aVoid) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
                isSlonAdding.set(false);
            } else {
                edit.setText(Strings.EMPTY);
                edit.setHint(Strings.DESCRIPTION);
            }
        } else {
            currentAddSlon.setVisibility(View.VISIBLE);
            isSlonAdding.set(false);
        }
    }

    public void addNews(View view) {
        newsToAdd = new News();
        newsToAdd.setTitle(this.fragment.binding.newsTitle.getText().toString());
        newsToAdd.setText(this.fragment.binding.newsContent.getText().toString());
        newsToAdd.setDate(System.currentTimeMillis());
        Intent gallery = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        this.fragment.startActivityForResult(gallery, CHOOSE_IMAGE);
    }

    @Override
    public void activityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == CHOOSE_IMAGE) {
            File file = new File(data.getData().getPath());

        }
        newsToAdd.setPicture("");
        addNewsUseCase.makeRequest(newsToAdd, new DisposableObserver<Void>() {
            @Override
            public void onNext(@NonNull Void aVoid) {

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void pause() {

    }

    @Override
    public void release() {
        getNews.dispose();
        elephants.dispose();
        addNewsUseCase.dispose();
        addSloneUseCase.dispose();
    }

}
