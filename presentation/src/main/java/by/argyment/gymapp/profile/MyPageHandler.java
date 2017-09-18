package by.argyment.gymapp.profile;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import by.argyment.gymapp.GymApplication;
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
    boolean starGiven;

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
        fillAdapter();
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

    public void mainPhoto(View view) {
        mainImg = MyPage.getInstance().userpic.get();
    }

    public void addPhoto(View view) {
        Intent gallery = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        this.fragment.startActivityForResult(gallery, PICK_IMAGE);
    }

    private void addImage(String name) {
        UserImage image = new UserImage();
        image.setEmail(MyPage.getInstance().getEmail());
        image.setLink("https://api.backendless.com/FCBFF78E-1D57-5C7C-FF9A-7A8C1078C400/" +
                "175DDE14-033B-C914-FFF8-D66210C89700/files/UsersImages/" + name);
        addImg.makeRequest(image, new DisposableObserver<Void>() {
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

                }

                @Override
                public void onComplete() {

                }
            });
            adapter.setItems(list);
            MyPage.getInstance().userpic.set(mainImg);
        }
    }

    private void fillAdapter() {
        getImages.makeRequest(MyPage.getInstance().getEmail(), new DisposableObserver<List<UserImage>>() {
            @Override
            public void onNext(@NonNull List<UserImage> userImages) {
                list = userImages;
                adapter.setItems(list);
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
        if (increaseStatus) {
            MyPage.getInstance().status.set(MyPage.getInstance().status.get() + 1);
            MyPage.getInstance().setTimeCheckin(System.currentTimeMillis());
            increaseStatus = false;
        }
        if (starGiven){
            MyPage.getInstance().setTimeStar(System.currentTimeMillis());
        }
        UserProfile updated = new UserProfile();
        updated.setEmail(MyPage.getInstance().getEmail());
        updated.setUsername(MyPage.getInstance().username.get());
        updated.setPassword(MyPage.getInstance().getPassword());
        updated.setTrainer(MyPage.getInstance().isTrainer.get());
        updated.setAdmin(MyPage.getInstance().isAdmin.get());
        updated.setUserpic(mainImg);
        updated.setSlon(MyPage.getInstance().slon.get());
        updated.setStars(MyPage.getInstance().stars.get());
        updated.setStatus(MyPage.getInstance().status.get());
        updated.setObjectId(MyPage.getInstance().getObjectId());
        updated.setTimeCheckin(MyPage.getInstance().getTimeCheckin());
        updated.setTimeStar(MyPage.getInstance().getTimeStar());
        updateUser.makeRequest(updated, new DisposableObserver<Void>() {
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
    public void release() {
        getImages.dispose();
        addImg.dispose();
        addBitmap.dispose();
        updateUser.dispose();
        removeImg.dispose();
    }
}
