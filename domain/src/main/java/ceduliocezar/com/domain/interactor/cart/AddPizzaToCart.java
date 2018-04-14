package ceduliocezar.com.domain.interactor.cart;

import javax.inject.Inject;

import ceduliocezar.com.domain.interactor.UseCase;
import ceduliocezar.com.domain.logging.Logger;
import ceduliocezar.com.domain.threading.PostExecutionThread;
import ceduliocezar.com.domain.threading.ThreadExecutor;
import io.reactivex.Observable;

public class AddPizzaToCart extends UseCase<Integer, String> {

    private static final String TAG = "AddPizzaToCart";


    @Inject
    public AddPizzaToCart(ThreadExecutor threadExecutor,
                          PostExecutionThread postExecutionThread,
                          Logger logger) {
        super(threadExecutor, postExecutionThread, logger);
    }

    @Override
    protected Observable<Integer> buildUseCaseObservable(String pizzaId) {
        getLogger().debug(TAG, "buildUseCaseObservable");
        return Observable.just(0); // TODO: 14/04/2018
    }
}