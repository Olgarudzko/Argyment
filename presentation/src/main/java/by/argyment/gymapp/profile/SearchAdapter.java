package by.argyment.gymapp.profile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import by.argyment.gymapp.base.BaseAdapter;
import by.argyment.gymapp.base.BaseItemViewHolder;
import by.argyment.gymapp.databinding.ItemSearchBinding;
import by.argyment.gymapp.domain.entity.UserProfile;

/**
 * @author Olga Rudzko
 */

public class SearchAdapter extends BaseAdapter<UserProfile, SearchItemViewModel> {

    SearchFragment fragment;

    public SearchAdapter(SearchFragment fragment){
        this.fragment=fragment;
    }

    @Override
    public SearchItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SearchItemViewModel model=new SearchItemViewModel();
        ItemSearchBinding binding = ItemSearchBinding.inflate
                (LayoutInflater.from(parent.getContext()), parent, false);
        return new SearchItemViewHolder(binding, model);
    }

    @Override
    public void onBindViewHolder(final BaseItemViewHolder<UserProfile, SearchItemViewModel, ?> holder, int position) {
        super.onBindViewHolder(holder, position);
        final UserProfile user = items.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                fragment.loadMemberPage(user);
            }
        });
    }
}
