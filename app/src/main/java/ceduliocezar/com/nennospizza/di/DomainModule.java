package ceduliocezar.com.nennospizza.di;

import ceduliocezar.com.data.repository.cart.CartDataRepository;
import ceduliocezar.com.data.repository.drink.DrinkDataRepository;
import ceduliocezar.com.data.repository.ingredient.IngredientDataRepository;
import ceduliocezar.com.data.repository.pizza.PizzaDataRepository;
import ceduliocezar.com.domain.repository.CartRepository;
import ceduliocezar.com.domain.repository.DrinkRepository;
import ceduliocezar.com.domain.repository.IngredientRepository;
import ceduliocezar.com.domain.repository.PizzaRepository;
import dagger.Module;
import dagger.Provides;

/**
 * Module for domain classes.
 * Created by cedulio.silva on 4/12/2018.
 */
@Module
public class DomainModule {

    @Provides
    @SuppressWarnings("unused")
    PizzaRepository providesPizzaRepository(PizzaDataRepository pizzaDataRepository) {
        return pizzaDataRepository;
    }

    @Provides
    IngredientRepository providesIngredientRepository(IngredientDataRepository ingredientDataRepository) {
        return ingredientDataRepository;
    }

    @Provides
    CartRepository providesCartRepository(CartDataRepository cartDataRepository) {
        return cartDataRepository;
    }

    @Provides
    DrinkRepository providesDrinksRepository(DrinkDataRepository drinkDataRepository) {
        return drinkDataRepository;
    }
}
