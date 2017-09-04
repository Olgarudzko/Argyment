package by.argyment.gymapp.domain.interactions;

import java.util.ArrayList;
import java.util.List;

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

public class GetImageListUseCase extends UseCase<String, List<UserImage>> {

    @Override
    protected Observable<List<UserImage>> buildUseCase(final String param) {
        return RestService.getInstance().getImages().map(new Function<List<Image>, List<UserImage>>() {
            @Override
            public List<UserImage> apply(@NonNull List<Image> images) throws Exception {
                List<UserImage> newList = new ArrayList<UserImage>();
                for (Image img : images) {
                    if (img.getEmail().equals(param)) {
                        UserImage newImg = new UserImage();
                        newImg.setLink(img.getLink());
                        newImg.setEmail(param);
                        newImg.setLink(img.getLink());
                        newImg.setObjectId(img.getObjectId());
                        newList.add(newImg);
                    }
                }
                return newList;
            }
        });
    }

}