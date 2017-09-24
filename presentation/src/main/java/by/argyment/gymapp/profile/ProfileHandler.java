package by.argyment.gymapp.profile;

import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import by.argyment.gymapp.R;
import by.argyment.gymapp.greeting.GreetActivity;

/**
 * @author Olga Rudzko
 */

public class ProfileHandler {

    ProfileActivity activity;

    public ObservableBoolean ok=new ObservableBoolean(true);

    public ProfileHandler(ProfileActivity activity){
        this.activity=activity;
    }

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

    public static void showFragment(FragmentManager manager, Fragment fragment, boolean addToBackStack){
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.replace(R.id.container, fragment, fragment.getClass().getName());
        if (addToBackStack) transaction.addToBackStack(null);
        transaction.commit();
    }

    public void reTry(View view){
        ok.set(true);
        activity.goTo(new Intent(activity, GreetActivity.class));
    }
}
