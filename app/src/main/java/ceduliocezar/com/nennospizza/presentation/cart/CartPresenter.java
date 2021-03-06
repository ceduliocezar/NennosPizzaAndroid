package ceduliocezar.com.nennospizza.presentation.cart;

import java.util.List;

import javax.inject.Inject;

import ceduliocezar.com.domain.CartItem;
import ceduliocezar.com.domain.interactor.cart.GetCartItemById;
import ceduliocezar.com.domain.interactor.cart.GetCartItems;
import ceduliocezar.com.domain.interactor.cart.GetCartTotalPrice;
import ceduliocezar.com.domain.interactor.cart.ProceedCheckout;
import ceduliocezar.com.domain.interactor.cart.RemoveItemFromCart;
import ceduliocezar.com.domain.logging.Logger;
import io.reactivex.observers.DisposableObserver;

/**
 * Cart presenter, connects the view to the domain through uses cases with an rx approach.
 */
public class CartPresenter implements CartContract.Presenter {

    private static final String TAG = "CartPresenter";
    protected CartContract.View view;
    private GetCartItems getCartItems;
    private Logger logger;
    private CartItemPresentationMapper mapper;
    private RemoveItemFromCart removeItemFromCart;
    private GetCartItemById getCartItemById;
    private GetCartTotalPrice getCartTotalPrice;

    @Inject
    public CartPresenter(GetCartItems getCartItems,
                         Logger logger,
                         CartItemPresentationMapper mapper,
                         RemoveItemFromCart removeItemFromCart,
                         GetCartItemById getCartItemById,
                         GetCartTotalPrice getCartTotalPrice) {
        this.getCartItems = getCartItems;
        this.logger = logger;
        this.mapper = mapper;
        this.removeItemFromCart = removeItemFromCart;
        this.getCartItemById = getCartItemById;
        this.getCartTotalPrice = getCartTotalPrice;
    }

    @Override
    public void setView(CartContract.View view) {
        logger.debug(TAG, "setView: " + view.toString());
        this.view = view;
    }

    @Override
    public void init() {
        if (view == null) {
            throw new NullPointerException("view not set properly");
        }

        if (view.checkoutServiceRunning()) {
            view.showLoadingCheckout();
        } else {
            loadCartItems();
            loadTotalPrice();
        }
    }

    void loadTotalPrice() {
        logger.debug(TAG, "loadTotalPrice");

        getCartTotalPrice.execute(new DisposableObserver<Double>() {
            @Override
            public void onNext(Double price) {
                logger.debug(TAG, "loadTotalPrice: onNext" + price);
                if (hasViewAttached()) {
                    if (price > 0) {
                        view.showTotalPrice(price);
                    } else {
                        view.hideTotalPrice();
                    }

                }
            }

            @Override
            public void onError(Throwable e) {
                logger.error(TAG, e); // not specified what should be done in this casa
            }

            @Override
            public void onComplete() {
                logger.debug(TAG, "loadTotalPrice: onComplete");
            }
        }, null);
    }

    void loadCartItems() {
        logger.debug(TAG, "loadCartItems");
        if (hasViewAttached()) {
            view.showLoadingCartItems();
        }
        getCartItems.execute(new DisposableObserver<List<CartItem>>() {
            @Override
            public void onNext(List<CartItem> cartItems) {
                logger.debug(TAG, "onNext: " + cartItems.toString());
                if (hasViewAttached()) {
                    onLoadCartItems(cartItems);
                }
            }

            @Override
            public void onError(Throwable e) {
                logger.error(TAG, e); // not defined in specs what happened when an error occurs, by now just log error
            }

            @Override
            public void onComplete() {
                logger.debug(TAG, "onComplete");
                if (hasViewAttached()) {
                    view.hideLoadingCartItems();
                }
            }
        }, null);
    }

    private void onLoadCartItems(List<CartItem> cartItems) {
        logger.debug(TAG, "onLoadCartItems: " + cartItems);
        view.showCart(mapper.transform(cartItems));
        if (cartItems.isEmpty()) {
            view.showEmptyCartMessage();
        } else {
            view.hideEmptyCartMessage();
        }
    }

    private boolean hasViewAttached() {
        return view != null;
    }

    @Override
    public void viewDestroyed() {
        logger.debug(TAG, "viewDestroyed");
        view = null;
    }

    @Override
    public void onClickDeleteItemFromCart(CartItemModel cartItemModel) {
        logger.debug(TAG, "onClickDeleteItemFromCart: " + cartItemModel.toString());

        getCartItemById.execute(new DisposableObserver<CartItem>() {
            @Override
            public void onNext(CartItem cartItem) {
                removeItemFromCart(cartItem);
            }

            @Override
            public void onError(Throwable e) {
                logger.error(TAG, e); // not defined in ux what happened when an error occurs, by now just log error
            }

            @Override
            public void onComplete() {
                logger.debug(TAG, "onClickDeleteItemFromCart: onComplete");
            }
        }, cartItemModel.getId());
    }

    void removeItemFromCart(CartItem cartItem) {
        logger.debug(TAG, "removeItemFromCart: " + cartItem.toString());
        removeItemFromCart.execute(new DisposableObserver<Void>() {
            @Override
            public void onNext(Void aVoid) {
                logger.debug(TAG, "removeItemFromCart: onNext");
            }

            @Override
            public void onError(Throwable e) {
                logger.error(TAG, e); // not defined in ux what happened when an error occurs, by now just log error
            }

            @Override
            public void onComplete() {
                logger.debug(TAG, "removeItemFromCart: onComplete");
                if (hasViewAttached()) {
                    loadCartItems();
                    loadTotalPrice();

                }
            }
        }, cartItem);
    }

    @Override
    public void onClickCheckout() {
        logger.debug(TAG, "onClickCheckout");
        view.showLoadingCheckout();
        view.startCheckoutService();
    }

    @Override
    public void onCheckoutServiceFinished() {
        logger.debug(TAG, "onCheckoutServiceFinished");
        view.navigateToAfterCheckoutScreen();
    }
}
