package ceduliocezar.com.data.repository.pizza;

import java.util.List;

import ceduliocezar.com.data.entity.PizzaEntity;
import io.reactivex.Observable;


/**
 * Data source of {@link PizzaEntity}
 * Created by cedulio.silva on 4/12/2018.
 */

public interface PizzaDataSource {

    /**
     * Retrieves all of {@link PizzaEntity} available.
     *
     * @return List of {@link PizzaEntity}
     */
    Observable<List<PizzaEntity>> listPizza();

}
