package ceduliocezar.com.nennospizza.di;

import javax.inject.Named;

import ceduliocezar.com.data.remote.NennosService;
import ceduliocezar.com.data.repository.ingredient.IngredientDataSource;
import ceduliocezar.com.data.repository.ingredient.datasource.CloudIngredientDataSource;
import ceduliocezar.com.data.repository.pizza.PizzaDataSource;
import ceduliocezar.com.data.repository.pizza.datasource.cloud.CloudPizzaDataSource;
import ceduliocezar.com.nennospizza.BuildConfig;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Module that provides instances of data classes.
 * Created by cedulio.silva on 4/12/2018.
 */

@Module
public class DataModule {

    @Provides
    NennosService providesNennosService(Retrofit retrofit) {
        return retrofit.create(NennosService.class);
    }

    @Provides
    Retrofit providesRetrofit(@Named("baseUrl") String baseUrl, GsonConverterFactory factory) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    GsonConverterFactory providesGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Named("baseUrl")
    @Provides
    String providesBaseUrl() {
        return BuildConfig.nennosEndpointUrl;
    }

    @Provides
    PizzaDataSource providesCloudPizzaDataSource(CloudPizzaDataSource cloudPizzaDataSource) {
        return cloudPizzaDataSource;
    }


    @Provides
    IngredientDataSource providesIngredientDataSource(CloudIngredientDataSource ingredientDataSource) {
        return ingredientDataSource;
    }
}
