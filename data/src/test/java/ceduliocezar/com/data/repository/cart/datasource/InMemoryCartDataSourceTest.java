package ceduliocezar.com.data.repository.cart.datasource;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Arrays;
import java.util.List;

import ceduliocezar.com.data.entity.CartItemEntity;
import ceduliocezar.com.data.entity.DrinkEntity;
import ceduliocezar.com.data.entity.PizzaEntity;
import ceduliocezar.com.data.remote.CheckoutService;
import ceduliocezar.com.data.repository.cart.CartTO;
import ceduliocezar.com.domain.logging.Logger;
import io.reactivex.Completable;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.TestObserver;

import static junit.framework.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

    @Mock
    private CheckoutService checkoutService;


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

    @Test
    public void test_remove() throws Exception {

        inMemoryCartItemDataSource.add(new CartItemEntity("id", "title", 10.0d)).test().awaitTerminalEvent();
        inMemoryCartItemDataSource.add(new CartItemEntity("id2", "title2", 10.0d)).test().awaitTerminalEvent();

        when(cartItemEntity.getId()).thenReturn("id");
        TestObserver<Void> observer = inMemoryCartItemDataSource.remove(cartItemEntity).test();

        observer.awaitTerminalEvent();

        assertEquals(1L, observer.assertNoErrors().completions());

        assertEquals(1, (int) inMemoryCartItemDataSource.getNumOfItemsOnCart().blockingGet());
    }

    @Test
    public void test_getCartItemById() throws Exception {
        inMemoryCartItemDataSource.add(new CartItemEntity("id", "title", 1.0d)).test().awaitTerminalEvent();
        inMemoryCartItemDataSource.add(new CartItemEntity("id1", "title1", 2.0d)).test().awaitTerminalEvent();
        inMemoryCartItemDataSource.add(new CartItemEntity("id2", "title2", 3.0d)).test().awaitTerminalEvent();

        TestObserver<CartItemEntity> observer = inMemoryCartItemDataSource.getCartItemById("id").test();
        observer.awaitTerminalEvent();

        observer.assertNoErrors()
                .assertValue(new Predicate<CartItemEntity>() {
                    @Override
                    public boolean test(CartItemEntity cartItemEntity) throws Exception {

                        if (!cartItemEntity.getId().equals("id")
                                || !cartItemEntity.getTitle().equals("title")
                                || cartItemEntity.getPrice() != 1.0d) {
                            return false;
                        }
                        return true;
                    }
                });
    }

    @Test
    public void test_checkout() throws Exception {


        when(checkoutService.checkout(any(CartTO.class))).thenReturn(Completable.complete());

        CartItemEntity cartItemEntityPizza = new CartItemEntity("id", "title", 1.0d);
        cartItemEntityPizza.setPizzaEntity(new PizzaEntity("id", Arrays.asList(1L, 2L), "url"));
        inMemoryCartItemDataSource.add(cartItemEntityPizza).test().awaitTerminalEvent();
        CartItemEntity cartItemEntityDrink = new CartItemEntity("id1", "title1", 20d);
        cartItemEntityDrink.setDrinkEntity(new DrinkEntity(1, "name", 10.0d));
        inMemoryCartItemDataSource.add(cartItemEntityDrink).test().awaitTerminalEvent();
        inMemoryCartItemDataSource.add(new CartItemEntity("id2", "title2", 3.0d)).test().awaitTerminalEvent();

        TestObserver<Void> observer = inMemoryCartItemDataSource.checkout().test();


        observer.awaitTerminalEvent();

        verify(checkoutService).checkout(any(CartTO.class));

        assertEquals(1L, observer.assertNoErrors().completions());

    }
}