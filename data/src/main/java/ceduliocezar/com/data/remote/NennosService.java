package ceduliocezar.com.data.remote;

import java.util.List;

import ceduliocezar.com.data.entity.IngredientEntity;
import ceduliocezar.com.data.repository.pizza.datasource.cloud.PizzasTO;
import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by cedulio.silva on 4/12/2018.
 */

public interface NennosService {

    @GET("dokm7/")
    Observable<PizzasTO> listPizzas();

    @GET("ozt3z/")
    Observable<List<IngredientEntity>> listIngredient();

}
