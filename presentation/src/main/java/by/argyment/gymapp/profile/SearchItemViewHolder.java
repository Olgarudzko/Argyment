package by.argyment.gymapp.profile;

import by.argyment.gymapp.base.BaseItemViewHolder;
import by.argyment.gymapp.databinding.ItemSearchBinding;
import by.argyment.gymapp.domain.entity.UserProfile;


/**
 * @author Olga Rudzko
 */

public class SearchItemViewHolder extends
        BaseItemViewHolder<UserProfile, SearchItemViewModel, ItemSearchBinding> {

    public SearchItemViewHolder(ItemSearchBinding dataBinding, SearchItemViewModel viewModel) {
        super(dataBinding, viewModel);
        dataBinding.setSearchItem(viewModel);
    }
}



