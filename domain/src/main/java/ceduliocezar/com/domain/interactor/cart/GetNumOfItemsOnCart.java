package ceduliocezar.com.domain.interactor.cart;

import javax.inject.Inject;

import ceduliocezar.com.domain.interactor.UseCase;
import ceduliocezar.com.domain.logging.Logger;
import ceduliocezar.com.domain.repository.CartRepository;
import ceduliocezar.com.domain.threading.PostExecutionThread;
import ceduliocezar.com.domain.threading.ThreadExecutor;
import io.reactivex.Observable;

/**
 * Use case that retrieves the num of items in cart.
 * Created by cedulio.silva on 4/15/2018.
 */

public class GetNumOfItemsOnCart extends UseCase<Integer, Void> {

    private CartRepository cartRepository;

    @Inject
    protected GetNumOfItemsOnCart(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, Logger logger, CartRepository cartRepository) {
        super(threadExecutor, postExecutionThread, logger);
        this.cartRepository = cartRepository;
    }

    @Override
    protected Observable<Integer> buildUseCaseObservable(Void aVoid) {
        return cartRepository
                .getNumOfItemsOnCart()
                .toObservable();
    }
}
