package ceduliocezar.com.nennospizza.navigation;

import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;

import ceduliocezar.com.domain.logging.Logger;
import ceduliocezar.com.nennospizza.presentation.cart.CartActivity;
import ceduliocezar.com.nennospizza.presentation.pizza.create.CreateCustomPizzaActivity;
import ceduliocezar.com.nennospizza.presentation.pizza.detail.PizzaDetailActivity;
import ceduliocezar.com.nennospizza.presentation.pizza.list.PizzaModel;

public class AppNavigator implements Navigator {
    private static final String TAG = "AppNavigator";
    private static final String PIZZA_PARAM = "PIZZA";
    private Logger logger;

    @Inject
    public AppNavigator(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void navigateToCreateCustomPizza(Context context) {
        logger.debug(TAG, "navigateToCreateCustomPizza");
        context.startActivity(new Intent(context, CreateCustomPizzaActivity.class));
    }

    @Override
    public void navigateToCartScreen(Context context) {
        logger.debug(TAG, "navigateToCartScreen");
        context.startActivity(new Intent(context, CartActivity.class));
    }

    @Override
    public void navigateToPizzaDetailScreen(Context context, PizzaModel pizzaModel) {
        logger.debug(TAG, "navigateToPizzaDetailScreen");
        Intent intent = new Intent(context, PizzaDetailActivity.class);
        intent.putExtra(PIZZA_PARAM, pizzaModel);
        context.startActivity(intent);
    }
}
