package ceduliocezar.com.domain.logging;

/**
 * Wrapper to logging functions.
 * Created by cedulio.silva on 4/12/2018.
 */

public interface Logger {

    void debug(String tag, String message);

    void info(String tag, String message);

    void warn(String tag, String message);

    void error(String tag, String message);

    void error(String tag, Throwable e);

}
