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

public class SearchFragment extends BaseFragment {

    public SearchAdapter adapter;

    public static SearchFragment newInstance(FragmentManager manager) {
        Fragment fragment = manager.findFragmentByTag(SearchFragment.class.getName());
        return (fragment != null && fragment instanceof SearchFragment) ?
                (SearchFragment) fragment : new SearchFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter=new SearchAdapter();
    }

//    public static final String EMAIL = "EMAIL";
//    public String email;
//    public static SearchFragment newInstance(FragmentManager manager, String email) {
//        Fragment fragment = manager.findFragmentByTag(SearchFragment.class.getName());
//        if (fragment != null && fragment instanceof SearchFragment) fragment.onDestroy();
//        SearchFragment f = new SearchFragment();
//        Bundle b = new Bundle();
//        b.putString(EMAIL, email);
//        f.setArguments(b);
//        return f;
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Bundle bundle = getArguments();
//        if (bundle != null) {
//            email = bundle.getString(EMAIL);
//        }
//    }

}
