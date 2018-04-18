package ceduliocezar.com.nennospizza.presentation.cart;

import java.util.List;

/**
 * Contract for Cart screen.
 */
public class CartContract {


    interface View {
        void showCart(List<CartItemModel> cartItems);

        void navigateToAfterCheckoutScreen();

        void showCheckoutValue(double price);

        void showLoadingCartItems();

        void hideLoadingCartItems();

        void showLoadingCheckout();

        void showErrorOnCheckout();

        void hideLoadingCheckout();

        void showTotalPrice(Double price);

        void showEmptyCartMessage();

        void hideEmptyCartMessage();

        void hideTotalPrice();

        void finishScreen();

        boolean checkoutServiceRunning();

        void startCheckoutService();
    }

    public interface Presenter {
        void setView(View view);

        void init();

        void viewDestroyed();

        void onClickDeleteItemFromCart(CartItemModel cartItemModel);

        void onClickCheckout();

        void onCheckoutServiceFinished();
    }
}
