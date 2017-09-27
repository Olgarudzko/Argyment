package by.argyment.gymapp.base;

import android.content.Intent;

/**
 * @author Olga Rudzko
 *
 * Prototype of the view model of BaseFragment
 * @see BaseFragment
 */

public interface BaseFragmentHandler {

    void init();

    void resume();

    void viewCreated();

    void pause();

    void release();

    void activityResult(int requestCode, int resultCode, Intent data);
}
