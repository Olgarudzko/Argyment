package by.argyment.gymapp.dagger_injection;

import by.argyment.gymapp.domain.interactions.AddBitmapUseCase;
import by.argyment.gymapp.domain.interactions.AddImageUseCase;
import by.argyment.gymapp.domain.interactions.AddUserUseCase;
import by.argyment.gymapp.domain.interactions.DeleteImageUseCase;
import by.argyment.gymapp.domain.interactions.GetImageListUseCase;
import by.argyment.gymapp.domain.interactions.GetProfileListUseCase;
import by.argyment.gymapp.domain.interactions.GetProfileUseCase;
import by.argyment.gymapp.domain.interactions.UpdateProfileUseCase;
import dagger.Module;
import dagger.Provides;

/**
 * @author Olga Rudzko
 */
@Module
public class AppModule {

    @Provides
    public GetProfileListUseCase getProfileList(){
        return new  GetProfileListUseCase();
    }

    @Provides
    public GetProfileUseCase getProfile(){ return new GetProfileUseCase(); }

    @Provides
    public AddUserUseCase addUse(){
        return new AddUserUseCase();
    }

    @Provides
    public GetImageListUseCase getImages(){
        return new GetImageListUseCase();
    }

    @Provides
    public UpdateProfileUseCase updateUser(){
        return new UpdateProfileUseCase();
    }

    @Provides
    public AddImageUseCase addImg(){
        return new AddImageUseCase();
    }

    @Provides
    public AddBitmapUseCase addBitmap(){
        return new AddBitmapUseCase();
    }

    @Provides
    public DeleteImageUseCase removeImg(){
        return new DeleteImageUseCase();
    }
}
