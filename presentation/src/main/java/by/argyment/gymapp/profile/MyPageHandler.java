package by.argyment.gymapp.profile;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import by.argyment.gymapp.GymApplication;
import by.argyment.gymapp.R;
import by.argyment.gymapp.base.BaseFragmentHandler;
import by.argyment.gymapp.domain.entity.UserImage;
import by.argyment.gymapp.domain.entity.UserProfile;
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
 *
 * @see MyPageFragment
 * View model for MyPageFragment, defines the view of personal user page
 */

public class MyPageHandler implements BaseFragmentHandler {

    @Inject
    GetImageListUseCase getImages;
    @Inject
    UpdateProfileUseCase updateUser;
    @Inject
    AddImageUseCase addImg;
    @Inject
    DeleteImageUseCase removeImg;

    private static final int PICK_IMAGE = 99;
    private MyPageFragment fragment;

    boolean increaseStatus;

    MyPageImgAdapter adapter;
    private List<UserImage> list;
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
        //loads images links from database and sets them for adapter
        getImages.makeRequest(MyPage.getInstance().getEmail(), new DisposableObserver<List<UserImage>>() {
            @Override
            public void onNext(@NonNull List<UserImage> userImages) {
                list = userImages;
                adapter.setItems(list);
                mainImg = MyPage.getInstance().userpic.get();
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
     }

    @Override
    public void activityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == PICK_IMAGE) {
            File file = new File(data.getData().getPath());
            //TODO save img to server

            addImage("https://goo.gl/1PrGgG");
        }
    }

    /**
     * Removes the bonus. User can execute it when he got a service in order to be able to win new bonus
     * @param view binded element in layout
     */
    public void used(View view) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(view.getContext());
        dialog.setTitle(R.string.slondialog).setIcon(R.drawable.slon).setMessage(MyPage.getInstance().slon.get()); // сообщение
        dialog.setPositiveButton(R.string.deleteslon, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                    MyPage.getInstance().slon.set(Strings.NO);
                    fragment.binding.gotSlon.setVisibility(View.GONE);
            }
        });
        dialog.setCancelable(true);
        dialog.show();
    }

    /**
     * opens field for changing username
     * @param view binded element in layout
     */
    public void rename (View view){
        view.setVisibility(View.INVISIBLE);
        fragment.binding.changing.setVisibility(View.VISIBLE);
    }

    /**
     * sets new username to MyPage if it matches regex
     * @see MyPage
     * @param view binded element in layout
     */
    public void accept (View view){
        String input=fragment.binding.rename.getText().toString();
        if (!input.isEmpty() && input.matches(Strings.NAME_REGEX)){
            MyPage.getInstance().username.set(input);
            fragment.binding.changing.setVisibility(View.GONE);
            fragment.binding.profileName.setVisibility(View.VISIBLE);
            Toast.makeText(view.getContext(), R.string.renamed, Toast.LENGTH_SHORT).show();
        }else {
            fragment.binding.changing.setVisibility(View.GONE);
            fragment.binding.profileName.setVisibility(View.VISIBLE);
            Toast.makeText(view.getContext(), R.string.forbiddenformat, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * sets chosen photo s main
     * @param view binded element in layout
     */
    public void mainPhoto(View view) {
        mainImg = MyPage.getInstance().userpic.get();
        Toast.makeText(view.getContext(), R.string.mainingset, Toast.LENGTH_SHORT).show();
    }

    /**
     * opens android gallery
     * @param view binded element in layout
     */
    public void addPhoto(View view) {
        Intent gallery = new Intent(Intent.ACTION_PICK, INTERNAL_CONTENT_URI);
        this.fragment.startActivityForResult(gallery, PICK_IMAGE);
    }

    /**
     * adds new link of the image assosiated with user email to database
     * @see AddImageUseCase
     * @param link link that should bew assotiated with user image in databes
     */
    private void addImage(String link) {
        UserImage image = new UserImage();
        image.setEmail(MyPage.getInstance().getEmail());
        image.setLink(link);
        addImg.makeRequest(image, new DisposableObserver<UserImage>() {
            @Override
            public void onNext(@NonNull UserImage userImage) {
                Collections.reverse(list);
                list.add(userImage);
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
    }

    /**
     * Removes the chosen link assotiated with user email from database
     * @param view binded element in layout
     */
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
            removeImg.makeRequest(id, new DisposableObserver<UserImage>() {
                @Override
                public void onNext(@NonNull UserImage image) {
                    adapter.setItems(list);
                    MyPage.getInstance().userpic.set(mainImg);
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
    }

    /**
     * updates user information in database on pause
     */
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
        if (mainImg!=null) {
            updated.setUserpic(mainImg);
        } else {
            updated.setUserpic(MyPage.getInstance().userpic.get());
        }
        updated.setSlon(MyPage.getInstance().slon.get());
        updated.setStatus(MyPage.getInstance().status.get());
        updated.setObjectId(MyPage.getInstance().getObjectId());
        updated.setTimeCheckin(MyPage.getInstance().getTimeCheckin());
        updateUser.makeRequest(updated, new DisposableObserver<UserProfile>() {
            @Override
            public void onNext(@NonNull UserProfile userProfile) {

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
    public void release() {
        getImages.dispose();
        addImg.dispose();
        updateUser.dispose();
        removeImg.dispose();
    }
}
