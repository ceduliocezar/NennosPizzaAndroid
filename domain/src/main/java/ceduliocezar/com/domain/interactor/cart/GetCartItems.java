package ceduliocezar.com.domain.interactor.cart;

import java.util.List;

import javax.inject.Inject;

import ceduliocezar.com.domain.CartItem;
import ceduliocezar.com.domain.interactor.UseCase;
import ceduliocezar.com.domain.logging.Logger;
import ceduliocezar.com.domain.threading.PostExecutionThread;
import ceduliocezar.com.domain.threading.ThreadExecutor;
import io.reactivex.Observable;

public class GetCartItems extends UseCase<List<CartItem>, Void> {


    @Inject
    protected GetCartItems(ThreadExecutor threadExecutor,
                           PostExecutionThread postExecutionThread,
                           Logger logger) {
        super(threadExecutor, postExecutionThread, logger);
    }

    @Override
    protected Observable<List<CartItem>> buildUseCaseObservable(Void aVoid) {
        return null;
    }
}
