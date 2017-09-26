package by.argyment.gymapp.domain.interactions;

import javax.inject.Inject;

import by.argyment.gymapp.data.entity.Profile;
import by.argyment.gymapp.data.net.RestService;
import by.argyment.gymapp.domain.entity.UserProfile;
import by.argyment.gymapp.domain.interactions.base.UseCase;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * @author Olga Rudzko
 */

public class UpdateProfileUseCase extends UseCase<UserProfile, UserProfile> {
    RestService rest;

    @Inject
    public UpdateProfileUseCase(RestService rest) {
        this.rest = rest;
    }

    @Override
    protected Observable<UserProfile> buildUseCase(UserProfile param) {
        Profile updated=new Profile();
        updated.setEmail(param.getEmail());
        updated.setUsername(param.getUsername());
        updated.setPassword(param.getPassword());
        updated.setTrainer(param.isTrainer());
        updated.setAdmin(param.isAdmin());
        updated.setUserpic(param.getUserpic());
        updated.setSlon(param.getSlon());
        updated.setStatus(param.getStatus());
        updated.setObjectId(param.getObjectId());
        updated.setTimeCheckin(param.getTimeCheckin());
        updated.setSlon(param.getSlon());
        return rest.updateProfile(updated, param.getObjectId()).map(new Function<Profile, UserProfile>() {
            @Override
            public UserProfile apply(@NonNull Profile profile) throws Exception {
                UserProfile user = new UserProfile();
                user.setObjectId(profile.getObjectId());
                user.setEmail(profile.getEmail());
                user.setUserpic(profile.getUserpic());
                user.setStatus(profile.getStatus());
                user.setSlon(profile.getSlon());
                user.setAdmin(profile.isAdmin());
                user.setPassword(profile.getPassword());
                user.setUsername(profile.getUsername());
                user.setTimeCheckin(profile.getTimeCheckin());
                user.setTrainer(profile.isTrainer());
                return user;
            }
        });
    }
}
