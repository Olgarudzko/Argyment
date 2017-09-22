package by.argyment.gymapp.domain.interactions;

import javax.inject.Inject;

import by.argyment.gymapp.data.entity.Image;
import by.argyment.gymapp.data.net.RestService;
import by.argyment.gymapp.domain.entity.UserImage;
import by.argyment.gymapp.domain.interactions.base.UseCase;
import io.reactivex.Observable;

/**
 * @author Olga Rudzko
 */

public class AddImageUseCase extends UseCase<UserImage, Void> {
    RestService rest;

    @Inject
    public AddImageUseCase(RestService rest) {
        this.rest = rest;
    }

    @Override
    protected Observable<Void> buildUseCase(UserImage param) {
        Image newImage = new Image();
        newImage.setEmail(param.getEmail());
        newImage.setLink(param.getLink());
        return rest.addImage(newImage);
    }
}
