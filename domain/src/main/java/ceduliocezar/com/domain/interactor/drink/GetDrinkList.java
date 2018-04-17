package ceduliocezar.com.domain.interactor.drink;

import java.util.List;

import javax.inject.Inject;

import ceduliocezar.com.domain.Drink;
import ceduliocezar.com.domain.interactor.UseCase;
import ceduliocezar.com.domain.logging.Logger;
import ceduliocezar.com.domain.repository.DrinkRepository;
import ceduliocezar.com.domain.threading.PostExecutionThread;
import ceduliocezar.com.domain.threading.ThreadExecutor;
import io.reactivex.Observable;

public class GetDrinkList extends UseCase<List<Drink>, Void> {

    private DrinkRepository drinkRepository;

    @Inject
    GetDrinkList(ThreadExecutor threadExecutor,
                 PostExecutionThread postExecutionThread,
                 Logger logger,
                 DrinkRepository drinkRepository) {
        super(threadExecutor, postExecutionThread, logger);
        this.drinkRepository = drinkRepository;
    }

    @Override
    protected Observable<List<Drink>> buildUseCaseObservable(Void aVoid) {
        return drinkRepository
                .list()
                .toObservable();
    }
}
