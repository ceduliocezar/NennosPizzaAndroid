package ceduliocezar.com.data.repository.cart;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import ceduliocezar.com.data.entity.CartItemEntity;
import ceduliocezar.com.data.entity.DrinkEntity;
import ceduliocezar.com.data.repository.cart.datasource.CartDataSource;
import ceduliocezar.com.domain.CartItem;
import ceduliocezar.com.domain.CartItemType;
import ceduliocezar.com.domain.Drink;
import ceduliocezar.com.domain.Pizza;
import ceduliocezar.com.domain.logging.Logger;
import ceduliocezar.com.domain.repository.CartRepository;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.functions.Function;

/**
 * Implementation of {@link CartRepository}.
 */
public class CartDataRepository implements CartRepository {

    private static final String TAG = "CartDataRepository";

    private CartDataSource cartDataSource;
    private Logger logger;

    @Inject
    public CartDataRepository(CartDataSource cartDataSource,
                              Logger logger) {
        this.cartDataSource = cartDataSource;
        this.logger = logger;
    }

    @Override
    public Single<List<CartItem>> list() {
        logger.debug(TAG, "list");
        return Single.create(new SingleOnSubscribe<List<CartItem>>() {
            @Override
            public void subscribe(SingleEmitter<List<CartItem>> emitter) {
                List<CartItemEntity> cartItemEntities = cartDataSource.list().blockingGet();

                List<CartItem> cart = new ArrayList<>();
                for (CartItemEntity cartItemEntity : cartItemEntities) {

                    CartItemType type = CartItemType.PIZZA;

                    if (cartItemEntity.getDrinkEntity() != null) {
                        type = CartItemType.DRINK;
                    }

                    CartItem cartItem = new CartItem(cartItemEntity.getId(),
                            cartItemEntity.getTitle(),
                            cartItemEntity.getPrice(), type);

                    cart.add(cartItem);
                }

                emitter.onSuccess(cart);
            }
        });
    }

    @Override
    public Completable removeItemFromCart(CartItem cartItem) {
        logger.debug(TAG, "list");
        CartItemEntity cartItemEntity = new CartItemEntity(cartItem.getId(), cartItem.getTitle(), cartItem.getPrice());
        return cartDataSource
                .remove(cartItemEntity);
    }

    @Override
    public Single<Integer> addPizzaToCart(Pizza pizza) {
        logger.debug(TAG, "addPizzaToCart: " + pizza.toString());
        String uniqueID = UUID.randomUUID().toString();

        CartItemEntity cartItemEntity = new CartItemEntity(uniqueID, pizza.getName(), pizza.getTotalPrice());
        return cartDataSource
                .add(cartItemEntity)
                .toSingle(new Callable<Integer>() {
                    @Override
                    public Integer call() {
                        return list().blockingGet().size();
                    }
                });
    }

    @Override
    public Single<CartItem> getCartItemById(String id) {
        logger.debug(TAG, "getCartItemById: " + id);
        return cartDataSource.getCartItemById(id).map(new Function<CartItemEntity, CartItem>() {
            @Override
            public CartItem apply(CartItemEntity cartItemEntity) throws Exception {

                CartItemType type = CartItemType.PIZZA;

                if (cartItemEntity.getDrinkEntity() != null) {
                    type = CartItemType.DRINK;
                }
                return new CartItem(cartItemEntity.getId(), cartItemEntity.getTitle(), cartItemEntity.getPrice(), type);
            }
        });
    }

    @Override
    public Completable checkout() {
        logger.debug(TAG, "checkout");
        return cartDataSource.checkout();
    }

    @Override
    public Single<Integer> getNumOfItemsOnCart() {
        logger.debug(TAG, "getNumOfItemsOnCart");
        return cartDataSource
                .getNumOfItemsOnCart();
    }

    @Override
    public Single<Double> getCartTotalPrice() {
        return cartDataSource.getCartTotalPrice();
    }

    @Override
    public Completable addDrinkToCart(Drink drink) {
        String uniqueID = UUID.randomUUID().toString();

        CartItemEntity cartItemEntity = new CartItemEntity(uniqueID, drink.getName(), drink.getPrice());
        cartItemEntity.setDrinkEntity(new DrinkEntity(drink.getId(), drink.getName(), drink.getPrice()));
        return cartDataSource
                .add(cartItemEntity);
    }
}
