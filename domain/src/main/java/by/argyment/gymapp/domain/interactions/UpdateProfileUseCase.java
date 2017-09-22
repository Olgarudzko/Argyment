package by.argyment.gymapp.domain.interactions;

import javax.inject.Inject;

import by.argyment.gymapp.data.entity.Profile;
import by.argyment.gymapp.data.net.RestService;
import by.argyment.gymapp.domain.entity.UserProfile;
import by.argyment.gymapp.domain.interactions.base.UseCase;
import io.reactivex.Observable;

/**
 * @author Olga Rudzko
 */

public class UpdateProfileUseCase extends UseCase<UserProfile, Void> {
    RestService rest;

    @Inject
    public UpdateProfileUseCase(RestService rest) {
        this.rest = rest;
    }

    @Override
    protected Observable<Void> buildUseCase(UserProfile param) {
        Profile updated=new Profile();
        updated.setEmail(param.getEmail());
        updated.setUsername(param.getUsername());
        updated.setPassword(param.getPassword());
        updated.setTrainer(param.isTrainer());
        updated.setAdmin(param.isAdmin());
        updated.setUserpic(param.getUserpic());
        updated.setSlon(param.getSlon());
        updated.setStars(param.getStars());
        updated.setStatus(param.getStatus());
        updated.setObjectId(param.getObjectId());
        updated.setTimeCheckin(param.getTimeCheckin());
        updated.setTimeStar(param.getTimeStar());
        return rest.updateProfile(updated, param.getObjectId());
    }
}
