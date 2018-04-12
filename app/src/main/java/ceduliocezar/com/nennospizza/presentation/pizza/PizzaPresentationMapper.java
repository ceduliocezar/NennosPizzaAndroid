package ceduliocezar.com.nennospizza.presentation.pizza;

import java.util.List;

import ceduliocezar.com.domain.Pizza;
import ceduliocezar.com.nennospizza.presentation.pizza.list.PizzaModel;

public interface PizzaPresentationMapper {

    List<PizzaModel> transform(List<Pizza> pizzas);
}
