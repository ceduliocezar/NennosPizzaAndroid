package ceduliocezar.com.data.remote;

import java.util.List;

import ceduliocezar.com.data.entity.PizzaEntity;
import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by cedulio.silva on 4/12/2018.
 */

public interface NennosService {

    @GET("/dokm7")
    Observable<List<PizzaEntity>> listPizzas();

}
