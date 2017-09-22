package by.argyment.gymapp.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * @author Olga Rudzko
 */

public abstract class BaseFragment extends Fragment {

    protected BaseFragmentHandler handler;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler.init();
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.resume();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        handler.viewCreated();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        handler.activityResult(requestCode, resultCode, data);
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.release();
    }

}
