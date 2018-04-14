package ceduliocezar.com.domain.interactor.pizza;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import ceduliocezar.com.domain.logging.Logger;
import ceduliocezar.com.domain.repository.PizzaRepository;
import ceduliocezar.com.domain.threading.PostExecutionThread;
import ceduliocezar.com.domain.threading.ThreadExecutor;
import io.reactivex.Scheduler;

import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

/**
 * Test suite for {@link GetPizzaMenu}
 * Created by cedulio.silva on 4/12/2018.
 */

public class GetPizzaMenuTest {
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @InjectMocks
    private GetPizzaMenu getPizzaMenu;

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


    @Test
    public void test_buildUseCaseObservable() {

        getPizzaMenu.buildUseCaseObservable(null);

        verify(pizzaRepository, only()).list();
    }
}