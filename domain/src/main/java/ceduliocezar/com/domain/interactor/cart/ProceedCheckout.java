package ceduliocezar.com.domain.interactor.cart;

import javax.inject.Inject;

import ceduliocezar.com.domain.interactor.UseCase;
import ceduliocezar.com.domain.logging.Logger;
import ceduliocezar.com.domain.threading.PostExecutionThread;
import ceduliocezar.com.domain.threading.ThreadExecutor;
import io.reactivex.Observable;

public class ProceedCheckout extends UseCase<Void, Void> {
    @Inject
    protected ProceedCheckout(ThreadExecutor threadExecutor,
                              PostExecutionThread postExecutionThread,
                              Logger logger) {
        super(threadExecutor, postExecutionThread, logger);
    }

    @Override
    protected Observable<Void> buildUseCaseObservable(Void aVoid) {
        return null;
    }
}
