package by.argyment.gymapp.domain.interactions;


import android.util.Log;

import java.util.List;

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

public class GetProfileUseCase extends UseCase<String, UserProfile> {
    @Override
    protected Observable<UserProfile> buildUseCase(final String param) {

        return RestService.getInstance().getProfiles()
                .map(new Function<List<Profile>, UserProfile>() {
                    @Override
                    public UserProfile apply(@NonNull List<Profile> profiles) throws Exception {
                        UserProfile userProfile = new UserProfile();
                        for (Profile profile : profiles) {
                            if (param.equals(profile.getEmail())) {

//        return RestService.getInstance().getProfile(param)
//                .map(new Function<List<Profile>, UserProfile>() {
//                    @Override
//                    public UserProfile apply(@NonNull List<Profile> profiles) throws Exception {
//                        UserProfile userProfile = new UserProfile();
//                        Profile profile=profiles.get(0);
//                        Log.d(profile.getUsername(), profile.getEmail());

//        return RestService.getInstance().getProfile(param)
//                .map(new Function<Profile, UserProfile>() {
//                    @Override
//                    public UserProfile apply(@NonNull Profile profile) throws Exception {
//                        UserProfile userProfile=new UserProfile();
                        Log.d(profile.getUsername(), profile.getEmail());


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
                                break;
                            }
                        }

                        return userProfile;
                    }
                });
    }
}
