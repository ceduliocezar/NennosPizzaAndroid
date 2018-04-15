package ceduliocezar.com.domain.interactor.cart;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import ceduliocezar.com.domain.repository.CartRepository;
import io.reactivex.Single;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.TestObserver;

import static org.mockito.Mockito.when;

/**
 * Created by cedulio.silva on 4/15/2018.
 */
public class GetNumOfItemsOnCartTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @InjectMocks
    private GetNumOfItemsOnCart getNumOfItemsOnCart;

    @Mock
    private CartRepository cartRepository;

    @Test
    public void test_buildUseCaseObservable() throws Exception {

        when(cartRepository.getNumOfItemsOnCart()).thenReturn(Single.just(10));
        TestObserver<Integer> observer = getNumOfItemsOnCart.buildUseCaseObservable(null).test();

        observer.assertNoErrors().assertValue(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Exception {

                if (integer != 10) {
                    return false;
                }
                return true;
            }
        });
    }
}