package ceduliocezar.com.domain.interactor.cart;

import javax.inject.Inject;

import ceduliocezar.com.domain.Pizza;
import ceduliocezar.com.domain.interactor.UseCase;
import ceduliocezar.com.domain.logging.Logger;
import ceduliocezar.com.domain.repository.CartRepository;
import ceduliocezar.com.domain.threading.PostExecutionThread;
import ceduliocezar.com.domain.threading.ThreadExecutor;
import io.reactivex.Observable;

public class AddPizzaToCart extends UseCase<Integer, Pizza> {

    private static final String TAG = "AddPizzaToCart";

    private CartRepository cartRepository;

    @Inject
    public AddPizzaToCart(ThreadExecutor threadExecutor,
                          PostExecutionThread postExecutionThread,
                          Logger logger, CartRepository cartRepository) {
        super(threadExecutor, postExecutionThread, logger);
        this.cartRepository = cartRepository;
    }

    @Override
    protected Observable<Integer> buildUseCaseObservable(Pizza pizza) {
        getLogger().debug(TAG, "buildUseCaseObservable");
        return cartRepository
                .addPizzaToCart(pizza)
                .toObservable();
    }
}