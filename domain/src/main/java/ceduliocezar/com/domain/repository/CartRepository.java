package ceduliocezar.com.domain.repository;

import java.util.List;

import ceduliocezar.com.domain.CartItem;
import ceduliocezar.com.domain.Pizza;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Manipulates information about {@link CartItem}
 */
public interface CartRepository {

    Single<List<CartItem>> list();

    Completable removeItemFromCart(CartItem cartItem);

    Single<Integer> addPizzaToCart(Pizza pizza);

    Single<CartItem> getCartItemById(String id);

    Completable checkout();

    Single<Integer> getNumOfItemsOnCart();

    Single<Double> getCartTotalPrice();
}
