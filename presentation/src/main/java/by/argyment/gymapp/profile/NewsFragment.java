package by.argyment.gymapp.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import by.argyment.gymapp.base.BaseFragment;
import by.argyment.gymapp.databinding.FragmentNewsBinding;

/**
 * @author Olga Rudzko
 *
 * Fragment which froms the view of news feed, gives opportunity to add news for administrators,
 * to add bonuses for trainers and to win bonuses for clients
 * All business logic is implemented in NewsHandler
 * @see NewsHandler
 */

public class NewsFragment extends BaseFragment {

    FragmentNewsBinding binding;
    private NewsHandler newsHandler;
    public static NewsFragment newInstance(FragmentManager manager) {
        Fragment fragment = manager.findFragmentByTag(NewsFragment.class.getName());
        return (fragment != null && fragment instanceof NewsFragment) ?
                (NewsFragment) fragment : new NewsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        newsHandler= new NewsHandler(this);
        this.handler=newsHandler;
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= FragmentNewsBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setUser(MyPage.getInstance());
        binding.setHandler(newsHandler);
    }
}
