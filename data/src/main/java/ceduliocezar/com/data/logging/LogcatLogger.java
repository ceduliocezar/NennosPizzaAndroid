package ceduliocezar.com.data.logging;

import android.util.Log;

import ceduliocezar.com.domain.logging.AppLogger;

/**
 * Logcat implementation of {@link AppLogger}
 * Created by cedulio.silva on 4/12/2018.
 */

public class LogcatLogger implements AppLogger {
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
}
