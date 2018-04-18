package ceduliocezar.com.nennospizza.di;

import javax.inject.Singleton;

import ceduliocezar.com.nennospizza.presentation.cart.CartActivity;
import ceduliocezar.com.nennospizza.presentation.cart.CartFragment;
import ceduliocezar.com.nennospizza.presentation.checkout.AfterCheckoutActivity;
import ceduliocezar.com.nennospizza.presentation.drink.DrinkFragment;
import ceduliocezar.com.nennospizza.presentation.pizza.list.PizzaListFragment;
import ceduliocezar.com.nennospizza.service.CartService;
import dagger.Component;

/**
 * Main entry point for dependency injection container.
 * Created by cedulio.silva on 4/12/2018.
 */

@Singleton
@Component(modules = {AppModule.class, PresentationModule.class, DataModule.class, DomainModule.class})
public interface AppComponent {

    void inject(PizzaListFragment pizzaListFragment);

    void inject(CartFragment cartFragment);

    void inject(AfterCheckoutActivity afterCheckoutActivity);

    void inject(DrinkFragment drinkFragment);

    void inject(CartService cartService);

    void inject(CartActivity cartActivity);
}
