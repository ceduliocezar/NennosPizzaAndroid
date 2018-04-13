package ceduliocezar.com.nennospizza.di;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import javax.inject.Named;

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
    public PizzaListContract.Presenter providesPizzaListPresenter(PizzaListPresenter presenter) {
        return presenter;
    }

}
