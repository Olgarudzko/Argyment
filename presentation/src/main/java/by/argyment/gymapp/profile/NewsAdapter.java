package by.argyment.gymapp.profile;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import by.argyment.gymapp.base.BaseAdapter;
import by.argyment.gymapp.databinding.ItemNewsBinding;

/**
 * @author Olga Rudzko
 */

public class NewsAdapter extends BaseAdapter {


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        NewsItemViewModel model=new NewsItemViewModel();
        ItemNewsBinding binding=ItemNewsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new NewsItemViewHolder(binding, model);
    }
}
