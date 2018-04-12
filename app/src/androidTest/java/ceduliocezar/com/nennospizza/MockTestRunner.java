package ceduliocezar.com.nennospizza;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

/**
 * This class is the starting point for DI container customization,
 * it replaces the normal {@link CustomApplication} with {@link MockApplication}.
 */
public class MockTestRunner extends AndroidJUnitRunner {

    @Override
    public Application newApplication(ClassLoader cl, String className, Context context)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return super.newApplication(cl, MockApplication.class.getName(), context);
    }
}
