package by.argyment.gymapp.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import by.argyment.gymapp.base.BaseFragment;
import by.argyment.gymapp.databinding.FragmentMypageBinding;

/**
 * @author Olga Rudzko
 */

public class MyPageFragment extends BaseFragment {

    private FragmentMypageBinding binding;

    public static MyPageFragment newInstance(FragmentManager manager) {
        Fragment fragment = manager.findFragmentByTag(MyPageFragment.class.getName());
        return (fragment != null && fragment instanceof MyPageFragment) ?
                (MyPageFragment) fragment : new MyPageFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMypageBinding.inflate(inflater);
        return binding.getRoot();
//        return inflater.inflate(R.layout.fragment_mypage, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setMypage(MyPage.getInstance());
        binding.setHandler(this);
    }

    public void mainPhoto(View view){

    }

    public void addPhoto(View view){

    }

    public void removePhoto(View view){

    }
}
