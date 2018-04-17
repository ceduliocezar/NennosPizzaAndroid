package ceduliocezar.com.domain.interactor.cart;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import ceduliocezar.com.domain.CartItem;
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
 * Test suite for {@link GetCartItemById}
 * Created by cedulio.silva on 4/17/2018.
 */
public class GetCartItemByIdTest {
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @InjectMocks
    private GetCartItemById getCartItemById;

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

    @Mock
    private CartItem mockCartItem;


    @Test
    public void test_buildUseCaseObservable() {

        when(repository.getCartItemById("id")).thenReturn(Single.just(mockCartItem));

        TestObserver<CartItem> observer = getCartItemById.buildUseCaseObservable("id").test();

        observer.awaitTerminalEvent();

        observer.assertNoErrors().assertValue(new Predicate<CartItem>() {
            @Override
            public boolean test(CartItem cartItem) throws Exception {
                return mockCartItem == cartItem;
            }
        });
    }

}