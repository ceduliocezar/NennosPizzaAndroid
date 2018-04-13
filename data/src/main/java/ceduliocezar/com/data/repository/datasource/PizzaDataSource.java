package ceduliocezar.com.data.repository.datasource;

import java.util.List;

import ceduliocezar.com.data.entity.PizzaEntity;
import io.reactivex.Observable;


/**
 * Created by cedulio.silva on 4/12/2018.
 */

public interface PizzaDataSource {

    Observable<List<PizzaEntity>> pizzaList();
}
