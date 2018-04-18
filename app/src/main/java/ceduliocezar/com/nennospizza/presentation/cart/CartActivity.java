package ceduliocezar.com.nennospizza.presentation.cart;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import ceduliocezar.com.domain.logging.Logger;
import ceduliocezar.com.nennospizza.CustomApplication;
import ceduliocezar.com.nennospizza.R;
import ceduliocezar.com.nennospizza.service.CartService;

public class CartActivity extends AppCompatActivity implements CartFragment.Callback {


    private static final String TAG = "CartActivity";
    private CartFragment cartFragment;

    private BroadcastReceiver cartBroadCastReceiver;

    CartService cartService;

    @Inject
    Logger logger;

    private ServiceConnection serviceConnection = new ServiceConnection() {

        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            logger.debug(TAG, "onServiceConnected");
            CartService.LocalBinder binder = (CartService.LocalBinder) service;
            cartService = binder.getService();

            if (cartFragment != null) {
                cartFragment.onCartServiceConnected();
            }
        }

        public void onServiceDisconnected(ComponentName arg0) {
            logger.debug(TAG, "onServiceConnected");
            cartFragment.onCheckoutServiceFinished();
            cartService = null;
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((CustomApplication) getApplicationContext()).component().inject(this);

        Intent intent = new Intent(this, CartService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

        setContentView(R.layout.activity_cart);
        cartFragment = (CartFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_cart);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        logger.debug(TAG, "onPause");
        unregisterReceiver(cartBroadCastReceiver);
    }

    @OnClick(R.id.tool_bar_drinks)
    public void onClickDrinks() {
        cartFragment.onClickDrinks();
    }

    @Override
    public void startCheckoutService() {
        logger.debug(TAG, "startCheckoutService");
        Intent intent = new Intent(this, CartService.class);
        startService(intent);
    }

    @Override
    public boolean checkoutServiceRunning() {
        logger.debug(TAG, "checkoutServiceRunning");
        return cartService != null && cartService.isRunning();
    }

    @Override
    protected void onResume() {
        super.onResume();

        logger.debug(TAG, "onResume");
        registerFinishCheckoutReceiver();
    }

    private void registerFinishCheckoutReceiver() {
        cartBroadCastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                cartFragment.onCheckoutServiceFinished();
            }
        };
        registerReceiver(cartBroadCastReceiver, new IntentFilter(CartService.FINISH_CHECKOUT_ACTION));
    }
}
