package ceduliocezar.com.domain.interactor.cart;

import java.util.List;

import javax.inject.Inject;

import ceduliocezar.com.domain.CartItem;
import ceduliocezar.com.domain.interactor.UseCase;
import ceduliocezar.com.domain.logging.Logger;
import ceduliocezar.com.domain.repository.CartRepository;
import ceduliocezar.com.domain.threading.PostExecutionThread;
import ceduliocezar.com.domain.threading.ThreadExecutor;
import io.reactivex.Observable;

/**
 * Retrieves a list with all {@link CartItem}.
 */
public class GetCartItems extends UseCase<List<CartItem>, Void> {


    private CartRepository cartRepository;

    @Inject
    protected GetCartItems(ThreadExecutor threadExecutor,
                           PostExecutionThread postExecutionThread,
                           Logger logger, CartRepository cartRepository) {
        super(threadExecutor, postExecutionThread, logger);
        this.cartRepository = cartRepository;
    }

    @Override
    protected Observable<List<CartItem>> buildUseCaseObservable(Void aVoid) {
        return cartRepository
                .list()
                .toObservable(); // todo implement single use case.
    }
}
