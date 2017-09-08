package by.argyment.gymapp.profile;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import java.io.File;

import by.argyment.gymapp.R;
import by.argyment.gymapp.base.BaseActivity;
import by.argyment.gymapp.databinding.ActivityProfileBinding;
import by.argyment.gymapp.domain.entity.UserBitmap;
import by.argyment.gymapp.domain.interactions.AddBitmapUseCase;
import by.argyment.gymapp.extra.Strings;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

/**
 * @author Olga Rudzko
 */

public class ProfileActivity extends BaseActivity {

    public static final String USERMAIL = Strings.USERMAIL;
    public static final String CHECKIN = Strings.CHECKIN;

//    private static final int PICK_IMAGE = 100;
//    private MyPageFragment fragment;
//    private AddBitmapUseCase addBitmap = new AddBitmapUseCase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ProfileModel model = new ProfileModel();
        this.viewModel = model;
        ProfileHandler handler = new ProfileHandler(this);
        ActivityProfileBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        binding.setProfile(model);
        binding.setHandler(handler);
        super.onCreate(savedInstanceState);
        MyPage.getInstance().setEmail(getIntent().getStringExtra(USERMAIL));
        boolean checkIn = getIntent().getBooleanExtra(CHECKIN, false);
        if (savedInstanceState == null) {
            MyPageFragment newMyPage = MyPageFragment.newInstance(getSupportFragmentManager(), checkIn);
            ProfileHandler.showFragment(getSupportFragmentManager(), newMyPage, false);
            model.setMypage(newMyPage);
        }
    }
}


//    public void uploadBitmap(MyPageFragment fragment){
//        this.fragment=fragment;
//        Intent gallery = new Intent(Intent.ACTION_PICK,
//                android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
//        startActivityForResult(gallery, PICK_IMAGE);
//    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
//            File file=new File(data.getData().getPath());
//            UserBitmap bitmap=new UserBitmap();
//            bitmap.setFile(file);
//            bitmap.setName(String.valueOf(System.currentTimeMillis()).concat(MyPage.getInstance().username.get()));
//            addBitmap.makeRequest(bitmap, new DisposableObserver<Void>() {
//                @Override
//                public void onNext(@NonNull Void aVoid) {
//
//                }
//
//                @Override
//                public void onError(@NonNull Throwable e) {
//
//                }
//
//                @Override
//                public void onComplete() {
//
//                }
//            });
//            fragment.addImage(file.getPath());
//        }
//    }
//}
