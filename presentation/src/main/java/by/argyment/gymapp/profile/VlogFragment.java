package by.argyment.gymapp.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import by.argyment.gymapp.base.BaseFragment;
import by.argyment.gymapp.databinding.FragmentVlogBinding;

/**
 * @author Olga Rudzko
 */

public class VlogFragment extends BaseFragment {


    FragmentVlogBinding binding;
    VlogHandler vlogHandler;

    public static VlogFragment newInstance(FragmentManager manager) {
        Fragment fragment = manager.findFragmentByTag(VlogFragment.class.getName());
        return (fragment != null && fragment instanceof VlogFragment) ?
                (VlogFragment) fragment : new VlogFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        vlogHandler = new VlogHandler(this);
        this.handler = vlogHandler;
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentVlogBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setUser(MyPage.getInstance());
        binding.setHandler(vlogHandler);
    }
}
