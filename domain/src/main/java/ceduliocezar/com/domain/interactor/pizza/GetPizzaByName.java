package ceduliocezar.com.domain.interactor.pizza;

import javax.inject.Inject;

import ceduliocezar.com.domain.Pizza;
import ceduliocezar.com.domain.interactor.UseCase;
import ceduliocezar.com.domain.logging.Logger;
import ceduliocezar.com.domain.repository.PizzaRepository;
import ceduliocezar.com.domain.threading.PostExecutionThread;
import ceduliocezar.com.domain.threading.ThreadExecutor;
import io.reactivex.Observable;

public class GetPizzaByName extends UseCase<Pizza, String> {

    private static final String TAG = "GetPizzaMenu";
    private PizzaRepository pizzaRepository;

    @Inject
    public GetPizzaByName(ThreadExecutor threadExecutor,
                          PostExecutionThread postExecutionThread,
                          Logger logger,
                          PizzaRepository pizzaRepository) {
        super(threadExecutor, postExecutionThread, logger);
        this.pizzaRepository = pizzaRepository;
    }

    @Override
    protected Observable<Pizza> buildUseCaseObservable(String pizzaName) {
        getLogger().debug(TAG, "buildUseCaseObservable");
        return pizzaRepository.getPizzaByName(pizzaName);
    }
}
