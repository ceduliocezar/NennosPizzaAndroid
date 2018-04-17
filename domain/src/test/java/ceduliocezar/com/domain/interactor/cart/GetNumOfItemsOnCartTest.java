package ceduliocezar.com.domain.interactor.cart;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import ceduliocezar.com.domain.logging.Logger;
import ceduliocezar.com.domain.repository.CartRepository;
import ceduliocezar.com.domain.threading.PostExecutionThread;
import ceduliocezar.com.domain.threading.ThreadExecutor;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.TestObserver;

import static org.mockito.Mockito.when;

/**
 * Test suite for {@link GetNumOfItemsOnCart}
 * Created by cedulio.silva on 4/15/2018.
 */
public class GetNumOfItemsOnCartTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @InjectMocks
    private GetNumOfItemsOnCart getNumOfItemsOnCart;

    @Mock
    private CartRepository repository;

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

        when(repository.getNumOfItemsOnCart()).thenReturn(Single.just(20));

        TestObserver<Integer> observer = getNumOfItemsOnCart.buildUseCaseObservable(null).test();

        observer.awaitTerminalEvent();

        observer.assertNoErrors().assertValue(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return 20 == integer;
            }
        });
    }
}