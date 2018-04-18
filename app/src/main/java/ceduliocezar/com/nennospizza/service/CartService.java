package ceduliocezar.com.nennospizza.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import javax.inject.Inject;

import ceduliocezar.com.domain.interactor.cart.ProceedCheckout;
import ceduliocezar.com.domain.logging.Logger;
import ceduliocezar.com.nennospizza.CustomApplication;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by cedulio.silva on 4/18/2018.
 */

public class CartService extends Service {

    private static final String TAG = "CartService";

    public static final String FINISH_CHECKOUT_ACTION = "ceduliocezar.com.nennospizza.cart.FINISH_CHECKOUT";

    private final IBinder binder = new LocalBinder();
    private boolean running = false;

    @Inject
    Logger logger;

    @Inject
    ProceedCheckout proceedCheckout;

    @Override
    public IBinder onBind(Intent arg0) {
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        ((CustomApplication) getApplicationContext()).component().inject(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        running = true;
        startCheckout();
        return START_NOT_STICKY;
    }

    private void startCheckout() {
        logger.debug(TAG, "startCheckout");
        proceedCheckout.execute(new DisposableObserver<Void>() {
            @Override
            public void onNext(Void aVoid) {

            }

            @Override
            public void onError(Throwable e) {
                logger.error(TAG, e);
                stopSelf();
            }

            @Override
            public void onComplete() {
                logger.debug(TAG, "onComplete");
                running = false;
                sendFinishBroadcast();
                stopSelf();
            }
        }, null);
    }

    void sendFinishBroadcast() {
        logger.debug(TAG, "sendFinishBroadcast");
        Intent intent = new Intent();
        intent.setAction(FINISH_CHECKOUT_ACTION);
        sendBroadcast(intent);
    }

    public class LocalBinder extends Binder {
        public CartService getService() {
            return CartService.this;
        }
    }

    public boolean isRunning() {
        return running;
    }
}
