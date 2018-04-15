package ceduliocezar.com.domain.interactor.cart;

import javax.inject.Inject;

import ceduliocezar.com.domain.CartItem;
import ceduliocezar.com.domain.interactor.UseCase;
import ceduliocezar.com.domain.logging.Logger;
import ceduliocezar.com.domain.threading.PostExecutionThread;
import ceduliocezar.com.domain.threading.ThreadExecutor;
import io.reactivex.Observable;

public class GetCartItemById extends UseCase<CartItem, String> {

    @Inject
    protected GetCartItemById(ThreadExecutor threadExecutor,
                              PostExecutionThread postExecutionThread,
                              Logger logger) {
        super(threadExecutor, postExecutionThread, logger);
    }

    @Override
    protected Observable<CartItem> buildUseCaseObservable(String s) {
        return null;
    }
}
