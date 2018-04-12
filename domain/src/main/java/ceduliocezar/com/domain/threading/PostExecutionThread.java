package ceduliocezar.com.domain.threading;

import io.reactivex.Scheduler;

/**
 * Created by cedulio.silva on 4/12/2018.
 */

public interface PostExecutionThread {

    Scheduler getScheduler();
}
