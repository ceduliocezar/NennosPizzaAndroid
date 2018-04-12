package ceduliocezar.com.nennospizza;

import javax.inject.Inject;
import javax.inject.Singleton;

import ceduliocezar.com.domain.threading.PostExecutionThread;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

@Singleton
public class UiThread implements PostExecutionThread {

    @Inject
    UiThread() {
        // mandatory DI constructor
    }


    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
