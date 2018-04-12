package ceduliocezar.com.nennospizza.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import ceduliocezar.com.data.executor.JobExecutor;
import ceduliocezar.com.data.logging.LogcatLogger;
import ceduliocezar.com.domain.logging.Logger;
import ceduliocezar.com.domain.threading.PostExecutionThread;
import ceduliocezar.com.domain.threading.ThreadExecutor;
import ceduliocezar.com.nennospizza.UiThread;
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

    @Singleton
    @Provides
    @SuppressWarnings("unused")
    public Logger providesAppLogger(LogcatLogger logcatLogger) {
        return logcatLogger;
    }

    @Provides
    @SuppressWarnings("unused")
    @Singleton
    public ThreadExecutor providesThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Singleton
    @Provides
    @SuppressWarnings("unused")
    public PostExecutionThread providesPostExecutionThread(UiThread uiThread) {
        return uiThread;
    }
}
