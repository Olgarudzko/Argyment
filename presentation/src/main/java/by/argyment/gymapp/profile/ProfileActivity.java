package by.argyment.gymapp.profile;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import by.argyment.gymapp.R;
import by.argyment.gymapp.base.BaseActivity;
import by.argyment.gymapp.databinding.ActivityProfileBinding;
import by.argyment.gymapp.extra.Strings;
import by.argyment.gymapp.greeting.GreetActivity;

/**
 * @author Olga Rudzko
 */

public class ProfileActivity extends BaseActivity {

    public static final String USERMAIL = Strings.USERMAIL;
    public static final String CHECKIN = Strings.CHECKIN;
    SharedPreferences sharedPreferences;
    public static final String SHARED_PROF= Strings.SHARED_PROF;
    public static final String SAVED_MAIL= Strings.SAVED_MAIL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ProfileModel model = new ProfileModel(this);
        this.viewModel = model;
        ActivityProfileBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        binding.setHandler(model);
        super.onCreate(savedInstanceState);
        Glide.with(this).load(R.drawable.loading).into(new GlideDrawableImageViewTarget(binding.prloading));
        String email;
        if ((email=getIntent().getStringExtra(USERMAIL))!=null) {
            MyPage.getInstance().setEmail(email);
            boolean checkIn = getIntent().getBooleanExtra(CHECKIN, false);
            if (savedInstanceState == null) {
                MyPageFragment newMyPage = MyPageFragment.newInstance(getSupportFragmentManager(), checkIn);
                ProfileModel.showFragment(getSupportFragmentManager(), newMyPage, false);
                model.setMypage(newMyPage);
            }
        } else {
            goTo(new Intent(this, GreetActivity.class));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        sharedPreferences=getSharedPreferences(SHARED_PROF, Context.MODE_PRIVATE);
        if (MyPage.getInstance().getEmail()==null){
            MyPage.getInstance().setEmail(sharedPreferences.getString(SAVED_MAIL, null));
            if (MyPage.getInstance().getEmail()==null){
                goTo(new Intent(this, GreetActivity.class));
            }
        }
        if ((int)(System.currentTimeMillis()-MyPage.getInstance().getTimeCheckin())>80000000){
            goTo(new Intent(this, GreetActivity.class));
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
