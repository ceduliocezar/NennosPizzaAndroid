package ceduliocezar.com.domain.interactor.cart;

import javax.inject.Inject;

import ceduliocezar.com.domain.CartItem;
import ceduliocezar.com.domain.interactor.UseCase;
import ceduliocezar.com.domain.logging.Logger;
import ceduliocezar.com.domain.threading.PostExecutionThread;
import ceduliocezar.com.domain.threading.ThreadExecutor;
import io.reactivex.Observable;

public class RemoveItemFromCart extends UseCase<Void, CartItem> {

    private static final String TAG = "AddPizzaToCart";


    @Inject
    public RemoveItemFromCart(ThreadExecutor threadExecutor,
                              PostExecutionThread postExecutionThread,
                              Logger logger) {
        super(threadExecutor, postExecutionThread, logger);
    }

    @Override
    protected Observable<Void> buildUseCaseObservable(CartItem cartItem) {
        getLogger().debug(TAG, "buildUseCaseObservable");
        return Observable.just(null); // TODO: 14/04/2018 create a compleatable
    }
}