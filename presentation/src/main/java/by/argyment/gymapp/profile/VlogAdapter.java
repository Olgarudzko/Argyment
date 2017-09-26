package by.argyment.gymapp.profile;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import by.argyment.gymapp.base.BaseAdapter;
import by.argyment.gymapp.base.BaseItemViewHolder;
import by.argyment.gymapp.databinding.ItemVlogBinding;
import by.argyment.gymapp.domain.entity.Video;

/**
 * @author Olga Rudzko
 */

public class VlogAdapter extends BaseAdapter<Video, VlogItemViewModel> {

    VlogFragment fragment;

    public VlogAdapter(VlogFragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public BaseItemViewHolder<Video, VlogItemViewModel, ?> onCreateViewHolder(ViewGroup parent, int viewType) {
        VlogItemViewModel vlogItem=new VlogItemViewModel();
        ItemVlogBinding binding=ItemVlogBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new VlogItemViewHolder(binding, vlogItem);
    }

    @Override
    public void onBindViewHolder(BaseItemViewHolder<Video, VlogItemViewModel, ?> holder, int position) {
        super.onBindViewHolder(holder, position);
        final Video video=items.get(position);
        View.OnClickListener listener=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(video.getUrl())));
            }
        };
        ((VlogItemViewHolder)holder).play.setOnClickListener(listener);
        ((VlogItemViewHolder)holder).title.setOnClickListener(listener);
    }
}
