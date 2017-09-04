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
        ProfileHandler handler=new ProfileHandler(this);
        ActivityProfileBinding binding= DataBindingUtil.setContentView(this, R.layout.activity_profile);
        binding.setProfile(model);
        binding.setHandler(handler);
        super.onCreate(savedInstanceState);
        MyPage.getInstance().setEmail(getIntent().getStringExtra(USERMAIL));
        boolean checkIn=getIntent().getBooleanExtra(CHECKIN, false);
        if (savedInstanceState==null){
            ProfileHandler.showFragment(getSupportFragmentManager(), MyPageFragment.newInstance(getSupportFragmentManager(), checkIn), false);
        }
    }
}
