package by.argyment.gymapp.profile;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import by.argyment.gymapp.GymApplication;
import by.argyment.gymapp.base.BaseFragmentHandler;
import by.argyment.gymapp.domain.entity.UserImage;
import by.argyment.gymapp.domain.entity.UserProfile;
import by.argyment.gymapp.domain.interactions.GetImageListUseCase;
import by.argyment.gymapp.domain.interactions.GetProfileListUseCase;
import by.argyment.gymapp.extra.Strings;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

/**
 * @author Olga Rudzko
 */

public class SearchHandler implements BaseFragmentHandler {

    @Inject
    GetProfileListUseCase getProfiles;
    @Inject
    GetImageListUseCase getImages;

    List<UserProfile> list = new ArrayList<>();
    SearchFragment searchFragment;
    public SearchAdapter adapter;

    SearchHandler(SearchFragment fragment) {
        this.searchFragment=fragment;
        GymApplication.appComponent.injectSearchHandler(this);
    }

    public MyPageImgAdapter picsAdapter;

    @Override
    public void init() {
        adapter = new SearchAdapter(searchFragment);
    }

    @Override
    public void resume() {
        getProfiles.makeRequest(null, new DisposableObserver<List<UserProfile>>() {
            @Override
            public void onNext(@NonNull List<UserProfile> userProfiles) {
                for (int i = 0; i < userProfiles.size(); i++) {
                    if (userProfiles.get(i).getEmail().equals(MyPage.getInstance().getEmail())) {
                        userProfiles.remove(i);
                    }
                }
                list = userProfiles;
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
    public void viewCreated() {
        searchFragment.binding.usersList.setLayoutManager(new GridLayoutManager(searchFragment.getContext(), 3));
        searchFragment.binding.usersList.setAdapter(adapter);
    }

    public void loadMemberPage(UserProfile member) {
        picsAdapter = new MyPageImgAdapter();
        MemberPage.getInstance().setObjectId(member.getObjectId());
        MemberPage.getInstance().username.set(member.getUsername());
        MemberPage.getInstance().isTrainer.set(member.isTrainer());
        MemberPage.getInstance().userpic.set(member.getUserpic());
        MemberPage.getInstance().status.set(member.getStatus());
        MemberPage.getInstance().stars.set(member.getStars());
        getImages.makeRequest(member.getEmail(), new DisposableObserver<List<UserImage>>() {
            @Override
            public void onNext(@NonNull List<UserImage> userImages) {
                picsAdapter.setItems(userImages);
                MemberPage.getInstance().visibility.set(true);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        searchFragment.binding.memberGallery.setLayoutManager(new GridLayoutManager(searchFragment.getContext(), 3));
        searchFragment.binding.memberGallery.setAdapter(picsAdapter);
    }
    public void findName(View view) {
        String name = searchFragment.binding.findMember.getText().toString();
        if (!name.equals(Strings.EMPTY)) {
            List<UserProfile> nameSakes = new ArrayList<>();
            for (UserProfile user : list) {
                if (user.getUsername().equalsIgnoreCase(name)
                        || user.getUsername().toLowerCase().contains(name.toLowerCase())) {
                    nameSakes.add(user);
                }
            }
            adapter.setItems(nameSakes);
        } else {
            adapter.setItems(list);
        }
    }

    public void plusStar(View view) {}
    @Override
    public void pause() {
        MemberPage.getInstance().visibility.set(false);
    }

    @Override
    public void release() {
        getProfiles.dispose();
        getImages.dispose();
    }

    @Override
    public void activityResult(int requestCode, int resultCode, Intent data) {

    }
}
