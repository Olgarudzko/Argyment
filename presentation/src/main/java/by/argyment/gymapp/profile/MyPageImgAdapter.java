package by.argyment.gymapp.profile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import by.argyment.gymapp.base.BaseAdapter;
import by.argyment.gymapp.base.BaseItemViewHolder;
import by.argyment.gymapp.databinding.ItemImageBinding;
import by.argyment.gymapp.domain.entity.UserImage;

/**
 * @author Olga Rudzko
 */

public class MyPageImgAdapter extends BaseAdapter<UserImage, MyPageImgItemViewModel> {

    @Override
    public BaseItemViewHolder<UserImage, MyPageImgItemViewModel, ?> onCreateViewHolder(ViewGroup parent, int viewType) {
        MyPageImgItemViewModel imgModel = new MyPageImgItemViewModel();
        ItemImageBinding binding = ItemImageBinding.inflate
                (LayoutInflater.from(parent.getContext()), parent, false);
        return new MyPageImgItemViewHolder(binding, imgModel);
    }

    @Override
    public void onBindViewHolder(BaseItemViewHolder<UserImage, MyPageImgItemViewModel, ?> holder, int position) {
        super.onBindViewHolder(holder, position);
        final UserImage img = items.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MemberPage.getInstance().visibility.get()) {
                    MemberPage.getInstance().userpic.set(img.getLink());
                } else {
                    MyPage.getInstance().userpic.set(img.getLink());
                }
            }
        });
    }
}
