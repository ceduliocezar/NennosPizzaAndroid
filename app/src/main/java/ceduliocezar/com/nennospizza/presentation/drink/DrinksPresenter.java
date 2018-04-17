package ceduliocezar.com.nennospizza.presentation.drink;

import java.util.List;

import javax.inject.Inject;

import ceduliocezar.com.domain.Drink;
import ceduliocezar.com.domain.interactor.cart.AddDrinkToCart;
import ceduliocezar.com.domain.interactor.drink.GetDrinkList;
import ceduliocezar.com.domain.logging.Logger;
import io.reactivex.observers.DisposableObserver;

public class DrinksPresenter implements DrinksContract.Presenter {

    private static final String TAG = "DrinksPresenter";
    public DrinksContract.View view;

    private Logger logger;

    private GetDrinkList getDrinkList;

    private AddDrinkToCart addDrinkToCart;

    private DrinkPresentationMapper drinkPresentationMapper;

    @Inject
    public DrinksPresenter(Logger logger,
                           GetDrinkList getDrinkList,
                           AddDrinkToCart addDrinkToCart,
                           DrinkPresentationMapper drinkPresentationMapper) {
        this.logger = logger;
        this.getDrinkList = getDrinkList;
        this.addDrinkToCart = addDrinkToCart;
        this.drinkPresentationMapper = drinkPresentationMapper;
    }

    @Override
    public void viewDestroyed() {
        logger.debug(TAG, "viewDestroyed");
        this.view = null;
    }

    @Override
    public void setView(DrinksContract.View view) {
        logger.debug(TAG, "setView: " + view.toString());
        this.view = view;
    }

    @Override
    public void init() {
        logger.debug(TAG, "init");

        if (view == null) {
            logger.warn(TAG, "init called without setting a proper reference to view.");
        }
        loadDrinks();
    }

    void loadDrinks() {
        logger.debug(TAG, "loadDrinks");
        if (hasViewAttached()) {
            view.showLoadingDrinks();
        }
        getDrinkList.execute(new DisposableObserver<List<Drink>>() {
            @Override
            public void onNext(List<Drink> drinks) {
                logger.debug(TAG, "loadDrinks: onNext:" + drinks.toString());
                if (hasViewAttached()) {
                    view.showDrinks(drinkPresentationMapper.transform(drinks));
                }
            }

            @Override
            public void onError(Throwable e) {
                logger.error(TAG, e); // not defined in specs, just logging error by now
            }

            @Override
            public void onComplete() {
                logger.debug(TAG, "loadDrinks: onComplete");
                view.hideLoadingDrinks();

            }
        }, null);
    }

    @Override
    public void onClickAddDrink(DrinkModel drinkModel) {
        logger.debug(TAG, "onClickAddDrink");

        Drink drink = drinkPresentationMapper.transform(drinkModel);

        addDrinkToCart.execute(new DisposableObserver<Void>() {
            @Override
            public void onNext(Void aVoid) {
                logger.debug(TAG, "onClickAddDrink:");
            }

            @Override
            public void onError(Throwable e) {
                logger.error(TAG, e);
                if (hasViewAttached()) {
                    view.showErrorMessageOnAddDrink();
                }
            }

            @Override
            public void onComplete() {
                logger.debug(TAG, "onClickAddDrink: onComplete");
                if (hasViewAttached()) {
                    view.showNotificationDrinkAdded();
                }
            }
        }, drink);
    }

    private boolean hasViewAttached() {
        return view != null;
    }
}
