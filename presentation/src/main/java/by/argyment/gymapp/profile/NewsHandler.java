package by.argyment.gymapp.profile;

import android.app.Activity;
import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.files.BackendlessFile;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
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
import by.argyment.gymapp.domain.interactions.SetSlonWinnerUseCase;
import by.argyment.gymapp.extra.Elephant;
import by.argyment.gymapp.extra.Strings;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

/**
 * @author Olga Rudzko
 *
 * the view model of News Fragment
 * @see NewsFragment
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
    @Inject
    SetSlonWinnerUseCase setWinner;

    private static final int CHOOSE_IMAGE = 111;

    public ObservableField<String> slon1 = new ObservableField<>(Strings.EMPTY);
    public ObservableField<String> slon2 = new ObservableField<>(Strings.EMPTY);
    public ObservableField<String> slon3 = new ObservableField<>(Strings.EMPTY);
    private List<Elephant> slony;

    public ObservableBoolean isAdding = new ObservableBoolean(false);
    public ObservableBoolean isSlonAdding = new ObservableBoolean(false);

    Button currentAddSlon;

    private NewsFragment fragment;
    private News newsToAdd;
    public NewsAdapter adapter;
    private List<News> list;

    public NewsHandler(NewsFragment fragment) {
        this.fragment = fragment;
        GymApplication.appComponent.injectNewsHandler(this);
    }

    @Override
    public void init() {
        adapter = new NewsAdapter();
    }

    /**
     * on resume gets all news and all available bonuses from database
     * @see GetNewsUseCase
     * @see GetFreeElephantsUseCase
     */
    @Override
    public void resume() {
        getNews.makeRequest(null, new DisposableObserver<List<News>>() {
            @Override
            public void onNext(@NonNull List<News> news) {
                list = news;
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
        elephants.makeRequest(null, new DisposableObserver<List<Slon>>() {
            @Override
            public void onNext(@NonNull List<Slon> slons) {
                slony = new ArrayList<>();
                for (Slon slon : slons) {
                    Elephant el = new Elephant();
                    el.setObjectId(slon.getObjectId());
                    el.setSlon(slon.getSlon());
                    el.setTrainer(slon.getTrainer());
                    slony.add(el);
                }
                //sets visibility of available bonuses
                if (!slony.isEmpty()) {
                    slon1.set(slony.get(0).getSlon());
                    if (!MyPage.getInstance().isTrainer.get()) {
                        fragment.binding.slon1.setVisibility(View.VISIBLE);
                    }
                    if (slony.size() > 1) {
                        slon2.set(slony.get(1).getSlon());
                        if (!MyPage.getInstance().isTrainer.get()) {
                            fragment.binding.slon2.setVisibility(View.VISIBLE);
                        }
                        if (slony.size() > 2) {
                            slon3.set(slony.get(2).getSlon());
                            if (!MyPage.getInstance().isTrainer.get()) {
                                fragment.binding.slon3.setVisibility(View.VISIBLE);
                            }
                        } else {
                            if (MyPage.getInstance().isTrainer.get()) {
                                fragment.binding.addSlon1.setVisibility(View.VISIBLE);
                            }
                        }
                    } else {
                        if (MyPage.getInstance().isTrainer.get()) {
                            fragment.binding.addSlon1.setVisibility(View.VISIBLE);
                            fragment.binding.addSlon2.setVisibility(View.VISIBLE);
                        }
                    }
                } else {
                    if (MyPage.getInstance().isTrainer.get()) {
                        fragment.binding.addSlon1.setVisibility(View.VISIBLE);
                        fragment.binding.addSlon2.setVisibility(View.VISIBLE);
                        fragment.binding.addSlon3.setVisibility(View.VISIBLE);
                    }
                }
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
        fragment.binding.newsFeed.setLayoutManager(new LinearLayoutManager(fragment.getContext()));
        fragment.binding.newsFeed.setAdapter(adapter);
    }

    public void showEditor(View view) {
        isAdding.set(true);
    }

    public void hideEditor(View view) {
        isAdding.set(false);
    }

    /**
     * assosiate chosen bonus with user email, adds bonus to user page, redirects to MyPage Fragment
     * @see MyPageFragment
     * @see MyPage
     * @see SetSlonWinnerUseCase
     * @param view binded element in layout
     */
    public void wonSlon(View view) {
        String str = MyPage.getInstance().slon.get();
        if (str != null && str.equals(Strings.NO)) {
            Slon slon = new Slon();
            slon.setWinner(MyPage.getInstance().getEmail());
            switch (view.getId()) {
                case R.id.slon1:
                    slon.setTrainer(slony.get(0).getTrainer());
                    slon.setObjectId(slony.get(0).getObjectId());
                    slon.setSlon(slony.get(0).getSlon());
                    setWinner.makeRequest(slon, new DisposableObserver<Slon>() {
                        @Override
                        public void onNext(@NonNull Slon slon) {
                            MyPage.getInstance().slon.set(slon1.get());
                            fragment.binding.slon1.setVisibility(View.GONE);
                            slon1.set(Strings.EMPTY);
                            ProfileModel.showFragment(fragment.getFragmentManager(),
                                    MyPageFragment.newInstance(fragment.getFragmentManager(), false), true);
                            Toast.makeText(fragment.getContext(), slon1.get(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            Toast.makeText(fragment.getContext(), R.string.unavailable, Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onComplete() {

                        }
                    });

                    break;
                case R.id.slon2:
                    slon.setTrainer(slony.get(1).getTrainer());
                    slon.setObjectId(slony.get(1).getObjectId());
                    slon.setSlon(slony.get(1).getSlon());
                    setWinner.makeRequest(slon, new DisposableObserver<Slon>() {
                        @Override
                        public void onNext(@NonNull Slon slon) {
                            MyPage.getInstance().slon.set(slon2.get());
                            Toast.makeText(fragment.getContext(), slon2.get(), Toast.LENGTH_SHORT).show();
                            fragment.binding.slon2.setVisibility(View.GONE);
                            slon2.set(Strings.EMPTY);
                            ProfileModel.showFragment(fragment.getFragmentManager(),
                                    MyPageFragment.newInstance(fragment.getFragmentManager(), false), true);
                            Toast.makeText(fragment.getContext(), slon2.get(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            Toast.makeText(fragment.getContext(), R.string.unavailable, Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
                    break;
                default:
                    slon.setTrainer(slony.get(2).getTrainer());
                    slon.setObjectId(slony.get(2).getObjectId());
                    slon.setSlon(slony.get(2).getSlon());
                    setWinner.makeRequest(slon, new DisposableObserver<Slon>() {
                        @Override
                        public void onNext(@NonNull Slon slon) {
                            MyPage.getInstance().slon.set(slon3.get());
                            Toast.makeText(fragment.getContext(), slon3.get(), Toast.LENGTH_SHORT).show();
                            fragment.binding.slon3.setVisibility(View.GONE);
                            slon3.set(Strings.EMPTY);
                            ProfileModel.showFragment(fragment.getFragmentManager(),
                                    MyPageFragment.newInstance(fragment.getFragmentManager(), false), true);
                            Toast.makeText(fragment.getContext(), slon3.get(), Toast.LENGTH_SHORT).show();
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
        } else {
            Toast.makeText(view.getContext(), R.string.secondslon, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Action is available for trainers only. Opens the menu for adding bonus to the bottom panel
     * where the first client who presses at "slon" icon can win it
     *
     * @param view binded element in layout
     */
    public void addSlon(View view) {
        switch (view.getId()) {
            case R.id.add_slon2:
                currentAddSlon = fragment.binding.addSlon2;
                break;
            case R.id.add_slon3:
                currentAddSlon = fragment.binding.addSlon3;
                break;
            default:
                currentAddSlon = fragment.binding.addSlon1;
        }
        isSlonAdding.set(true);
    }

    /**
     * adds new bonus to database
     * @see AddElephantUseCase
     *
     * @param view binded element in layout
     */
    public void addSlonContent(View view) {
        Slon slon = new Slon();
        final EditText edit = this.fragment.binding.slonContent;
        if (!(edit.getText().toString().equals(Strings.EMPTY))) {
            String slonText;
            if ((slonText = edit.getText().toString()).matches(Strings.SLON_REGEX)) {
                slon.setSlon(slonText);
                slon.setTrainer(MyPage.getInstance().getEmail());
                addSloneUseCase.makeRequest(slon, new DisposableObserver<Slon>() {
                    @Override
                    public void onNext(@NonNull Slon slon) {
                        edit.setText(Strings.EMPTY);
                        currentAddSlon.setVisibility(View.GONE);
                        Toast.makeText(fragment.getContext(), R.string.slonadded, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Toast.makeText(fragment.getContext(), R.string.unavailable, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
                isSlonAdding.set(false);
            } else {
                edit.setHint(R.string.description);
            }
        } else {
            isSlonAdding.set(false);
        }
    }

    /**
     * Action is available for administrators only. Opens the panel for adding news.
     *
     * @param view binded element in layout
     */
    public void addNews(View view) {
        String title = fragment.binding.newsTitle.getText().toString();
        String text = fragment.binding.newsContent.getText().toString();
        if (!(title.equals(Strings.EMPTY)) && !(text.equals(Strings.EMPTY))) {
            if (title.matches(Strings.TITLE_REGEX) && text.matches(Strings.NEWS_REGEX)) {
                newsToAdd = new News();
                newsToAdd.setTitle(fragment.binding.newsTitle.getText().toString());
                newsToAdd.setText(fragment.binding.newsContent.getText().toString());
                Intent gallery = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                this.fragment.startActivityForResult(gallery, CHOOSE_IMAGE);
            } else {
                Toast.makeText(view.getContext(), R.string.wrong_format, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(view.getContext(), R.string.fill_fields, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Loads picture to server and adds news
     * @see AddNewsUseCase
     * @param requestCode special code of the request defined previously
     * @param resultCode indicates whether the user made his choice in gallery
     * @param data chosen data
     */
    @Override
    public void activityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == CHOOSE_IMAGE) {
            Bitmap bitmap;
            Uri selectedImage = data.getData();
            InputStream imageStream;
            try {
                imageStream = fragment.getContext().getContentResolver().openInputStream(selectedImage);
                bitmap = BitmapFactory.decodeStream(imageStream);
                bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() / 5, bitmap.getHeight() / 5, false);
                final String name = System.currentTimeMillis() + Strings.JPG;
                Backendless.Files.Android.upload(bitmap, Bitmap.CompressFormat.JPEG, 50,
                        name, Strings.NEWSIMAGES, new AsyncCallback<BackendlessFile>() {
                            @Override
                            public void handleResponse(BackendlessFile response) {
                                newsToAdd.setPicture(name);
                                addNewsUseCase.makeRequest(newsToAdd, new DisposableObserver<News>() {
                                    @Override
                                    public void onNext(@NonNull News news) {
                                        Collections.reverse(list);
                                        list.add(news);
                                        Collections.reverse(list);
                                        adapter.setItems(list);
                                        Toast.makeText(fragment.getContext(), R.string.newsadded, Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onError(@NonNull Throwable e) {
                                        Toast.makeText(fragment.getContext(), R.string.unavailable, Toast.LENGTH_LONG).show();
                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                });
                                fragment.binding.newsContent.setText(Strings.EMPTY);
                                fragment.binding.newsTitle.setText(Strings.EMPTY);
                                isAdding.set(false);
                            }

                            @Override
                            public void handleFault(BackendlessFault fault) {
                                Toast.makeText(fragment.getContext(), R.string.uploaderror, Toast.LENGTH_SHORT).show();
                            }
                        });
            } catch (Exception e) {
                Toast.makeText(fragment.getContext(), R.string.wrongfiletype, Toast.LENGTH_SHORT).show();
            }
        }
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
        setWinner.dispose();
    }

}
