package by.argyment.gymapp.base;

/**
 * @author Olga Rudzko
 * Prototype of the view model of BaseActivity
 * @see BaseActivity
 */

public interface BaseViewModel {

    void init();
    void resume();
    void pause();
    void release();

}
