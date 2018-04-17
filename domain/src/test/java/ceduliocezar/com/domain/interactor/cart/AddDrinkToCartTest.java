package ceduliocezar.com.domain.interactor.cart;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import ceduliocezar.com.domain.Drink;
import ceduliocezar.com.domain.repository.CartRepository;
import ceduliocezar.com.domain.threading.PostExecutionThread;
import ceduliocezar.com.domain.threading.ThreadExecutor;
import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.observers.TestObserver;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AddDrinkToCartTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @InjectMocks
    private AddDrinkToCart addDrinkToCart;

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
    private Drink drink;


    @Test
    public void test_buildUseCaseObservable() {


        when(repository.addDrinkToCart(drink)).thenReturn(Completable.complete());

        TestObserver<Void> observer = addDrinkToCart.buildUseCaseObservable(drink).test();

        observer.awaitTerminalEvent();

        assertEquals(1, observer.assertNoErrors().completions());

        verify(repository, only()).addDrinkToCart(drink);
    }


}
