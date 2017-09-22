package by.argyment.gymapp.domain.interactions;

import javax.inject.Inject;

import by.argyment.gymapp.data.entity.DataBitmap;
import by.argyment.gymapp.data.net.RestService;
import by.argyment.gymapp.domain.entity.UserBitmap;
import by.argyment.gymapp.domain.interactions.base.UseCase;
import io.reactivex.Observable;

/**
 * @author Olga Rudzko
 */

public class AddBitmapUseCase extends UseCase<UserBitmap, Void> {
    RestService rest;

    @Inject
    public AddBitmapUseCase(RestService rest) {
        this.rest = rest;
    }

    @Override
    protected Observable<Void> buildUseCase(UserBitmap param) {
        DataBitmap bitmap=new DataBitmap();
        bitmap.setFile(param.getFile());
        bitmap.setName(param.getName());
        return rest.addBitmap(bitmap);
    }
}
