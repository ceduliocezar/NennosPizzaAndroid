package ceduliocezar.com.nennospizza.presentation.cart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.ButterKnife;
import butterknife.OnClick;
import ceduliocezar.com.nennospizza.R;

public class CartActivity extends AppCompatActivity {

    private CartFragment cartFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartFragment = (CartFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_cart);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tool_bar_drinks)
    public void onClickDrinks() {
        cartFragment.onClickDrinks();
    }
}
