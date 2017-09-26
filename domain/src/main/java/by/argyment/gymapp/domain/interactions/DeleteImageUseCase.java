package by.argyment.gymapp.domain.interactions;

import javax.inject.Inject;

import by.argyment.gymapp.data.net.RestService;
import by.argyment.gymapp.domain.interactions.base.UseCase;
import io.reactivex.Observable;

/**
 * @author Olga Rudzko
 */

public class DeleteImageUseCase extends UseCase<String, Long> {
    RestService rest;

    @Inject
    public DeleteImageUseCase(RestService rest) {
        this.rest = rest;
    }

    @Override
    protected Observable<Long> buildUseCase(String param) {
        return rest.deleteImage(param);
    }
}
