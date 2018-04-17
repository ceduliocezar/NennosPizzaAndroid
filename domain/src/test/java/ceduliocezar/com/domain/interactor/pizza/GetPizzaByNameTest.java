package ceduliocezar.com.domain.interactor.pizza;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import ceduliocezar.com.domain.Pizza;
import ceduliocezar.com.domain.logging.Logger;
import ceduliocezar.com.domain.repository.PizzaRepository;
import ceduliocezar.com.domain.threading.PostExecutionThread;
import ceduliocezar.com.domain.threading.ThreadExecutor;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.TestObserver;

import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Testsuite for {@link GetPizzaByName}
 * Created by cedulio.silva on 4/17/2018.
 */
public class GetPizzaByNameTest {
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @InjectMocks
    private GetPizzaByName getPizzaByName;

    @Mock
    private PizzaRepository pizzaRepository;

    @Mock
    @SuppressWarnings("unused")
    private ThreadExecutor threadExecutor;

    @Mock
    @SuppressWarnings("unused")
    private PostExecutionThread postExecutionThread;

    @Mock
    @SuppressWarnings("unused")
    private Scheduler scheduler;

    @Mock
    @SuppressWarnings("unused")
    private Logger logger;

    @Mock
    private Pizza pizzaMock;


    @Test
    public void test_buildUseCaseObservable() {

        when(pizzaRepository.getPizzaByName("Pizza")).thenReturn(Observable.just(pizzaMock));

        TestObserver<Pizza> observer = getPizzaByName.buildUseCaseObservable("Pizza").test();

        observer.awaitTerminalEvent();

        observer.assertNoErrors().assertValue(new Predicate<Pizza>() {
            @Override
            public boolean test(Pizza pizza) {
                return pizza == pizzaMock;
            }
        });
    }


}