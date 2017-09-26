package by.argyment.gymapp.domain.interactions;

import javax.inject.Inject;

import by.argyment.gymapp.data.entity.SlonData;
import by.argyment.gymapp.data.net.RestService;
import by.argyment.gymapp.domain.entity.Slon;
import by.argyment.gymapp.domain.interactions.base.UseCase;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * @author Olga Rudzko
 */

public class SetSlonWinnerUseCase extends UseCase<Slon, Slon> {

    RestService rest;

    @Inject
    public SetSlonWinnerUseCase(RestService rest) {
        this.rest = rest;
    }

    @Override
    protected Observable<Slon> buildUseCase(Slon param) {
        SlonData slon=new SlonData();
        slon.setTrainer(param.getTrainer());
        slon.setWinner(param.getWinner());
        slon.setSlon(param.getSlon());
        slon.setObjectId(param.getObjectId());
        return rest.setWinner(slon, param.getObjectId()).map(new Function<SlonData, Slon>() {
            @Override
            public Slon apply(@NonNull SlonData slonData) throws Exception {
                Slon slon=new Slon();
                slon.setObjectId(slonData.getObjectId());
                slon.setSlon(slonData.getSlon());
                slon.setWinner(slonData.getWinner());
                slon.setTrainer(slonData.getTrainer());
                return slon;
            }
        });
    }
}
