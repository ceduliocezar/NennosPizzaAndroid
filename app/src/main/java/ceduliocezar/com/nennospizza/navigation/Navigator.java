package ceduliocezar.com.nennospizza.navigation;

import android.content.Context;

import ceduliocezar.com.nennospizza.presentation.pizza.list.PizzaModel;

public interface Navigator {

    void navigateToCreateCustomPizza(Context context);

    void navigateToCartScreen(Context context);

    void navigateToPizzaDetailScreen(Context context, PizzaModel pizzaModel);

    void navigateToAfterCheckoutScreen(Context context);
}
