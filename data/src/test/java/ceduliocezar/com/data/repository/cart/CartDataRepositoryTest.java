package ceduliocezar.com.data.repository.cart;

import android.support.annotation.NonNull;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ceduliocezar.com.data.entity.CartItemEntity;
import ceduliocezar.com.data.entity.DrinkEntity;
import ceduliocezar.com.data.entity.PizzaEntity;
import ceduliocezar.com.data.repository.cart.datasource.CartDataSource;
import ceduliocezar.com.domain.CartItem;
import ceduliocezar.com.domain.CartItemType;
import ceduliocezar.com.domain.Pizza;
import ceduliocezar.com.domain.logging.Logger;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.TestObserver;

import static junit.framework.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test suite for {@link CartDataRepository}
 */
public class CartDataRepositoryTest {
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @InjectMocks
    private CartDataRepository repository;

    @Mock
    private CartDataSource dataSource;


    @Mock
    @SuppressWarnings("unused")
    private Logger logger;

    @Mock
    private CartItem cartItem;

    @Mock
    private CartItemEntity cartItemEntity;

    @Mock
    private Pizza pizza;

    @Test
    public void test_list() {

        List<CartItemEntity> cartItems = getCartItemEntities(10);

        when(dataSource.list()).thenReturn(Single.just(cartItems));

        TestObserver<List<CartItem>> observer = repository.list().test();
        observer.awaitTerminalEvent();


        observer.assertNoErrors().assertValue(new Predicate<List<CartItem>>() {
            @Override
            public boolean test(List<CartItem> cartItems) {

                CartItem cartItem = cartItems.get(0);
                if (cartItems.size() != 10
                        || cartItem.getCartItemType() != CartItemType.PIZZA
                        || !cartItem.getId().equals("id0")
                        || cartItem.getPrice() != 10.0d
                        || !cartItem.getTitle().equals("title0")) {

                    return false;
                }
                return true;
            }
        });
    }

    @Test
    public void test_listDrink() {

        List<CartItemEntity> cartItems = new ArrayList<>();

        CartItemEntity cartItemEntity = new CartItemEntity("id", "title", 10.0d);
        cartItemEntity.setDrinkEntity(new DrinkEntity(10L, "drink", 10.0d));
        cartItems.add(cartItemEntity);

        when(dataSource.list()).thenReturn(Single.just(cartItems));

        TestObserver<List<CartItem>> observer = repository.list().test();
        observer.awaitTerminalEvent();


        observer.assertNoErrors().assertValue(new Predicate<List<CartItem>>() {
            @Override
            public boolean test(List<CartItem> cartItems) {

                CartItem cartItem = cartItems.get(0);
                if (cartItems.size() != 1
                        || cartItem.getCartItemType() != CartItemType.DRINK
                        || !cartItem.getId().equals("id")
                        || cartItem.getPrice() != 10.0d
                        || !cartItem.getTitle().equals("title")) {

                    return false;
                }
                return true;
            }
        });
    }

    @Test
    public void test_getNumOfItemsOnCart() throws Exception {
        when(dataSource.getNumOfItemsOnCart()).thenReturn(Single.just(10));

        TestObserver<Integer> observer = repository.getNumOfItemsOnCart().test();
        observer.awaitTerminalEvent();

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

    @Test
    public void test_removeItemFromCart() {


        when(dataSource.remove(any(CartItemEntity.class))).thenReturn(Completable.complete());

        TestObserver<Void> observer = repository.removeItemFromCart(cartItem).test();
        observer.awaitTerminalEvent();

        verify(dataSource).remove(any(CartItemEntity.class));


    }

    @NonNull
    private List<CartItemEntity> getCartItemEntities(int max) {
        List<CartItemEntity> cartItems = new ArrayList<>();

        for (int i = 0; i < max; i++) {
            cartItems.add(new CartItemEntity("id" + i, "title" + i, 10.0d));
        }
        return cartItems;
    }

    @Test
    public void test_getCartItemByIdDrink() throws Exception {

        CartItemEntity cartItemEntity = new CartItemEntity("id", "title", 10.d);
        cartItemEntity.setDrinkEntity(new DrinkEntity(10, "name", 1.0d));

        when(dataSource.getCartItemById("id")).thenReturn(Single.just(cartItemEntity));

        TestObserver<CartItem> observer = repository.getCartItemById("id").test();
        observer.awaitTerminalEvent();

        observer.assertNoErrors().assertValue(new Predicate<CartItem>() {
            @Override
            public boolean test(CartItem cartItem) throws Exception {

                if (!cartItem.getId().equals("id")
                        || !cartItem.getTitle().equals("title")
                        || cartItem.getPrice() != 10.0d
                        || cartItem.getCartItemType() != CartItemType.DRINK) {
                    return false;
                }
                return true;
            }
        });
    }

    @Test
    public void test_getCartItemByIdPizza() throws Exception {

        CartItemEntity cartItemEntity = new CartItemEntity("id", "title", 10.d);
        List<Long> ingredients = Arrays.asList(5L);
        cartItemEntity.setPizzaEntity(new PizzaEntity("pizza", ingredients, "fakrUrl"));

        when(dataSource.getCartItemById("id")).thenReturn(Single.just(cartItemEntity));

        TestObserver<CartItem> observer = repository.getCartItemById("id").test();
        observer.awaitTerminalEvent();

        observer.assertNoErrors().assertValue(new Predicate<CartItem>() {
            @Override
            public boolean test(CartItem cartItem) throws Exception {

                if (!cartItem.getId().equals("id")
                        || !cartItem.getTitle().equals("title")
                        || cartItem.getPrice() != 10.0d
                        || cartItem.getCartItemType() != CartItemType.PIZZA) {
                    return false;
                }
                return true;
            }
        });
    }

    @Test
    public void test_addPizzaToCart() throws Exception {

        List<CartItemEntity> list = new ArrayList<>();
        list.add(cartItemEntity);
        list.add(cartItemEntity);
        list.add(cartItemEntity);

        when(dataSource.add(any(CartItemEntity.class))).thenReturn(Completable.complete());
        when(dataSource.list()).thenReturn(Single.just(list));
        TestObserver<Integer> observer = repository.addPizzaToCart(pizza).test();
        observer.awaitTerminalEvent();

        observer.assertNoErrors().assertValue(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Exception {
                return integer == 3;
            }
        });
    }

    @Test
    public void test_checkout() throws Exception {
        when(dataSource.checkout()).thenReturn(Completable.complete());

        TestObserver<Void> observer = repository.checkout().test();
        observer.awaitTerminalEvent();

        assertEquals(1, observer.assertNoErrors().completions());
    }
}