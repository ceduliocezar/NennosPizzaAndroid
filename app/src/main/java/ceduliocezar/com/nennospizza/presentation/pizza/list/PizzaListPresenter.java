package ceduliocezar.com.nennospizza.presentation.pizza.list;

import android.support.annotation.Nullable;

import java.util.List;

import javax.inject.Inject;

import ceduliocezar.com.domain.Pizza;
import ceduliocezar.com.domain.interactor.cart.AddPizzaToCart;
import ceduliocezar.com.domain.interactor.cart.GetNumOfItemsOnCart;
import ceduliocezar.com.domain.interactor.pizza.GetPizzaByName;
import ceduliocezar.com.domain.interactor.pizza.GetPizzaMenu;
import ceduliocezar.com.domain.logging.Logger;
import ceduliocezar.com.nennospizza.presentation.pizza.PizzaPresentationMapper;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by cedulio.silva on 4/13/2018.
 */

public class PizzaListPresenter implements PizzaListContract.Presenter {

    private static final String TAG = "PizzaListPresenter";

    private Logger logger;
    @Nullable
    protected PizzaListContract.View view;
    private AddPizzaToCart addPizzaToCart;
    private GetPizzaMenu getPizzaMenu;
    private PizzaPresentationMapper pizzaPresentationMapper;
    private GetPizzaByName getPizzaByName;
    private GetNumOfItemsOnCart getNumOfItemsOnCart;

    @Inject
    public PizzaListPresenter(Logger logger,
                              AddPizzaToCart addPizzaToCart,
                              GetPizzaMenu getPizzaMenu,
                              PizzaPresentationMapper pizzaPresentationMapper, GetPizzaByName getPizzaByName, GetNumOfItemsOnCart getNumOfItemsOnCart) {
        this.logger = logger;
        this.addPizzaToCart = addPizzaToCart;
        this.getPizzaMenu = getPizzaMenu;
        this.pizzaPresentationMapper = pizzaPresentationMapper;
        this.getPizzaByName = getPizzaByName;
        this.getNumOfItemsOnCart = getNumOfItemsOnCart;
    }

    @Override
    public void setView(PizzaListContract.View view) {
        this.view = view;
        logger.debug(TAG, "setView: " + view.toString());
    }

    @Override
    public void init() {
        logger.debug(TAG, "init: ");

        if (view == null) {
            logger.warn(TAG, "Init called without setting view. This can lead to problems during the execution.");
        }

        loadPizzaList();
        loadNumItemsOnCart();
    }

    void loadNumItemsOnCart() {
        logger.debug(TAG, "loadNumItemsOnCart");

        getNumOfItemsOnCart.execute(new DisposableObserver<Integer>() {
            @Override
            public void onNext(Integer integer) {
                logger.debug(TAG, "onNext");
                if (hasViewAttached()) {
                    showNumOfItemsInCart(integer);
                }
            }

            @Override
            public void onError(Throwable e) {
                logger.error(TAG, e);
            }

            @Override
            public void onComplete() {
                logger.debug(TAG, "onComplete");
            }
        }, null);
    }

    void loadPizzaList() {
        logger.info(TAG, "loadPizzaList: started loading pizzas");

        if (hasViewAttached()) {
            view.showPizzaLoader();
        }

        getPizzaMenu.execute(new DisposableObserver<List<Pizza>>() {
            @Override
            public void onNext(List<Pizza> pizzas) {
                logger.debug(TAG, "loadPizzaList: loaded pizzas:" + pizzas.toString());
                if (hasViewAttached()) {
                    view.showPizzas(pizzaPresentationMapper.transform(pizzas));
                } else {
                    logger.info(TAG, "loadPizzaList: no view available to deliver");
                }
            }

            @Override
            public void onError(Throwable e) {
                logger.error(TAG, e);
            }

            @Override
            public void onComplete() {
                logger.info(TAG, "onComplete: finished loading pizzas");
                view.hidePizzaLoader();
            }
        }, null);
    }

    @Override
    public void viewDestroyed() {
        logger.debug(TAG, "viewDestroyed: ");
        view = null;
    }

    @Override
    public void userSelectedPizza(PizzaModel pizzaModel) {
        logger.debug(TAG, "userSelectedPizza: ");
        if (hasViewAttached()) {
            view.showDetailPizzaScreen(pizzaModel);
        }
    }

    @Override
    public void userSelectedAddPizzaToCart(PizzaModel pizzaModel) {
        logger.debug(TAG, "userSelectedAddPizzaToCart: " + pizzaModel.toString());
        addPizzaToCart(pizzaModel);
    }

    @Override
    public void viewResumed() {
        logger.debug(TAG, "viewResumed");
        loadNumItemsOnCart();
    }

    void addPizzaToCart(PizzaModel pizzaModel) {
        logger.debug(TAG, "addPizzaToCart: ");

        getPizzaByName.execute(new DisposableObserver<Pizza>() {
            @Override
            public void onNext(Pizza pizza) {
                addPizzaToCart.execute(new DisposableObserver<Integer>() {
                    @Override
                    public void onNext(Integer numItemsOnCart) {
                        logger.debug(TAG, "addPizzaToCart: onNext: numItemsOnCart:" + numItemsOnCart);
                        if (hasViewAttached()) {
                            showNumOfItemsInCart(numItemsOnCart);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        logger.error(TAG, e);
                    }

                    @Override
                    public void onComplete() {
                        logger.info(TAG, "Finish adding pizza to the cart.");
                    }
                }, pizza);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        }, pizzaModel.getName());
    }

    private void showNumOfItemsInCart(Integer numItemsOnCart) {
        if (numItemsOnCart > 0) {
            view.showCartNotification(numItemsOnCart);
        } else {
            view.hideCartNotification();
        }
    }

    private boolean hasViewAttached() {
        return view != null;
    }
}
