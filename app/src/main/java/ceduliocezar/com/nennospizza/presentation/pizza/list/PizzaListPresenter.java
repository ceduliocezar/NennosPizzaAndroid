package ceduliocezar.com.nennospizza.presentation.pizza.list;

import android.support.annotation.Nullable;

import javax.inject.Inject;

import ceduliocezar.com.domain.logging.Logger;

/**
 * Created by cedulio.silva on 4/13/2018.
 */

public class PizzaListPresenter implements PizzaListContract.Presenter {

    private static final String TAG = "PizzaListPresenter";

    private Logger logger;

    @Nullable
    private PizzaListContract.View view;

    @Inject
    public PizzaListPresenter(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void setView(PizzaListContract.View view) {
        this.view = view;
        logger.debug(TAG, "setView: " + view.toString());
    }

    @Override
    public void init() {
        logger.debug(TAG, "init: ");
    }

    @Override
    public void viewDestroyed() {
        view = null;
        logger.debug(TAG, "viewDestroyed: ");
        // TODO: 4/13/2018
    }

    @Override
    public void userSelectedPizza(PizzaModel pizzaModel) {
        logger.debug(TAG, "userSelectedPizza: ");
        // TODO: 14/04/2018
    }

    @Override
    public void userSelectedAddPizzaToCart(PizzaModel pizzaModel) {
        logger.debug(TAG, "userSelectedAddPizzaToCart: ");
        // TODO: 14/04/2018
    }
}
