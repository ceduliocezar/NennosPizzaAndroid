package ceduliocezar.com.nennospizza.di;

import javax.inject.Singleton;

import ceduliocezar.com.domain.logging.Logger;
import ceduliocezar.com.nennospizza.navigation.Navigator;
import ceduliocezar.com.nennospizza.presentation.cart.CartContract;
import ceduliocezar.com.nennospizza.presentation.cart.CartItemModel;
import ceduliocezar.com.nennospizza.presentation.cart.CartItemPresentationMapper;
import ceduliocezar.com.nennospizza.presentation.pizza.PizzaPresentationMapper;
import ceduliocezar.com.nennospizza.presentation.pizza.list.PizzaListContract;
import ceduliocezar.com.nennospizza.presentation.pizza.list.PizzaModel;
import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;

/**
 * Module for presentation that provides mocks that can be controlled and verified
 * during UI tests.
 * <p>
 * - All instances provide here <b>MUST</b> be singletons,
 * in order to be able to verify interactions in test methods.
 * <p>
 * - Each test suite is responsible for cleaning interactions with mocks before and after each test.
 * <p>
 * - Use the instances provided here with {@link javax.inject.Inject} annotation.
 */
@Module
public class MockPresentationModule {


    @Singleton
    @Provides
    PizzaListContract.Presenter providesPizzaListPresenter() {
        return mock(PizzaListContract.Presenter.class);
    }


    @Provides
    public CartItemPresentationMapper providesCartItemPresentationMapper() {
        return mock(CartItemPresentationMapper.class);
    }

    @Singleton
    @Provides
    CartContract.Presenter providesCartPresenter() {
        return mock(CartContract.Presenter.class);
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

    @Provides
    @Singleton
    CartItemModel provideCartItemModel() {
        return mock(CartItemModel.class);
    }

    @Provides
    @Singleton
    PizzaPresentationMapper providesPizzaPresentationMapper() {
        return mock(PizzaPresentationMapper.class);
    }
}
