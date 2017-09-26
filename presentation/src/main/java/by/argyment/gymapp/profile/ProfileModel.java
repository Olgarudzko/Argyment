package by.argyment.gymapp.profile;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.ObservableBoolean;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

import javax.inject.Inject;

import by.argyment.gymapp.GymApplication;
import by.argyment.gymapp.R;
import by.argyment.gymapp.base.BaseViewModel;
import by.argyment.gymapp.data.entity.Profile;
import by.argyment.gymapp.domain.entity.UserProfile;
import by.argyment.gymapp.domain.interactions.GetProfileUseCase;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

/**
 * @author Olga Rudzko
 */

public class ProfileModel implements BaseViewModel {

    @Inject
    GetProfileUseCase getProfile;

    ProfileActivity activity;

    private MyPageFragment mypage;

    public MyPageFragment getMypage() {
        return mypage;
    }

    public void setMypage(MyPageFragment mypage) {
        this.mypage = mypage;
    }

    public ProfileModel(ProfileActivity activity) {
        this.activity=activity;
        GymApplication.appComponent.injectProfileModel(this);
    }

    private BroadcastReceiver connectCheck = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager manager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = manager.getActiveNetworkInfo();
            if (info == null || !info.isConnectedOrConnecting()) {
                ok.set(false);
            } else {
                ok.set(true);
            }
        }
    };

    public ObservableBoolean ok = new ObservableBoolean(true);

    @Override
    public void init() {

    }

    @Override
    public void resume() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        activity.registerReceiver(connectCheck, filter);
        getProfile.makeRequest(MyPage.getInstance().getEmail(), new DisposableObserver<UserProfile>() {
            @Override
            public void onNext(@NonNull UserProfile userProfile) {
                MyPage.getInstance().setPassword(userProfile.getPassword());
                MyPage.getInstance().username.set(userProfile.getUsername());
                MyPage.getInstance().userpic.set(userProfile.getUserpic());
                MyPage.getInstance().isAdmin.set(userProfile.isAdmin());
                MyPage.getInstance().isTrainer.set(userProfile.isTrainer());
                MyPage.getInstance().status.set(userProfile.getStatus());
                MyPage.getInstance().slon.set(userProfile.getSlon());
                MyPage.getInstance().setObjectId(userProfile.getObjectId());
                MyPage.getInstance().setTimeCheckin(userProfile.getTimeCheckin());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("!!!ProfModel/getProf", e.toString());
            }

            @Override
            public void onComplete() {

            }
        });

    }

    @Override
    public void pause() {
        activity.unregisterReceiver(connectCheck);
        getProfile.dispose();
    }

    @Override
    public void release() {}

    public void goHome(View view) {
        showFragment(activity.getSupportFragmentManager(),
                MyPageFragment.newInstance(activity.getSupportFragmentManager(), true), false);
    }

    public void goSearch(View view) {
        showFragment(activity.getSupportFragmentManager(),
                SearchFragment.newInstance(activity.getSupportFragmentManager()), true);
    }

    public void goNews(View view) {
        showFragment(activity.getSupportFragmentManager(),
                NewsFragment.newInstance(activity.getSupportFragmentManager()), true);
    }

    public void goVlog(View view) {
        showFragment(activity.getSupportFragmentManager(),
                VlogFragment.newInstance(activity.getSupportFragmentManager()), true);
    }

    public void goInfo(View view) {
        showFragment(activity.getSupportFragmentManager(),
                InfoFragment.newInstance(activity.getSupportFragmentManager()), true);
    }

    public static void showFragment(FragmentManager manager, Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, fragment, fragment.getClass().getName());
        if (addToBackStack) transaction.addToBackStack(null);
        transaction.commit();
    }
}
