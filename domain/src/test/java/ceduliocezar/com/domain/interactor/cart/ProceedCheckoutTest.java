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
import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.observers.TestObserver;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Test suite for {@link ProceedCheckout}
 * Created by cedulio.silva on 4/17/2018.
 */
public class ProceedCheckoutTest {
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @InjectMocks
    private ProceedCheckout proceedCheckout;

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

        when(repository.checkout()).thenReturn(Completable.complete());

        TestObserver<Void> observer = proceedCheckout.buildUseCaseObservable(null).test();

        observer.awaitTerminalEvent();

        assertEquals(1, observer.assertNoErrors().completions());
    }
}