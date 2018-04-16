package ceduliocezar.com.nennospizza.presentation.checkout;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ceduliocezar.com.nennospizza.CustomApplication;
import ceduliocezar.com.nennospizza.R;

public class AfterCheckoutActivity extends AppCompatActivity {

    @BindView(R.id.thanks_tv)
    TextView thanksTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_checkout);

        ((CustomApplication) getApplication()).component().inject(this);
        ButterKnife.bind(this);

        thanksTv.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-LightItalic.ttf"));
    }

    @OnClick(R.id.back_to_home_bt)
    public void onClickBackToHome() {
        finish();
    }
}
