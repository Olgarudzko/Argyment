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

public class AddImageUseCase extends UseCase<UserImage, UserImage> {
    RestService rest;

    @Inject
    public AddImageUseCase(RestService rest) {
        this.rest = rest;
    }

    @Override
    protected Observable<UserImage> buildUseCase(UserImage param) {
        Image newImage = new Image();
        newImage.setEmail(param.getEmail());
        newImage.setLink(param.getLink());
        return rest.addImage(newImage).map(new Function<Image, UserImage>() {
            @Override
            public UserImage apply(@NonNull Image image) throws Exception {
               UserImage img=new UserImage();
                img.setEmail(image.getEmail());
                img.setObjectId(image.getObjectId());
                img.setLink(image.getLink());
                img.setDay(System.currentTimeMillis());
                return img;
            }
        });
    }
}
