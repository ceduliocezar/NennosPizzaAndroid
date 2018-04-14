package ceduliocezar.com.nennospizza.di;

import javax.inject.Singleton;

import ceduliocezar.com.domain.logging.Logger;
import ceduliocezar.com.nennospizza.navigation.Navigator;
import ceduliocezar.com.nennospizza.presentation.pizza.list.PizzaListContract;
import ceduliocezar.com.nennospizza.presentation.pizza.list.PizzaModel;
import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;

/**
 * Module for presentation that provides singleton mocks that can be controlled and verified
 * during UI tests.
 */
@Module
public class MockPresentationModule {


    @Singleton
    @Provides
    PizzaListContract.Presenter providesPizzaListPresenter() {
        return mock(PizzaListContract.Presenter.class);
    }

    @Singleton
    @Provides
    Navigator providesNavigator() {
        return mock(Navigator.class);
    }

    @Singleton
    @Provides
    Logger providesLogger() {
        return mock(Logger.class);
    }

    @Provides
    @Singleton
    PizzaModel providesPizzaModel() {
        return mock(PizzaModel.class);
    }
}
