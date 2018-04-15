package ceduliocezar.com.domain.interactor.cart;

import javax.inject.Inject;

import ceduliocezar.com.domain.interactor.UseCase;
import ceduliocezar.com.domain.logging.Logger;
import ceduliocezar.com.domain.repository.CartRepository;
import ceduliocezar.com.domain.threading.PostExecutionThread;
import ceduliocezar.com.domain.threading.ThreadExecutor;
import io.reactivex.Observable;

/**
 * Retrieves the total price of items in cart.
 * Created by cedulio.silva on 4/16/2018.
 */

public class GetCartTotalPrice extends UseCase<Double, Void> {

    private CartRepository cartRepository;

    @Inject
    protected GetCartTotalPrice(ThreadExecutor threadExecutor,
                                PostExecutionThread postExecutionThread,
                                Logger logger,
                                CartRepository cartRepository) {
        super(threadExecutor, postExecutionThread, logger);
        this.cartRepository = cartRepository;
    }

    @Override
    protected Observable<Double> buildUseCaseObservable(Void aVoid) {
        return cartRepository
                .getCartTotalPrice()
                .toObservable();
    }
}
