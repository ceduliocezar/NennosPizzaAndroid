package ceduliocezar.com.data.repository.cart.datasource;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.List;

import ceduliocezar.com.data.entity.CartItemEntity;
import ceduliocezar.com.domain.logging.Logger;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.TestObserver;

/**
 * Test suite for {@link InMemoryCartDataSource}
 */
public class InMemoryCartDataSourceTest {


    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @InjectMocks
    private InMemoryCartDataSource inMemoryCartItemDataSource;

    @Mock
    @SuppressWarnings("unused")
    private Logger logger;

    @Mock
    private CartItemEntity cartItemEntity;


    @Test
    public void test_list() {
        TestObserver<List<CartItemEntity>> observer = inMemoryCartItemDataSource.list().test();

        observer.awaitTerminalEvent();

        observer.assertNoErrors().assertValue(new Predicate<List<CartItemEntity>>() {
            @Override
            public boolean test(List<CartItemEntity> cartItemEntities) throws Exception {
                if (!cartItemEntities.isEmpty()) {
                    return false;
                }

                return true;
            }
        });
    }

    @Test
    public void test_put() {
        inMemoryCartItemDataSource.add(cartItemEntity).test().awaitTerminalEvent();


        TestObserver<List<CartItemEntity>> observer = inMemoryCartItemDataSource.list().test();

        observer.awaitTerminalEvent();

        observer.assertNoErrors().assertValue(new Predicate<List<CartItemEntity>>() {
            @Override
            public boolean test(List<CartItemEntity> cartItemEntities) throws Exception {
                if (cartItemEntities.isEmpty()) {
                    return false;
                }

                return true;
            }
        });
    }
}