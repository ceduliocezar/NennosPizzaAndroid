package ceduliocezar.com.nennospizza.presentation.drink;

import java.util.List;

public interface DrinksContract {

    interface View {
        void showDrinks(List<DrinkModel> drinks);

        void showLoadingDrinks();

        void hideLoadingDrinks();

        void showNotificationDrinkAdded();

        void showErrorMessageOnAddDrink();
    }

    interface Presenter {

        void viewDestroyed();

        void setView(View view);

        void init();

        void onClickAddDrink(DrinkModel drinkModel);
    }

}
