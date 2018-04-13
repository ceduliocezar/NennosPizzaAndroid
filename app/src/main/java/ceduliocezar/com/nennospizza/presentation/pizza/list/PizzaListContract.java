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
    }
}
