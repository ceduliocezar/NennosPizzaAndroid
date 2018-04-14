package ceduliocezar.com.nennospizza.di;

import ceduliocezar.com.data.repository.PizzaDataRepository;
import ceduliocezar.com.domain.repository.PizzaRepository;
import dagger.Module;
import dagger.Provides;

/**
 * Module for domain classes.
 * Created by cedulio.silva on 4/12/2018.
 */
@Module
public class DomainModule {

    @Provides
    @SuppressWarnings("unused")
    PizzaRepository providesPizzaRepository(PizzaDataRepository pizzaDataRepository) {
        return pizzaDataRepository;
    }

}
