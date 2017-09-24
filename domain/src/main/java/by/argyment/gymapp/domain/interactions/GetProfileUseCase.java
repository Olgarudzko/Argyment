package by.argyment.gymapp.domain.interactions;

import java.util.List;

import javax.inject.Inject;

import by.argyment.gymapp.data.entity.Profile;
import by.argyment.gymapp.data.net.RestService;
import by.argyment.gymapp.domain.entity.UserProfile;
import by.argyment.gymapp.domain.interactions.base.UseCase;
import by.argyment.gymapp.domain.extra.Strings;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * @author Olga Rudzko
 */

public class GetProfileUseCase extends UseCase<String, UserProfile> {
    RestService rest;

    @Inject
    public GetProfileUseCase(RestService rest) {
        this.rest = rest;
    }

    @Override
    protected Observable<UserProfile> buildUseCase(String email) {
        String ready = Strings.EMAILIS.concat(Strings.CH).concat(email).concat(Strings.CH);

        return rest.getProfile(ready)
                .map(new Function<List<Profile>, UserProfile>() {
                    @Override
                    public UserProfile apply(@NonNull List<Profile> profiles) throws Exception {
                        UserProfile userProfile = new UserProfile();
                        Profile profile = profiles.get(0);
                        userProfile.setEmail(profile.getEmail());
                        userProfile.setSlon(profile.getSlon());
                        userProfile.setStatus(profile.getStatus());
                        userProfile.setAdmin(profile.isAdmin());
                        userProfile.setPassword(profile.getPassword());
                        userProfile.setTrainer(profile.isTrainer());
                        userProfile.setUsername(profile.getUsername());
                        userProfile.setUserpic(profile.getUserpic());
                        userProfile.setObjectId(profile.getObjectId());
                        userProfile.setTimeCheckin(profile.getTimeCheckin());
                        return userProfile;
                    }
                });
    }
}
