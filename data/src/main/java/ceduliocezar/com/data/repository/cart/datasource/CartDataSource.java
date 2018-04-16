package ceduliocezar.com.data.repository.cart.datasource;

import java.util.List;

import ceduliocezar.com.data.entity.CartItemEntity;
import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Provides data about {@link ceduliocezar.com.domain.CartItem}
 */
public interface CartDataSource {

    /**
     * Retrieve all the cart items available.
     *
     * @return list with all {@link CartItemEntity}.
     */
    Single<List<CartItemEntity>> list();


    /**
     * Add a {@link CartItemEntity} to the cart.
     *
     * @param cartItemEntity will be added to cart.
     */
    Completable add(CartItemEntity cartItemEntity);


    /**
     * Will remove the car entity if it exists on cart.
     *
     * @param cartItemEntity to be removed.
     */
    Completable remove(CartItemEntity cartItemEntity);

    /**
     * Checkout current cart.
     */
    Completable checkout();

    Single<CartItemEntity> getCartItemById(String id);

    Single<Integer> getNumOfItemsOnCart();
}
