package by.argyment.gymapp.profile;

import by.argyment.gymapp.base.BaseItemViewHolder;
import by.argyment.gymapp.databinding.ItemImageBinding;
import by.argyment.gymapp.domain.entity.UserImage;

/**
 * @author Olga Rudzko
 */

public class MyPageImgItemViewHolder extends
        BaseItemViewHolder<UserImage, MyPageImgItemViewModel, ItemImageBinding> {
    public MyPageImgItemViewHolder(ItemImageBinding dataBinding, MyPageImgItemViewModel viewModel) {
        super(dataBinding, viewModel);
        dataBinding.setImageItem(viewModel);
    }
}
