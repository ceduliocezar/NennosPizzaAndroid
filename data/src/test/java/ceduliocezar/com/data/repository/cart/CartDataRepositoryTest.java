package ceduliocezar.com.data.repository.cart;

import android.support.annotation.NonNull;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import ceduliocezar.com.data.entity.CartItemEntity;
import ceduliocezar.com.data.repository.cart.datasource.CartDataSource;
import ceduliocezar.com.domain.CartItem;
import ceduliocezar.com.domain.CartItemType;
import ceduliocezar.com.domain.logging.Logger;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.TestObserver;

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
}