package ceduliocezar.com.nennospizza.presentation.pizza;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ceduliocezar.com.domain.Ingredient;
import ceduliocezar.com.domain.Pizza;
import ceduliocezar.com.nennospizza.presentation.pizza.list.PizzaModel;

public class AppPizzaPresentationMapper implements PizzaPresentationMapper {

    @Inject
    public AppPizzaPresentationMapper() {
        // DI mandatory constructor
    }

    @Override
    public List<PizzaModel> transform(List<Pizza> pizzas) {

        List<PizzaModel> pizzaModels = new ArrayList<>();

        if (pizzas != null) {

            for (Pizza pizza : pizzas) {

                double totalPrice = pizza.getBasePrice();
                List<Ingredient> ingredients = pizza.getIngredients();
                List<String> ingredientsString = new ArrayList<>();

                if (ingredients != null) {
                    for (Ingredient ingredient : ingredients) {
                        totalPrice += ingredient.getPrice();
                        ingredientsString.add(ingredient.getName());
                    }
                }

                PizzaModel pizzaModel = new PizzaModel(pizza.getName(),
                        ingredientsString,
                        totalPrice,
                        pizza.getImageUrl());

                pizzaModels.add(pizzaModel);

            }
        }
        return pizzaModels;
    }
}
