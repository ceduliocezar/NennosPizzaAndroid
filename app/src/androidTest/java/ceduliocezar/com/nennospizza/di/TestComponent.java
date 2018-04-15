package ceduliocezar.com.nennospizza.di;


import javax.inject.Singleton;

import ceduliocezar.com.nennospizza.presentation.cart.CartFragmentTest;
import ceduliocezar.com.nennospizza.presentation.pizza.list.PizzaListFragmentTest;
import dagger.Component;

/**
 * Test component to inject mocks inside test classes.
 */
@Singleton
@Component(modules = {MockPresentationModule.class, DataModule.class, DomainModule.class})
public interface TestComponent extends AppComponent {


    void inject(PizzaListFragmentTest pizzaListFragmentTest);

    void inject(CartFragmentTest cartFragmentTest);
}
