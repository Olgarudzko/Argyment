package by.argyment.gymapp.domain.interactions;

import javax.inject.Inject;

import by.argyment.gymapp.data.entity.Image;
import by.argyment.gymapp.data.net.RestService;
import by.argyment.gymapp.domain.entity.UserImage;
import by.argyment.gymapp.domain.interactions.base.UseCase;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * @author Olga Rudzko
 */

public class DeleteImageUseCase extends UseCase<String, UserImage> {
    RestService rest;

    @Inject
    public DeleteImageUseCase(RestService rest) {
        this.rest = rest;
    }

    @Override
    protected Observable<UserImage> buildUseCase(String param) {
        return rest.deleteImage(param).map(new Function<Image, UserImage>() {
            @Override
            public UserImage apply(@NonNull Image image) throws Exception {
                UserImage img=new UserImage();
                img.setDay(image.getDay());
                img.setObjectId(image.getObjectId());
                img.setLink(image.getLink());
                img.setEmail(image.getEmail());
                return img;
            }
        });
    }
}
