package by.argyment.gymapp.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import by.argyment.gymapp.base.BaseFragment;
import by.argyment.gymapp.domain.interactions.GetImageListUseCase;
import by.argyment.gymapp.extra.Strings;
import by.argyment.gymapp.databinding.FragmentMypageBinding;
import by.argyment.gymapp.domain.entity.UserImage;
import by.argyment.gymapp.domain.entity.UserProfile;
import by.argyment.gymapp.domain.interactions.AddImageUseCase;
import by.argyment.gymapp.domain.interactions.DeleteImageUseCase;
import by.argyment.gymapp.domain.interactions.UpdateProfileUseCase;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

/**
 * @author Olga Rudzko
 */

public class MyPageFragment extends BaseFragment {

    private FragmentMypageBinding binding;
    public static final String CHECKIN = Strings.CHECKIN;
    private boolean increaseStatus;
    private String mainImg;
    public MyPageImgAdapter adapter;
    private GetImageListUseCase getImages=new GetImageListUseCase();
    private UpdateProfileUseCase updateUser=new UpdateProfileUseCase();
    private DeleteImageUseCase removeImg=new DeleteImageUseCase();
    private AddImageUseCase addImg=new AddImageUseCase();
    List<UserImage> list=new ArrayList<>();

    public static MyPageFragment newInstance(FragmentManager manager, boolean isCheckedIn) {
        Fragment fragment = manager.findFragmentByTag(MyPageFragment.class.getName());
        MyPageFragment result = (fragment != null && fragment instanceof MyPageFragment) ?
                (MyPageFragment) fragment : new MyPageFragment();
        Bundle b = new Bundle();
        b.putBoolean(CHECKIN, isCheckedIn);
        result.setArguments(b);
        return result;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter=new MyPageImgAdapter();
        Bundle bundle = getArguments();
        if (bundle != null) {
            increaseStatus = bundle.getBoolean(CHECKIN);
            bundle.putBoolean(CHECKIN, false);
        }
        mainImg=MyPage.getInstance().userpic.get();
        fillAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMypageBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setMypage(MyPage.getInstance());
        binding.setHandler(this);
        mainImg=MyPage.getInstance().userpic.get();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.myGallery.setLayoutManager(new GridLayoutManager(getContext(), 3));
        binding.myGallery.setAdapter(adapter);
    }

    public void mainPhoto(View view) {
        mainImg=MyPage.getInstance().userpic.get();
    }

    public void addPhoto(View view) {
        UserImage image=new UserImage();
        image.setEmail(MyPage.getInstance().getEmail());
        image.setLink("https://goo.gl/hgt7d4");
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
        fillAdapter();
    }

    public void removePhoto(View view) {
        String link=MyPage.getInstance().userpic.get();
        String id= Strings.EMPTY;
        for (UserImage img: list) {
            if (img.getLink().equals(link)){
                id=img.getObjectId();
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
            fillAdapter();
        }
        MyPage.getInstance().userpic.set(mainImg);
    }

    public boolean isIncreaseStatus() {
        return increaseStatus;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (increaseStatus)        {
            MyPage.getInstance().status.set(MyPage.getInstance().status.get()+1);
            MyPage.getInstance().setTimeCheckin(System.currentTimeMillis());
            increaseStatus=false;
        }
        UserProfile updated=new UserProfile();
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

    private void fillAdapter(){
        getImages.makeRequest(MyPage.getInstance().getEmail(), new DisposableObserver<List<UserImage>>() {
            @Override
            public void onNext(@NonNull List<UserImage> userImages) {
                list=userImages;
                adapter.setItems(list);
                Log.d("ADAPTER FILLED", String.valueOf(adapter.getItemCount()));
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
        getImages.dispose();
        addImg.dispose();
        updateUser.dispose();
        removeImg.dispose();
    }
}
