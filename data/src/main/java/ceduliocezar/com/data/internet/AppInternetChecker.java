package ceduliocezar.com.data.internet;

import javax.inject.Inject;

public class AppInternetChecker implements InternetChecker {

    @Inject
    public AppInternetChecker() {
        // TODO: 14/04/2018
    }

    @Override
    public boolean isDeviceOnline() {
        return false; // TODO: 14/04/2018
    }
}
