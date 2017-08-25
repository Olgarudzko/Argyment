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

public class InfoFragment extends BaseFragment {

    public static InfoFragment newInstance(FragmentManager manager) {
        Fragment fragment = manager.findFragmentByTag(InfoFragment.class.getName());
        return (fragment != null && fragment instanceof InfoFragment) ?
                (InfoFragment) fragment : new InfoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_info, container, false);
    }

}
