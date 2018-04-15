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

        void finishCurrentScreen();
    }

    public interface Presenter {
        void setView(View view);

        void init();

        void viewDestroyed();

        void onClickDeleteItemFromCart(CartItemModel cartItemModel);

        void onClickCheckout();
    }
}
