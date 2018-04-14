package ceduliocezar.com.nennospizza.di;

import ceduliocezar.com.nennospizza.navigation.AppNavigator;
import ceduliocezar.com.nennospizza.navigation.Navigator;
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
    @SuppressWarnings("unused")
    Navigator providesNavigator(AppNavigator appNavigator) {
        return appNavigator;
    }

    @Provides
    @SuppressWarnings("unused")
    public PizzaListContract.Presenter providesPizzaListPresenter(PizzaListPresenter presenter) {
        return presenter;
    }

    @Provides
    @SuppressWarnings("unused")
    public PizzaPresentationMapper providesPizzaPresentationMapper(AppPizzaPresentationMapper mapper) {
        return mapper;
    }

}
