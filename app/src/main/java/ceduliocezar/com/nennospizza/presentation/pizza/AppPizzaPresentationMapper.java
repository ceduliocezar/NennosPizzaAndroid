package ceduliocezar.com.nennospizza.presentation.pizza;

import java.util.List;

import javax.inject.Inject;

import ceduliocezar.com.domain.Pizza;
import ceduliocezar.com.nennospizza.presentation.pizza.list.PizzaModel;

public class AppPizzaPresentationMapper implements PizzaPresentationMapper {

    @Inject
    public AppPizzaPresentationMapper() {
        // TODO: 14/04/2018
    }

    @Override
    public List<PizzaModel> transform(List<Pizza> pizzas) {
        return null; // TODO: 14/04/2018
    }
}
