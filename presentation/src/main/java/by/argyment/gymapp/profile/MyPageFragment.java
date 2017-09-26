package by.argyment.gymapp.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import by.argyment.gymapp.base.BaseFragment;
import by.argyment.gymapp.extra.Strings;
import by.argyment.gymapp.databinding.FragmentMypageBinding;

/**
 * @author Olga Rudzko
 */

public class MyPageFragment extends BaseFragment {

    FragmentMypageBinding binding;
    public static final String CHECKIN = Strings.CHECKIN;

    private MyPageHandler currentHandler;
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
        currentHandler=new MyPageHandler(this);
        this.handler=currentHandler;
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            currentHandler.increaseStatus = bundle.getBoolean(CHECKIN);
            bundle.putBoolean(CHECKIN, false);
        }
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
        binding.setHandler(currentHandler);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.myGallery.setLayoutManager(new GridLayoutManager(getContext(), 3));
        binding.myGallery.setAdapter(currentHandler.adapter);
    }
}
