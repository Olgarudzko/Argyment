package by.argyment.gymapp.domain.interactions;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import by.argyment.gymapp.data.entity.Profile;
import by.argyment.gymapp.data.net.RestService;
import by.argyment.gymapp.domain.entity.UserProfile;
import by.argyment.gymapp.domain.interactions.base.UseCase;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

public class GetProfileListUseCase extends UseCase<Void, List<UserProfile>> {
    RestService rest;

    @Inject
    public GetProfileListUseCase(RestService rest) {
        this.rest = rest;
    }

    @Override
    protected Observable<List<UserProfile>> buildUseCase(Void param) {
        return rest.getProfiles()
                .map(new Function<List<Profile>, List<UserProfile>>() {
                    @Override
                    public List<UserProfile> apply(@NonNull List<Profile> profiles) throws Exception {
                        List<UserProfile> list=new ArrayList<UserProfile>();
                        for (Profile profile: profiles) {
                            UserProfile userProfile=new UserProfile();
                            userProfile.setEmail(profile.getEmail());
                            userProfile.setSlon(profile.getSlon());
                            userProfile.setStars(profile.getStars());
                            userProfile.setStatus(profile.getStatus());
                            userProfile.setAdmin(profile.isAdmin());
                            userProfile.setPassword(profile.getPassword());
                            userProfile.setTrainer(profile.isTrainer());
                            userProfile.setUsername(profile.getUsername());
                            userProfile.setUserpic(profile.getUserpic());
                            userProfile.setObjectId(profile.getObjectId());
                            userProfile.setTimeCheckin(profile.getTimeCheckin());
                            userProfile.setTimeStar(profile.getTimeStar());
                            list.add(userProfile);
                        }
                        return list;
                    }
                });
    }
}
