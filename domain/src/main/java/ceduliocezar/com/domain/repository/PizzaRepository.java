package ceduliocezar.com.domain.repository;

import java.util.List;

import ceduliocezar.com.domain.Pizza;
import io.reactivex.Observable;

/**
 * Created by cedulio.silva on 4/12/2018.
 */

public interface PizzaRepository {

    Observable<List<Pizza>> list();
}
