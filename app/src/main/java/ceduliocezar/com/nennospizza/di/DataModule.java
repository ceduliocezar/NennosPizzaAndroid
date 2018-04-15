package ceduliocezar.com.nennospizza.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Named;
import javax.inject.Singleton;

import ceduliocezar.com.data.remote.CheckoutService;
import ceduliocezar.com.data.remote.MenuService;
import ceduliocezar.com.data.repository.cart.CartTO;
import ceduliocezar.com.data.repository.cart.datasource.CartDataSource;
import ceduliocezar.com.data.repository.cart.datasource.InMemoryCartDataSource;
import ceduliocezar.com.data.repository.ingredient.IngredientDataSource;
import ceduliocezar.com.data.repository.ingredient.datasource.CloudIngredientDataSource;
import ceduliocezar.com.data.repository.pizza.PizzaDataSource;
import ceduliocezar.com.data.repository.pizza.datasource.cloud.CloudPizzaDataSource;
import ceduliocezar.com.data.serialization.CartTOSerializer;
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
    MenuService providesMenuService(@Named("menuService") Retrofit retrofit) {
        return retrofit.create(MenuService.class);
    }

    @Provides
    CheckoutService providesCheckoutService(@Named("checkoutService") Retrofit retrofit) {
        return retrofit.create(CheckoutService.class);
    }

    @Provides
    @Named("menuService")
    Retrofit providesMenuRetrofit(@Named("menuServiceUrl") String url,
                                  GsonConverterFactory factory) {

        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Named("checkoutService")
    Retrofit providesCheckoutRetrofit(@Named("checkoutServiceUrl") String url,
                                      GsonConverterFactory factory) {

        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    GsonConverterFactory providesGsonConverterFactory(CartTOSerializer cartTOSerializer,
                                                      GsonBuilder gsonBuilder) {

        gsonBuilder.registerTypeAdapter(CartTO.class, cartTOSerializer);

        Gson gson = gsonBuilder.create();
        return GsonConverterFactory.create(gson);
    }

    @Provides
    GsonBuilder providesGsonBuilder() {
        return new GsonBuilder();
    }

    @Named("menuServiceUrl")
    @Provides
    String providesMenuServiceUrl() {
        return BuildConfig.menuServiceUrl;
    }

    @Named("checkoutServiceUrl")
    @Provides
    String providesCheckoutServiceUrl() {
        return BuildConfig.checkoutServiceUrl;
    }

    @Provides
    PizzaDataSource providesCloudPizzaDataSource(CloudPizzaDataSource cloudPizzaDataSource) {
        return cloudPizzaDataSource;
    }


    @Provides
    IngredientDataSource providesIngredientDataSource(CloudIngredientDataSource ingredientDataSource) {
        return ingredientDataSource;
    }

    @Provides
    @Singleton
    CartDataSource providesCartDatasource(InMemoryCartDataSource inMemoryCartDataSource) {
        return inMemoryCartDataSource;
    }
}
