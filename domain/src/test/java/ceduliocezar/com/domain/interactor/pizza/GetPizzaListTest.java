package ceduliocezar.com.domain.interactor.pizza;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import ceduliocezar.com.domain.logging.AppLogger;
import ceduliocezar.com.domain.repository.PizzaRepository;
import ceduliocezar.com.domain.threading.PostExecutionThread;
import ceduliocezar.com.domain.threading.ThreadExecutor;
import io.reactivex.Scheduler;

import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

/**
 * Test suite for {@link GetPizzaList}
 * Created by cedulio.silva on 4/12/2018.
 */

public class GetPizzaListTest {
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @InjectMocks
    private GetPizzaList getPizzaList;

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
    private AppLogger appLogger;


    @Test
    public void test_buildUseCaseObservable() {

        getPizzaList.buildUseCaseObservable(null);

        verify(pizzaRepository, only()).list();
    }
}