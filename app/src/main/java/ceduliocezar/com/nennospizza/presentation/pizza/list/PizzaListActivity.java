package ceduliocezar.com.nennospizza.presentation.pizza.list;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import ceduliocezar.com.nennospizza.R;

public class PizzaListActivity extends AppCompatActivity implements PizzaListFragment.Callback {

    private PizzaListFragment pizzaListFragment;
    private ImageView badgeNotificationImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_list);


        pizzaListFragment = (PizzaListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_pizza_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        badgeNotificationImageView = toolbar.findViewById(R.id.badge);

        ImageView fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pizzaListFragment.onClickAddCustomPizza();
            }
        });

        toolbar.findViewById(R.id.tool_bar_cart_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pizzaListFragment.onClickCart();
            }
        });
    }

    @Override
    public void setCartNotificationDrawable(Drawable drawable) {
        badgeNotificationImageView.setImageDrawable(drawable);
    }
}
