package by.argyment.gymapp.domain.interactions;

import by.argyment.gymapp.data.entity.Profile;
import by.argyment.gymapp.data.net.RestService;
import by.argyment.gymapp.domain.entity.UserProfile;
import by.argyment.gymapp.domain.interactions.base.UseCase;
import io.reactivex.Observable;

/**
 * @author Olga Rudzko
 */

public class AddUserUseCase extends UseCase<UserProfile, Void> {

    @Override
    protected Observable<Void> buildUseCase(UserProfile param) {
        Profile newUser = new Profile();
        newUser.setAdmin(param.isAdmin());
        newUser.setEmail(param.getEmail());
        newUser.setPassword(param.getPassword());
        newUser.setSlon(param.getSlon());
        newUser.setStars(param.getStars());
        newUser.setStatus(param.getStatus());
        newUser.setTrainer(param.isTrainer());
        newUser.setUsername(param.getUsername());
        newUser.setUserpic(param.getUserpic());
        return RestService.getInstance().addProfile(newUser);
    }
}
