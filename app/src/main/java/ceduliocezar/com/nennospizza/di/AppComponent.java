package ceduliocezar.com.nennospizza.di;

import ceduliocezar.com.nennospizza.presentation.pizza.list.PizzaListFragment;
import dagger.Component;

/**
 * Main entry point for dependency injection container.
 * Created by cedulio.silva on 4/12/2018.
 */

@Component(modules = {AppModule.class, PresentationModule.class, DataModule.class, DomainModule.class})
public interface AppComponent {

    void inject(PizzaListFragment pizzaListFragment);

}
