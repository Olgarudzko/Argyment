package by.argyment.gymapp.base;

import android.content.Intent;

import by.argyment.gymapp.GymApplication;

/**
 * @author Olga Rudzko
 */

public interface BaseFragmentHandler {

    void init();

    void resume();

    void viewCreated();

    void pause();

    void release();

    void activityResult(int requestCode, int resultCode, Intent data);
}
