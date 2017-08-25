package by.argyment.gymapp.base;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * @author Olga Rudzko
 */

public abstract class BaseItemViewHolder
        <Model, ViewModel extends BaseItemViewModel<Model>, DataBinding extends ViewDataBinding>
        extends RecyclerView.ViewHolder{


    private DataBinding dataBinding;
    private ViewModel viewModel;


    public BaseItemViewHolder(DataBinding db, ViewModel vm) {
        super(db.getRoot());
        this.viewModel=vm;
        this.dataBinding=db;
        vm.init();
    }

    public void bindTo(Model item, int position){
        viewModel.setItem(item, position);
        dataBinding.executePendingBindings();
    }

    public void release(){
        viewModel.release();
    }
}
