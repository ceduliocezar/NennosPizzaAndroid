package ceduliocezar.com.nennospizza.presentation.pizza.list;

import android.support.annotation.Nullable;

import javax.inject.Inject;

import ceduliocezar.com.domain.logging.AppLogger;

/**
 * Created by cedulio.silva on 4/13/2018.
 */

public class PizzaListPresenter implements PizzaListContract.Presenter {

    private static final String TAG = "PizzaListPresenter";

    private AppLogger logger;

    @Nullable
    private PizzaListContract.View view;

    @Inject
    public PizzaListPresenter(AppLogger logger) {
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
        // TODO: 4/13/2018
    }
}
