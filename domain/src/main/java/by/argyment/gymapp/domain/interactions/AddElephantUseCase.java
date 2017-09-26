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

public class AddElephantUseCase extends UseCase<Slon, Slon>{

    RestService rest;

    @Inject
    public AddElephantUseCase(RestService rest) {
        this.rest = rest;
    }

    @Override
    protected Observable<Slon> buildUseCase(Slon param) {
        SlonData data=new SlonData();
        data.setSlon(param.getSlon());
        data.setTrainer(param.getTrainer());
        data.setWinner("no");
        return rest.addSlon(data).map(new Function<SlonData, Slon>() {
            @Override
            public Slon apply(@NonNull SlonData slonData) throws Exception {
                Slon slon=new Slon();
                slon.setSlon(slonData.getSlon());
                slon.setTrainer(slonData.getTrainer());
                slon.setWinner(slonData.getWinner());
                slon.setObjectId(slonData.getObjectId());
                return slon;
            }
        });
    }
}
