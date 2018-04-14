package ceduliocezar.com.nennospizza.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Named;

import ceduliocezar.com.data.logging.LogcatLogger;
import ceduliocezar.com.domain.logging.Logger;
import dagger.Module;
import dagger.Provides;

/**
 * Application module responsible for provide general classes.
 * Created by cedulio.silva on 4/12/2018.
 */

@Module
public class AppModule {

    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @SuppressWarnings("unused")
    @Named("applicationContext")
    public Context providesApplicationContext() {
        return application;
    }

    @Provides
    @SuppressWarnings("unused")
    public Logger providesAppLogger(LogcatLogger logcatLogger) {
        return logcatLogger;
    }
}
