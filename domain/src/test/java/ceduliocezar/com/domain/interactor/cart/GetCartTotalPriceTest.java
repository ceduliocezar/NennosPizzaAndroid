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

import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test suite for {@link GetCartTotalPrice}
 * Created by cedulio.silva on 4/16/2018.
 */
public class GetCartTotalPriceTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @InjectMocks
    private GetCartTotalPrice getCartTotalPrice;

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

        when(repository.getCartTotalPrice()).thenReturn(Single.just(20.0d));

        TestObserver<Double> observer = getCartTotalPrice.buildUseCaseObservable(null).test();

        observer.awaitTerminalEvent();

        observer.assertNoErrors().assertValue(new Predicate<Double>() {
            @Override
            public boolean test(Double aDouble) throws Exception {
                return aDouble == 20.0d;
            }
        });

        verify(repository, only()).getCartTotalPrice();
    }

}