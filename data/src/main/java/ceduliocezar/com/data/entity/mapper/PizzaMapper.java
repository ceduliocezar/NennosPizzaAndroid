package ceduliocezar.com.data.entity.mapper;

import java.util.List;

import ceduliocezar.com.data.entity.PizzaEntity;
import ceduliocezar.com.domain.Pizza;

/**
 * Created by cedulio.silva on 4/12/2018.
 */

public interface PizzaMapper {

    List<Pizza> transform(List<PizzaEntity> pizzaEntities);
}
