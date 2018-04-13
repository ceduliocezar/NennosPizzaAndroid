package ceduliocezar.com.nennospizza.di;

import javax.inject.Named;

import ceduliocezar.com.data.remote.NennosService;
import ceduliocezar.com.nennospizza.BuildConfig;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

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
    Retrofit providesRetrofit(@Named("baseUrl") String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .build();
    }

    @Named("baseUrl")
    @Provides
    String providesBaseUrl() {
        return BuildConfig.nennosEndpointUrl;
    }
}
