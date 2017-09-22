package by.argyment.gymapp.domain.interactions;

import javax.inject.Inject;

import by.argyment.gymapp.data.entity.SlonData;
import by.argyment.gymapp.data.net.RestService;
import by.argyment.gymapp.domain.entity.Slon;
import by.argyment.gymapp.domain.interactions.base.UseCase;
import io.reactivex.Observable;

/**
 * @author Olga Rudzko
 */

public class AddElephantUseCase extends UseCase<Slon, Void>{

    RestService rest;

    @Inject
    public AddElephantUseCase(RestService rest) {
        this.rest = rest;
    }

    @Override
    protected Observable<Void> buildUseCase(Slon param) {
        SlonData data=new SlonData();
        data.setSlon(param.getSlon());
        data.setTrainer(param.getTrainer());
        data.setWinner("no");
        return rest.addSlon(data);
    }
}
