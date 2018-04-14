package ceduliocezar.com.domain.interactor.pizza;

import java.util.List;

import javax.inject.Inject;

import ceduliocezar.com.domain.Pizza;
import ceduliocezar.com.domain.interactor.UseCase;
import ceduliocezar.com.domain.logging.Logger;
import ceduliocezar.com.domain.repository.PizzaRepository;
import ceduliocezar.com.domain.threading.PostExecutionThread;
import ceduliocezar.com.domain.threading.ThreadExecutor;
import io.reactivex.Observable;

/**
 * This is a use case implementation for retrieve a list of available pizzas.
 * Created by cedulio.silva on 4/12/2018.
 */

public class GetPizzaList extends UseCase<List<Pizza>, Void> {

    private static final String TAG = "GetPizzaList";
    private PizzaRepository pizzaRepository;

    @Inject
    public GetPizzaList(ThreadExecutor threadExecutor,
                        PostExecutionThread postExecutionThread,
                        Logger logger,
                        PizzaRepository pizzaRepository) {
        super(threadExecutor, postExecutionThread, logger);
        this.pizzaRepository = pizzaRepository;
    }

    @Override
    protected Observable<List<Pizza>> buildUseCaseObservable(Void aVoid) {
        getLogger().debug(TAG, "buildUseCaseObservable");
        return pizzaRepository.list();
    }
}
