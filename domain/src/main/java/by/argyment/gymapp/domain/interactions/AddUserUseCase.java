package by.argyment.gymapp.domain.interactions;

import javax.inject.Inject;

import by.argyment.gymapp.data.entity.Profile;
import by.argyment.gymapp.data.net.RestService;
import by.argyment.gymapp.domain.entity.UserProfile;
import by.argyment.gymapp.domain.extra.Strings;
import by.argyment.gymapp.domain.interactions.base.UseCase;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * @author Olga Rudzko
 */

public class AddUserUseCase extends UseCase<UserProfile, UserProfile> {
    RestService rest;

    @Inject
    public AddUserUseCase(RestService rest) {
        this.rest = rest;
    }

    @Override
    protected Observable<UserProfile> buildUseCase(UserProfile param) {
        Profile newUser = new Profile();
        newUser.setAdmin(false);
        newUser.setEmail(param.getEmail());
        newUser.setPassword(param.getPassword());
        newUser.setSlon(Strings.NO);
        newUser.setStatus(0);
        newUser.setTrainer(false);
        newUser.setUsername(param.getUsername());
        newUser.setUserpic(Strings.DEFAULT_IMG);
        newUser.setTimeCheckin(System.currentTimeMillis() - 81000000);
        return rest.addProfile(newUser).map(new Function<Profile, UserProfile>() {
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
