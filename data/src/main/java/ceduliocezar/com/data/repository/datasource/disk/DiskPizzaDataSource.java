package ceduliocezar.com.data.repository.datasource.disk;

import java.util.List;

import javax.inject.Inject;

import ceduliocezar.com.data.entity.PizzaEntity;
import ceduliocezar.com.data.repository.datasource.PizzaDataSource;
import io.reactivex.Observable;

public class DiskPizzaDataSource implements PizzaDataSource {

    @Inject
    public DiskPizzaDataSource() {
        // TODO: 14/04/2018
    }

    @Override
    public Observable<List<PizzaEntity>> pizzaList() {
        throw new RuntimeException("Not implemented");
    }
}
