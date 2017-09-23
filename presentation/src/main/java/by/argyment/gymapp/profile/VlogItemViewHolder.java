package by.argyment.gymapp.profile;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import by.argyment.gymapp.base.BaseItemViewHolder;
import by.argyment.gymapp.databinding.ItemNewsBinding;
import by.argyment.gymapp.databinding.ItemVlogBinding;
import by.argyment.gymapp.domain.entity.Video;

/**
 * @author Olga Rudzko
 */

class VlogItemViewHolder extends BaseItemViewHolder<Video, VlogItemViewModel, ItemVlogBinding> {

    ImageView play;
    TextView title;
    public VlogItemViewHolder(ItemVlogBinding binding, VlogItemViewModel vlogItem) {
        super(binding, vlogItem);
        binding.setVlogItem(vlogItem);
        play=binding.playButton;
        title=binding.videoTitle;
    }
}
