package ceduliocezar.com.data.repository.pizza.datasource.cloud;

import java.util.List;

import javax.inject.Inject;

import ceduliocezar.com.data.entity.PizzaEntity;
import ceduliocezar.com.data.remote.MenuService;
import ceduliocezar.com.data.repository.pizza.PizzaDataSource;
import ceduliocezar.com.domain.logging.Logger;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Cloud implementation of {@link PizzaDataSource}.
 * Created by cedulio.silva on 4/12/2018.
 */

public class CloudPizzaDataSource implements PizzaDataSource {
    private static final String TAG = "CloudPizzaDataSource";

    private MenuService menuService;
    private Logger logger;

    @Inject
    public CloudPizzaDataSource(MenuService menuService, Logger logger) {
        this.menuService = menuService;
        this.logger = logger;
    }

    @Override
    public Observable<List<PizzaEntity>> listPizza() {
        logger.debug(TAG, "listPizza");
        return menuService.listPizzas()
                .map(new Function<PizzasTO, List<PizzaEntity>>() {
                    @Override
                    public List<PizzaEntity> apply(PizzasTO pizzasTO) {

                        double basePrice = pizzasTO.getBasePrice();
                        List<PizzaEntity> pizzas = pizzasTO.getPizzas();

                        for (PizzaEntity pizzaEntity : pizzas) {
                            pizzaEntity.setBasePrice(basePrice);
                        }

                        return pizzas;
                    }
                });
    }
}
