package by.argyment.gymapp.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import by.argyment.gymapp.R;
import by.argyment.gymapp.base.BaseFragment;

/**
 * @author Olga Rudzko
 */

public class VlogFragment extends Fragment {

    public VlogAdapter adapter;

    public static VlogFragment newInstance(FragmentManager manager) {
        Fragment fragment = manager.findFragmentByTag(VlogFragment.class.getName());
        return (fragment != null && fragment instanceof VlogFragment) ?
                (VlogFragment) fragment : new VlogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_vlog, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter=new VlogAdapter();
    }
}
