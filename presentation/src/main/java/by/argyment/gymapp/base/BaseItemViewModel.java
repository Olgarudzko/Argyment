package by.argyment.gymapp.base;

/**
 * @author Olga Rudzko
 */

public abstract class BaseItemViewModel<Model> implements BaseViewModel {

    public abstract void setItem(Model item, int position);

    @Override
    public void init() {

    }

    @Override
    public void release() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }
}
