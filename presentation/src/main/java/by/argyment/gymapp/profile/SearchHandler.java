package by.argyment.gymapp.profile;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import by.argyment.gymapp.GymApplication;
import by.argyment.gymapp.R;
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
 * View model for SearchFragment
 * @see SearchFragment
 */

public class SearchHandler implements BaseFragmentHandler {

    @Inject
    GetProfileListUseCase getProfiles;
    @Inject
    GetImageListUseCase getImages;

    private List<UserProfile> list;
    SearchFragment fragment;
    public SearchAdapter adapter;

    SearchHandler(SearchFragment fragment) {
        this.fragment = fragment;
        GymApplication.appComponent.injectSearchHandler(this);
    }

    public MyPageImgAdapter picsAdapter;

    @Override
    public void init() {
        adapter = new SearchAdapter(fragment);
    }

    /**
     * get all profiles from database sorted by status (depends on amount of check-ins)
     * @see GetProfileListUseCase
     */
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
                Toast.makeText(fragment.getContext(), R.string.unavailable, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onComplete() {
            }
        });
    }

    @Override
    public void viewCreated() {
        fragment.binding.usersList.setLayoutManager(new GridLayoutManager(fragment.getContext(), 3));
        fragment.binding.usersList.setAdapter(adapter);
    }

    /**
     * Shows chosen user information and loads images assotiated with his email.
     * @see GetImageListUseCase
     * @param member binded element in layout
     */
    public void loadMemberPage(UserProfile member) {
        picsAdapter = new MyPageImgAdapter();
        MemberPage.getInstance().setObjectId(member.getObjectId());
        MemberPage.getInstance().username.set(member.getUsername());
        MemberPage.getInstance().isTrainer.set(member.isTrainer());
        MemberPage.getInstance().userpic.set(member.getUserpic());
        MemberPage.getInstance().status.set(member.getStatus());
        getImages.makeRequest(member.getEmail(), new DisposableObserver<List<UserImage>>() {
            @Override
            public void onNext(@NonNull List<UserImage> userImages) {
                picsAdapter.setItems(userImages);
                MemberPage.getInstance().visibility.set(true);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Toast.makeText(fragment.getContext(), R.string.unavailable, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onComplete() {
            }
        });
        fragment.binding.memberGallery.setLayoutManager(new GridLayoutManager(fragment.getContext(), 3));
        fragment.binding.memberGallery.setAdapter(picsAdapter);
    }

    /**
     * searches for given row of symbols through the list formed on resume
     * @param view binded element in layout
     */
    public void findName(View view) {
        String name = fragment.binding.findMember.getText().toString();
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
