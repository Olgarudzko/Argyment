package by.argyment.gymapp.domain.interactions;

import java.util.ArrayList;
import java.util.List;

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
 *
 * @see Slon
 * the business logic of the application permits trainer to create only 3 Slon objects which
 * still have no winners
 */

public class GetFreeElephantsUseCase extends UseCase<Void, List<Slon>> {

    RestService rest;

    @Inject
    public GetFreeElephantsUseCase(RestService rest) {
        this.rest = rest;
    }

    @Override
    protected Observable<List<Slon>> buildUseCase(Void param) {
        return rest.getFreeElephants().map(new Function<List<SlonData>, List<Slon>>() {
            @Override
            public List<Slon> apply(@NonNull List<SlonData> slonDatas) throws Exception {
                List<Slon> list=new ArrayList<>();
                for (SlonData data: slonDatas) {
                    Slon slon=new Slon();
                    slon.setObjectId(data.getObjectId());
                    slon.setWinner(data.getWinner());
                    slon.setSlon(data.getSlon());
                    slon.setTrainer(data.getTrainer());
                    list.add(slon);
                }
                return list;
            }
        });
    }
}
