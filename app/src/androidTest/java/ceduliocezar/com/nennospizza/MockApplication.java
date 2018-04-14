package ceduliocezar.com.nennospizza;

import ceduliocezar.com.nennospizza.di.AppComponent;
import ceduliocezar.com.nennospizza.di.DaggerTestComponent;
import ceduliocezar.com.nennospizza.di.DataModule;
import ceduliocezar.com.nennospizza.di.DomainModule;
import ceduliocezar.com.nennospizza.di.MockPresentationModule;


/**
 * Mock extension of {@link CustomApplication} that enables mocking in UI tests .
 */
public class MockApplication extends CustomApplication {


    @Override
    AppComponent createComponent() {
        return DaggerTestComponent.builder()
                .mockPresentationModule(new MockPresentationModule())
                .dataModule(new DataModule())
                .domainModule(new DomainModule())
                .build();
    }
}
