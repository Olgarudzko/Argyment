package by.argyment.gymapp.profile;

import android.app.Activity;
import android.content.Intent;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import by.argyment.gymapp.GymApplication;
import by.argyment.gymapp.R;
import by.argyment.gymapp.base.BaseFragmentHandler;
import by.argyment.gymapp.domain.entity.UserBitmap;
import by.argyment.gymapp.domain.entity.UserImage;
import by.argyment.gymapp.domain.entity.UserProfile;
import by.argyment.gymapp.domain.interactions.AddBitmapUseCase;
import by.argyment.gymapp.domain.interactions.AddImageUseCase;
import by.argyment.gymapp.domain.interactions.DeleteImageUseCase;
import by.argyment.gymapp.domain.interactions.GetImageListUseCase;
import by.argyment.gymapp.domain.interactions.UpdateProfileUseCase;
import by.argyment.gymapp.extra.Strings;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

import static android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI;

/**
 * @author Olga Rudzko
 */

public class MyPageHandler implements BaseFragmentHandler {

    @Inject
    GetImageListUseCase getImages;
    @Inject
    UpdateProfileUseCase updateUser;
    @Inject
    AddImageUseCase addImg;
    @Inject
    AddBitmapUseCase addBitmap;
    @Inject
    DeleteImageUseCase removeImg;

    private static final int PICK_IMAGE = 99;
    private MyPageFragment fragment;

    boolean increaseStatus;

    MyPageImgAdapter adapter;
    private List<UserImage> list = new ArrayList<>();
    private String mainImg;


    public MyPageHandler(MyPageFragment fragment) {
        this.fragment = fragment;
        GymApplication.appComponent.injectMyPageHandler(this);
    }

    @Override
    public void init() {
        adapter = new MyPageImgAdapter();
    }

    @Override
    public void resume() {
        if (!MyPage.getInstance().slon.get().equals(Strings.NO)) {
            fragment.binding.gotSlon.setVisibility(View.VISIBLE);
        }
        getImages.makeRequest(MyPage.getInstance().getEmail(), new DisposableObserver<List<UserImage>>() {
            @Override
            public void onNext(@NonNull List<UserImage> userImages) {
                list = userImages;
                adapter.setItems(list);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("!!!MyPageHand/getImages", e.toString());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void viewCreated() {
        mainImg = MyPage.getInstance().userpic.get();
    }

    @Override
    public void activityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == PICK_IMAGE) {
            File file = new File(data.getData().getPath());
            UserBitmap bitmap = new UserBitmap();
            bitmap.setFile(file);
            bitmap.setName(String.valueOf(System.currentTimeMillis()).concat(MyPage.getInstance().username.get()));
            addBitmap.makeRequest(bitmap, new DisposableObserver<Void>() {
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
            addImage(bitmap.getName());
        }
    }

    public void used(View view) {

    }

    public void mainPhoto(View view) {
        mainImg = MyPage.getInstance().userpic.get();
        Toast.makeText(view.getContext(), R.string.mainingset, Toast.LENGTH_SHORT).show();
    }

    public void addPhoto(View view) {
        Intent gallery = new Intent(Intent.ACTION_PICK, INTERNAL_CONTENT_URI);
        this.fragment.startActivityForResult(gallery, PICK_IMAGE);
    }

    private void addImage(String name) {
        UserImage image = new UserImage();
        image.setEmail(MyPage.getInstance().getEmail());
        image.setLink("");
        addImg.makeRequest(image, new DisposableObserver<Void>() {
            @Override
            public void onNext(@NonNull Void aVoid) {
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("!!!MyPageHand/addImg", e.toString());
            }

            @Override
            public void onComplete() {
            }
        });
    }

    public void removePhoto(View view) {
        String link = MyPage.getInstance().userpic.get();
        String id = Strings.EMPTY;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getLink().equals(link)) {
                id = list.get(i).getObjectId();
                list.remove(i);
            }
        }
        if (!(id.equals(Strings.EMPTY))) {
            removeImg.makeRequest(id, new DisposableObserver<Void>() {
                @Override
                public void onNext(@NonNull Void aVoid) {
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    Log.e("!!!MyPageHand/removeImg", e.toString());
                }

                @Override
                public void onComplete() {
                }
            });
            adapter.setItems(list);
            MyPage.getInstance().userpic.set(mainImg);
        }
    }

    @Override
    public void pause() {
        if (increaseStatus) {
            MyPage.getInstance().status.set(MyPage.getInstance().status.get() + 1);
            MyPage.getInstance().setTimeCheckin(System.currentTimeMillis());
            increaseStatus = false;
        }
        UserProfile updated = new UserProfile();
        updated.setEmail(MyPage.getInstance().getEmail());
        updated.setUsername(MyPage.getInstance().username.get());
        updated.setPassword(MyPage.getInstance().getPassword());
        updated.setTrainer(MyPage.getInstance().isTrainer.get());
        updated.setAdmin(MyPage.getInstance().isAdmin.get());
        updated.setUserpic(mainImg);
        updated.setSlon(MyPage.getInstance().slon.get());
        updated.setStatus(MyPage.getInstance().status.get());
        updated.setObjectId(MyPage.getInstance().getObjectId());
        updated.setTimeCheckin(MyPage.getInstance().getTimeCheckin());
        updateUser.makeRequest(updated, new DisposableObserver<Void>() {
            @Override
            public void onNext(@NonNull Void aVoid) {
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("!!!MyPageHand/updUser", e.toString());
            }

            @Override
            public void onComplete() {
            }
        });
    }

    @Override
    public void release() {
        getImages.dispose();
        addImg.dispose();
        addBitmap.dispose();
        updateUser.dispose();
        removeImg.dispose();
    }
}
