package ceduliocezar.com.nennospizza.navigation;

import android.content.Context;

public interface Navigator {

    void navigateToCreateCustomPizza(Context context);

    void navigateToCartScreen(Context context);

    void navigateToPizzaDetailScreen(Context context);
}
