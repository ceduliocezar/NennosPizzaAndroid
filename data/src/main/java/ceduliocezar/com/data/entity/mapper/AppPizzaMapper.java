package ceduliocezar.com.data.entity.mapper;

import java.util.List;

import javax.inject.Inject;

import ceduliocezar.com.data.entity.PizzaEntity;
import ceduliocezar.com.domain.Pizza;

public class AppPizzaMapper implements PizzaMapper {

    @Inject
    public AppPizzaMapper() {
        // TODO: 14/04/2018
    }

    @Override
    public List<Pizza> transform(List<PizzaEntity> pizzaEntities) {
        return null; // TODO: 14/04/2018
    }
}
