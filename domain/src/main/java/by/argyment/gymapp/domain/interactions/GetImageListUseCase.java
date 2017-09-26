package by.argyment.gymapp.domain.interactions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import by.argyment.gymapp.data.entity.Image;
import by.argyment.gymapp.data.net.RestService;
import by.argyment.gymapp.domain.entity.UserImage;
import by.argyment.gymapp.domain.extra.Strings;
import by.argyment.gymapp.domain.interactions.base.UseCase;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * @author Olga Rudzko
 */

public class GetImageListUseCase extends UseCase<String, List<UserImage>> {
    RestService rest;

    @Inject
    public GetImageListUseCase(RestService rest) {
        this.rest = rest;
    }

    @Override
    protected Observable<List<UserImage>> buildUseCase(final String param) {
        String ready = Strings.EMAILIS.concat(Strings.CH).concat(param).concat(Strings.CH);
        return rest.getImages(ready).map(new Function<List<Image>, List<UserImage>>() {
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
                        newImg.setDay(img.getDay());
                        newList.add(newImg);
                    }
                    Collections.sort(newList, new Comparator<UserImage>() {
                        @Override
                        public int compare(UserImage userImage, UserImage t1) {
                            return (userImage.getDay()>(t1.getDay())) ? 1 : -1;
                        }
                    });
                }
                return newList;
            }
        });
    }

}