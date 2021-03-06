package by.argyment.gymapp.profile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import by.argyment.gymapp.databinding.FragmentInfoBinding;
import by.argyment.gymapp.extra.Strings;

/**
 * @author Olga Rudzko
 *
 * Fragment without any requests to server, provides general information about the fitness centre,
 * contains social buttons and clickable phone numbers
 */

public class InfoFragment extends Fragment {

    FragmentInfoBinding binding;

    public static InfoFragment newInstance(FragmentManager manager) {
        Fragment fragment = manager.findFragmentByTag(InfoFragment.class.getName());
        return (fragment != null && fragment instanceof InfoFragment) ?
                (InfoFragment) fragment : new InfoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentInfoBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setInfo(this);
    }


    public void goVk(View view) {
        Uri uri = Uri.parse(Strings.VK);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }

    public void goInsta(View view) {
        Uri uri = Uri.parse(Strings.INSTAGRAM);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }

    public void goFb(View view) {
        Uri uri = Uri.parse(Strings.FACEBOOK);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}
