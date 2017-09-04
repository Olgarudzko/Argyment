package by.argyment.gymapp.domain.interactions;

import by.argyment.gymapp.data.net.RestService;
import by.argyment.gymapp.domain.interactions.base.UseCase;
import io.reactivex.Observable;

/**
 * @author Olga Rudzko
 */

public class DeleteImageUseCase extends UseCase<String, Void> {

    @Override
    protected Observable<Void> buildUseCase(String param) {
        return RestService.getInstance().deleteImage(param);
    }
}
