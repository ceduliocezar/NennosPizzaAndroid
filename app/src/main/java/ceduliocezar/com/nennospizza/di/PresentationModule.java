package ceduliocezar.com.nennospizza.di;

import ceduliocezar.com.nennospizza.navigation.AppNavigator;
import ceduliocezar.com.nennospizza.navigation.Navigator;
import ceduliocezar.com.nennospizza.presentation.cart.AppCartItemPresentationMapper;
import ceduliocezar.com.nennospizza.presentation.cart.CartContract;
import ceduliocezar.com.nennospizza.presentation.cart.CartItemPresentationMapper;
import ceduliocezar.com.nennospizza.presentation.cart.CartPresenter;
import ceduliocezar.com.nennospizza.presentation.pizza.AppPizzaPresentationMapper;
import ceduliocezar.com.nennospizza.presentation.pizza.PizzaPresentationMapper;
import ceduliocezar.com.nennospizza.presentation.pizza.list.PizzaListContract;
import ceduliocezar.com.nennospizza.presentation.pizza.list.PizzaListPresenter;
import dagger.Module;
import dagger.Provides;

/**
 * Module that provides presentation classes.
 * Created by cedulio.silva on 4/12/2018.
 */
@Module
public class PresentationModule {

    @Provides
    Navigator providesNavigator(AppNavigator appNavigator) {
        return appNavigator;
    }

    @Provides
    public PizzaListContract.Presenter providesPizzaListPresenter(PizzaListPresenter presenter) {
        return presenter;
    }

    @Provides
    public CartItemPresentationMapper providesCartItemPresentationMapper(AppCartItemPresentationMapper mapper) {
        return mapper;
    }

    @Provides
    public PizzaPresentationMapper providesPizzaPresentationMapper(AppPizzaPresentationMapper mapper) {
        return mapper;
    }

    @Provides
    public CartContract.Presenter providesCartPresenter(CartPresenter presenter) {
        return presenter;
    }


}
