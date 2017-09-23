package by.argyment.gymapp.profile;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import by.argyment.gymapp.base.BaseAdapter;
import by.argyment.gymapp.base.BaseItemViewHolder;
import by.argyment.gymapp.databinding.ItemNewsBinding;
import by.argyment.gymapp.domain.entity.News;

/**
 * @author Olga Rudzko
 */

public class NewsAdapter extends BaseAdapter<News, NewsItemViewModel> {

    @Override
    public BaseItemViewHolder<News, NewsItemViewModel, ?> onCreateViewHolder(ViewGroup parent, int viewType) {
        NewsItemViewModel model=new NewsItemViewModel();
        ItemNewsBinding binding=ItemNewsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new NewsItemViewHolder(binding, model);
    }
}
