package by.argyment.gymapp.profile;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import by.argyment.gymapp.R;
import by.argyment.gymapp.base.BaseActivity;
import by.argyment.gymapp.databinding.ActivityProfileBinding;

/**
 * @author Olga Rudzko
 */

public class ProfileActivity extends BaseActivity {

    public static final String USERMAIL="USERMAIL";
    public static final String CHECKIN="CHECKIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ProfileModel model=new ProfileModel();
        this.viewModel=model;
        ActivityProfileBinding binding= DataBindingUtil.setContentView(this, R.layout.activity_profile);
        binding.setProfile(model);
        super.onCreate(savedInstanceState);
        if (savedInstanceState==null){
            showFragment(getSupportFragmentManager(), MyPageFragment.newInstance(getSupportFragmentManager()), true);
        }
    }

    public void goHome(View view) {
        showFragment(getSupportFragmentManager(), MyPageFragment.newInstance(getSupportFragmentManager()), true);
    }

    public void goSearch(View view) {
        showFragment(getSupportFragmentManager(), SearchFragment.newInstance(getSupportFragmentManager()), true);
    }

    public void goNews(View view) {
        showFragment(getSupportFragmentManager(), NewsFragment.newInstance(getSupportFragmentManager()), true);
    }

    public void goVlog(View view) {
        showFragment(getSupportFragmentManager(), VlogFragment.newInstance(getSupportFragmentManager()), true);
    }

    public void goInfo(View view) {
        showFragment(getSupportFragmentManager(), InfoFragment.newInstance(getSupportFragmentManager()), true);
    }

    public static void showFragment(FragmentManager manager, Fragment fragment, boolean addToBackStack){
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.replace(R.id.container, fragment, fragment.getClass().getName());
        if (addToBackStack) transaction.addToBackStack(null);
        transaction.commit();
    }
}
