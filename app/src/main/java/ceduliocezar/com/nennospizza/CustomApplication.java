package ceduliocezar.com.nennospizza;

import android.app.Application;

import ceduliocezar.com.nennospizza.di.AppComponent;
import ceduliocezar.com.nennospizza.di.AppModule;
import ceduliocezar.com.nennospizza.di.DaggerAppComponent;
import ceduliocezar.com.nennospizza.di.DataModule;
import ceduliocezar.com.nennospizza.di.DomainModule;
import ceduliocezar.com.nennospizza.di.PresentationModule;

/**
 * Android application implementation, responsible for setup basic structure for the application
 * like dependency injection.
 * Created by cedulio.silva on 4/11/2018.
 */

public class CustomApplication extends Application {

    private AppComponent appComponent = createComponent();


    /**
     * Keep this method separated to enable override on android instrumented tests package.
     */
    AppComponent createComponent() {

        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .dataModule(new DataModule())
                .presentationModule(new PresentationModule())
                .domainModule(new DomainModule())
                .build();
    }

    public AppComponent component() {
        return appComponent;
    }
}
