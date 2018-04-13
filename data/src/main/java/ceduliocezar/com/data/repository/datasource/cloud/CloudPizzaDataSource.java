package ceduliocezar.com.data.repository.datasource.cloud;

import java.util.List;

import javax.inject.Inject;

import ceduliocezar.com.data.entity.PizzaEntity;
import ceduliocezar.com.data.remote.NennosService;
import ceduliocezar.com.data.repository.datasource.PizzaDataSource;
import io.reactivex.Observable;

/**
 * Created by cedulio.silva on 4/12/2018.
 */

public class CloudPizzaDataSource implements PizzaDataSource {

    private NennosService nennosService;

    @Inject
    public CloudPizzaDataSource(NennosService nennosService) {
        this.nennosService = nennosService;
    }

    @Override
    public Observable<List<PizzaEntity>> pizzaList() {
        return nennosService.listPizzas();
    }
}
