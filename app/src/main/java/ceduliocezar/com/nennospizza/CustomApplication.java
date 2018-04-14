package ceduliocezar.com.nennospizza;

import android.app.Application;

import ceduliocezar.com.nennospizza.di.AppComponent;
import ceduliocezar.com.nennospizza.di.AppModule;
import ceduliocezar.com.nennospizza.di.DaggerAppComponent;
import ceduliocezar.com.nennospizza.di.PresentationModule;

/**
 * Android application implementation, responsible for setup basic structure for the application
 * like dependency injection.
 * Created by cedulio.silva on 4/11/2018.
 */

public class CustomApplication extends Application {

    private AppComponent appComponent = createComponent();


    /**
     * Dedicated method to only component creation to enable polymorphism on android instrumented tests package.
     */
    AppComponent createComponent() {

        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .presentationModule(new PresentationModule())
                .build();
    }

    public AppComponent component() {
        return appComponent;
    }
}
