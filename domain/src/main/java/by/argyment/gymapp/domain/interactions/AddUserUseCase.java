package by.argyment.gymapp.domain.interactions;

import javax.inject.Inject;

import by.argyment.gymapp.data.entity.Profile;
import by.argyment.gymapp.data.net.RestService;
import by.argyment.gymapp.domain.entity.UserProfile;
import by.argyment.gymapp.domain.extra.Strings;
import by.argyment.gymapp.domain.interactions.base.UseCase;
import io.reactivex.Observable;

/**
 * @author Olga Rudzko
 */

public class AddUserUseCase extends UseCase<UserProfile, Void> {
    RestService rest;

    @Inject
    public AddUserUseCase(RestService rest) {
        this.rest = rest;
    }

    @Override
    protected Observable<Void> buildUseCase(UserProfile param) {
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
        return rest.addProfile(newUser);
    }
}
