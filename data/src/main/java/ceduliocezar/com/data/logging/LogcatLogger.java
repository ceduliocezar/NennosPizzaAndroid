package ceduliocezar.com.data.logging;

import android.util.Log;

import javax.inject.Inject;

import ceduliocezar.com.domain.logging.Logger;

/**
 * Logcat implementation of {@link Logger}
 * Created by cedulio.silva on 4/12/2018.
 */

public class LogcatLogger implements Logger {

    @Inject
    public LogcatLogger() {
        // DI constructor
    }

    @Override
    public void debug(String tag, String message) {
        Log.d(tag, message);
    }

    @Override
    public void info(String tag, String message) {
        Log.i(tag, message);
    }

    @Override
    public void warn(String tag, String message) {
        Log.w(tag, message);
    }

    @Override
    public void error(String tag, String message) {
        Log.e(tag, message);
    }

    @Override
    public void error(String tag, Throwable t) {
        Log.e(tag, Log.getStackTraceString(t));
    }
}
