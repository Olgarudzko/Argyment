package by.argyment.gymapp.profile;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import by.argyment.gymapp.base.BaseAdapter;
import by.argyment.gymapp.base.BaseItemViewHolder;
import by.argyment.gymapp.databinding.ItemImageBinding;
import by.argyment.gymapp.domain.entity.UserImage;

/**
 * @author Olga Rudzko
 */

public class MyPageImgAdapter extends BaseAdapter<UserImage, MyPageImgItemViewModel> {

    private UserImage img;

    @Override
    public BaseItemViewHolder<UserImage, MyPageImgItemViewModel, ?> onCreateViewHolder(ViewGroup parent, int viewType) {
        MyPageImgItemViewModel imgModel = new MyPageImgItemViewModel();
        ItemImageBinding binding = ItemImageBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyPageImgItemViewHolder(binding, imgModel);
    }

    @Override
    public void onBindViewHolder(BaseItemViewHolder<UserImage, MyPageImgItemViewModel, ?> holder, int position) {
        super.onBindViewHolder(holder, position);
        img=items.get(position);
    }

    public void showPhoto(){
        MyPage.getInstance().userpic.set(img.getLink());
    }
}
