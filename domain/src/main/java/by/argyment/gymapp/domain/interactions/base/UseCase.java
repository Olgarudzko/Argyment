package by.argyment.gymapp.domain.interactions.base;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Olga Rudzko
 */

public abstract class UseCase<OnReceive, OnReturn> {

    private Disposable disposable;

    protected abstract Observable<OnReturn> buildUseCase(OnReceive param);

    public void makeRequest(OnReceive param, DisposableObserver<OnReturn> observer) {
        disposable = buildUseCase(param).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribeWith(observer);
    }

    public void dispose() {
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
    }

}
