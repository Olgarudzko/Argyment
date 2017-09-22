package by.argyment.gymapp.profile;

import by.argyment.gymapp.base.BaseItemViewHolder;
import by.argyment.gymapp.databinding.ItemNewsBinding;
import by.argyment.gymapp.domain.entity.News;

/**
 * @author Olga Rudzko
 */

public class NewsItemViewHolder extends BaseItemViewHolder<News, NewsItemViewModel, ItemNewsBinding>{
    public NewsItemViewHolder(ItemNewsBinding dataBinding, NewsItemViewModel viewModel) {
        super(dataBinding, viewModel);
    }
}
