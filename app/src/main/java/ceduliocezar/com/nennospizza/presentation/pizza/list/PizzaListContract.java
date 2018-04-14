package ceduliocezar.com.nennospizza.presentation.pizza.list;

import java.util.List;

/**
 * Contract for Pizza List.
 * Created by cedulio.silva on 4/12/2018.
 */

public class PizzaListContract {

    public interface View {
        /**
         * Shows a list o pizzas in the UI.
         *
         * @param pizzas - list of pizzas that will be shown in the UI.
         */
        void showPizzas(List<PizzaModel> pizzas);

        /**
         * Shows custom pizza creation screen.
         */
        void showCreateCustomPizzaScreen();

        /**
         * Shows pizza details screen pizza.
         *
         * @param pizzaModel - pizza model to be shown
         */
        void showDetailPizzaScreen(PizzaModel pizzaModel);

        /**
         * Displays the num of items add to cart.
         *
         * @param itemsInCart -  Number of items in cart.
         */
        void showCartNotification(int itemsInCart);

        /**
         * Hides cart notification badge.
         */
        void hideCartNotification();
    }

    public interface Presenter {

        /**
         * Defines a view to be notified by the presenter.
         *
         * @param view - view that will receive updates.
         */
        void setView(View view);

        /**
         * Loads data for the first time.
         */
        void init();

        /**
         * Releases all the references held by the presenter.
         */
        void viewDestroyed();

        /**
         * Called when user selected on UI aa pizza.
         *
         * @param pizzaModel - Pizza selected
         */
        void userSelectedPizza(PizzaModel pizzaModel);

        /**
         * Called when user desires to add a specific pizza to cart.
         *
         * @param pizzaModel - Pizza to be added to cart.
         */
        void userSelectedAddPizzaToCart(PizzaModel pizzaModel);
    }
}
