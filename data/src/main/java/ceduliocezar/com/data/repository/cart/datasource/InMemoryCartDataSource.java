package ceduliocezar.com.data.repository.cart.datasource;


import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.inject.Inject;
import javax.inject.Singleton;

import ceduliocezar.com.data.entity.CartItemEntity;
import ceduliocezar.com.data.entity.DrinkEntity;
import ceduliocezar.com.data.entity.PizzaEntity;
import ceduliocezar.com.data.remote.CheckoutService;
import ceduliocezar.com.data.repository.cart.CartTO;
import ceduliocezar.com.domain.logging.Logger;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;


/**
 * In memory {@link CartItemEntity} {@link CartDataSource} implementation.
 */
@Singleton
public class InMemoryCartDataSource implements CartDataSource {

    private static final String TAG = "InMemoryCartItemDataSou";

    private List<CartItemEntity> cart = new ArrayList<>();
    private Logger logger;
    private CheckoutService checkoutService;

    @Inject
    public InMemoryCartDataSource(Logger logger,
                                  CheckoutService checkoutService) {
        this.logger = logger;
        this.checkoutService = checkoutService;
    }

    @Override
    public Single<List<CartItemEntity>> list() {
        logger.debug(TAG, "list");
        return Single.just(cart);
    }

    @Override
    public Completable add(final CartItemEntity cartItemEntity) {
        logger.debug(TAG, "add");
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) {
                cart.add(cartItemEntity);
                emitter.onComplete();
            }
        });
    }

    @Override
    public Completable remove(final CartItemEntity cartItemEntity) {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) {
                ListIterator<CartItemEntity> iterator = cart.listIterator();
                String id = cartItemEntity.getId();
                while (iterator.hasNext()) {
                    if (iterator.next().getId().equals(id)) {
                        iterator.remove();
                        break;
                    }
                }

                emitter.onComplete();
            }
        });
    }

    @Override
    public Completable checkout() {
        logger.debug(TAG, "add");
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) {
                checkoutService.checkout(createTO()).blockingAwait();
                newCart();
                emitter.onComplete();
            }
        });
    }

    @Override
    public Single<CartItemEntity> getCartItemById(final String id) {
        return Single.create(new SingleOnSubscribe<CartItemEntity>() {
            @Override
            public void subscribe(SingleEmitter<CartItemEntity> emitter) {
                CartItemEntity cartItemEntity = null;

                for (CartItemEntity entity : cart) {
                    if (entity.getId().equals(id)) {
                        cartItemEntity = entity;
                        break;
                    }
                }

                if (cartItemEntity != null) {
                    emitter.onSuccess(cartItemEntity);
                } else {
                    emitter.onError(new RuntimeException("Cart Item not found"));
                }
            }
        });
    }

    @Override
    public Single<Integer> getNumOfItemsOnCart() {
        logger.debug(TAG, "getNumOfItemsOnCart");
        return Single.create(new SingleOnSubscribe<Integer>() {
            @Override
            public void subscribe(SingleEmitter<Integer> emitter) throws Exception {
                emitter.onSuccess(cart.size());
            }
        });
    }

    private void newCart() {
        cart = new ArrayList<>();
    }

    private CartTO createTO() {
        List<PizzaEntity> pizzas = new ArrayList<>();
        List<DrinkEntity> drinks = new ArrayList<>();

        for (CartItemEntity cartItemEntity : cart) {
            PizzaEntity pizzaEntity = cartItemEntity.getPizzaEntity();
            DrinkEntity drinkEntity = cartItemEntity.getDrinkEntity();
            if (pizzaEntity != null) {
                pizzas.add(pizzaEntity);
            } else if (drinkEntity != null) {
                drinks.add(drinkEntity);
            }

        }
        return new CartTO(pizzas, drinks);
    }
}
