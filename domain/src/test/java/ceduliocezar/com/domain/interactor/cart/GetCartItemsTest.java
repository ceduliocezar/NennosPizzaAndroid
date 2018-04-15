package ceduliocezar.com.domain.interactor.cart;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Arrays;
import java.util.List;

import ceduliocezar.com.domain.CartItem;
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
 * Test suite for {@link GetCartItems}
 */
public class GetCartItemsTest {
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @InjectMocks
    private GetCartItems getCartItems;

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
    private CartItem cartItem;


    @Test
    public void test_buildUseCaseObservable() {

        when(repository.list()).thenReturn(Single.just(Arrays.asList(cartItem, cartItem)));

        TestObserver<List<CartItem>> observer = getCartItems.buildUseCaseObservable(null).test();

        observer.awaitTerminalEvent();

        observer.assertNoErrors().assertValue(new Predicate<List<CartItem>>() {
            @Override
            public boolean test(List<CartItem> cartItems) throws Exception {
                return cartItems.size() == 2;
            }
        });

        verify(repository, only()).list();
    }

}