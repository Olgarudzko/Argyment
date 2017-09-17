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
import by.argyment.gymapp.databinding.FragmentSearchBinding;

/**
 * @author Olga Rudzko
 */

public class SearchFragment extends BaseFragment {

    public SearchHandler currentHandler;
    public SearchAdapter adapter;
    FragmentSearchBinding binding;

    public static SearchFragment newInstance(FragmentManager manager) {
        Fragment fragment = manager.findFragmentByTag(SearchFragment.class.getName());
        return (fragment != null && fragment instanceof SearchFragment) ?
                (SearchFragment) fragment : new SearchFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        currentHandler=new SearchHandler(this);
        this.handler=currentHandler;
        super.onCreate(savedInstanceState);
        adapter = new SearchAdapter(this);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setMember(MemberPage.getInstance());
        binding.setHandler(currentHandler);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.usersList.setLayoutManager(new GridLayoutManager(getContext(), 3));
        binding.usersList.setAdapter(adapter);
    }

}
