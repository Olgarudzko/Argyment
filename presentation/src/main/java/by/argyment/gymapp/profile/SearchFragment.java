package by.argyment.gymapp.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import by.argyment.gymapp.base.BaseFragment;
import by.argyment.gymapp.databinding.FragmentSearchBinding;
import by.argyment.gymapp.domain.entity.UserImage;
import by.argyment.gymapp.domain.entity.UserProfile;
import by.argyment.gymapp.domain.interactions.GetImageListUseCase;
import by.argyment.gymapp.domain.interactions.GetProfileListUseCase;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

/**
 * @author Olga Rudzko
 */

public class SearchFragment extends BaseFragment {

    public SearchAdapter adapter;
    private FragmentSearchBinding binding;
    private GetProfileListUseCase getProfiles;
    private GetImageListUseCase getImages;

    public static SearchFragment newInstance(FragmentManager manager) {
        Fragment fragment = manager.findFragmentByTag(SearchFragment.class.getName());
        return (fragment != null && fragment instanceof SearchFragment) ?
                (SearchFragment) fragment : new SearchFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new SearchAdapter(this);
        getProfiles = new GetProfileListUseCase();
        getProfiles.makeRequest(null, new DisposableObserver<List<UserProfile>>() {
            @Override
            public void onNext(@NonNull List<UserProfile> userProfiles) {
                for (int i=0; i<userProfiles.size(); i++) {
                    if (userProfiles.get(i).getEmail().equals(MyPage.getInstance().getEmail())){
                        userProfiles.remove(i);
                    }
                }
                adapter.setItems(userProfiles);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setSearch(this);
        binding.setMember(MemberPage.getInstance());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.usersList.setLayoutManager(new GridLayoutManager(getContext(), 3));
        binding.usersList.setAdapter(adapter);
    }

    public void loadMemberPage(UserProfile member){
        MemberPage.getInstance().setObjectId(member.getObjectId());
        MemberPage.getInstance().username.set(member.getUsername());
        MemberPage.getInstance().isTrainer.set(member.isTrainer());
        MemberPage.getInstance().userpic.set(member.getUserpic());
        MemberPage.getInstance().status.set(member.getStatus());
        MemberPage.getInstance().stars.set(member.getStars());
        getImages=new GetImageListUseCase();
        getImages.makeRequest(member.getEmail(), new DisposableObserver<List<UserImage>>() {
            @Override
            public void onNext(@NonNull List<UserImage> userImages) {
                List<UserImage> newList=new ArrayList<UserImage>();
                for (UserImage img: userImages){
                    UserImage newImg=new UserImage();
                    newImg.setObjectId(img.getObjectId());
                    newImg.setLink(img.getLink());
                    newList.add(newImg);
                }
                MemberPage.getInstance().memberPics=newList;
                MemberPage.getInstance().visibility.set(true);
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
    public void onDestroy() {
        super.onDestroy();
        getProfiles.dispose();
        getImages.dispose();
    }
}
