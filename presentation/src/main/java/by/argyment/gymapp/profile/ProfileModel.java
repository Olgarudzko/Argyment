package by.argyment.gymapp.profile;

import java.util.ArrayList;
import java.util.List;

import by.argyment.gymapp.base.BaseViewModel;
import by.argyment.gymapp.domain.entity.UserImage;
import by.argyment.gymapp.domain.entity.UserProfile;
import by.argyment.gymapp.domain.interactions.GetImageListUseCase;
import by.argyment.gymapp.domain.interactions.GetProfileUseCase;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

/**
 * @author Olga Rudzko
 */

public class ProfileModel implements BaseViewModel {

    private GetProfileUseCase getProfile;
    private GetImageListUseCase getImages;

    @Override
    public void init() {

    }

    @Override
    public void resume() {
        getProfile = new GetProfileUseCase();
        getImages = new GetImageListUseCase();
        getProfile.makeRequest(MyPage.getInstance().getEmail(), new DisposableObserver<UserProfile>() {
            @Override
            public void onNext(@NonNull UserProfile userProfile) {
                MyPage.getInstance().setPassword(userProfile.getPassword());
                MyPage.getInstance().username.set(userProfile.getUsername());
                MyPage.getInstance().userpic.set(userProfile.getUserpic());
                MyPage.getInstance().isAdmin.set(userProfile.isAdmin());
                MyPage.getInstance().isTrainer.set(userProfile.isTrainer());
                MyPage.getInstance().status.set(userProfile.getStatus());
                MyPage.getInstance().stars.set(userProfile.getStars());
                MyPage.getInstance().slon.set(userProfile.getSlon());
                MyPage.getInstance().setObjectId(userProfile.getObjectId());
                MyPage.getInstance().setTimeCheckin(userProfile.getTimeCheckin());
                MyPage.getInstance().setTimeStar(userProfile.getTimeStar());
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        getImages.makeRequest(MyPage.getInstance().getEmail(), new DisposableObserver<List<UserImage>>() {
            @Override
            public void onNext(@NonNull List<UserImage> userImages) {
                MyPage.getInstance().myPics=new ArrayList<UserImage>();
                for (UserImage img: userImages) {
                    UserImage newArrayImg=new UserImage();
                    newArrayImg.setEmail(img.getEmail());
                    newArrayImg.setLink(img.getLink());
                    newArrayImg.setObjectId(img.getObjectId());
                    MyPage.getInstance().myPics.add(newArrayImg);
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
    public void release() {
        getProfile.dispose();
        getImages.dispose();
    }

    @Override
    public void pause() {

    }
}
