package ceduliocezar.com.nennospizza.presentation.pizza.list;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ceduliocezar.com.domain.logging.Logger;
import ceduliocezar.com.nennospizza.CustomApplication;
import ceduliocezar.com.nennospizza.R;
import ceduliocezar.com.nennospizza.navigation.Navigator;
import cn.nekocode.badge.BadgeDrawable;

/**
 * Fragment that shows a list of Pizzas.
 * Created by cedulio.silva on 4/12/2018.
 */
public class PizzaListFragment extends Fragment implements PizzaListContract.View {

    private static final String TAG = "PizzaListFragment";

    @Inject
    PizzaListContract.Presenter presenter;

    @Inject
    Logger logger;

    @Inject
    Navigator navigator;

    @BindView(R.id.pizza_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.pizza_loader)
    AVLoadingIndicatorView loaderView;

    private PizzasAdapter pizzasAdapter;

    @Nullable
    private Callback callback;

    public PizzaListFragment() {
        // mandatory android constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((CustomApplication) getActivity().getApplication()).component().inject(this);
        logger.debug(TAG, "onCreate");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        logger.debug(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.fragment_pizza_list, container, false);
        ButterKnife.bind(this, view);
        initRecyclerView();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.callback = (Callback) context;
    }

    private void initRecyclerView() {
        pizzasAdapter = new PizzasAdapter(getActivity());
        pizzasAdapter.setOnItemClick(new PizzasAdapter.OnItemClick() {
            @Override
            public void onClickAddToCart(PizzaModel pizzaModel) {
                logger.debug(TAG, "onClickAddToCart:" + pizzaModel.toString());
                presenter.userSelectedAddPizzaToCart(pizzaModel);
            }

            @Override
            public void onSelectedPizza(PizzaModel pizzaModel) {
                logger.debug(TAG, "onSelectedPizza:" + pizzaModel.toString());
                presenter.userSelectedPizza(pizzaModel);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(pizzasAdapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        logger.debug(TAG, "onViewCreated");
        presenter.setView(this);
        presenter.init();

    }

    @Override
    public void showPizzas(List<PizzaModel> pizzas) {
        logger.debug(TAG, "showPizzas: " + pizzas.toString());
        pizzasAdapter.setPizzas(pizzas);
    }

    @Override
    public void showCreateCustomPizzaScreen() {
        logger.debug(TAG, "showCreateCustomPizzaScreen: ");
        navigator.navigateToCreateCustomPizza(getActivity());
    }

    @Override
    public void showDetailPizzaScreen(PizzaModel pizzaModel) {
        logger.debug(TAG, "showDetailPizzaScreen: ");
        navigator.navigateToPizzaDetailScreen(getActivity(), pizzaModel);

    }

    @Override
    public void showCartNotification(int itemsInCart) {
        logger.debug(TAG, "showCartNotification: " + itemsInCart);

        if (callback != null) {
            final BadgeDrawable drawable =
                    new BadgeDrawable.Builder()
                            .type(BadgeDrawable.TYPE_NUMBER)
                            .number(itemsInCart)
                            .badgeColor(ContextCompat.getColor(getActivity(), R.color.colorAccent))
                            .build();


            callback.setCartNotificationDrawable(drawable);
        } else {
            logger.warn(TAG, "showCartNotification: callback not available");
        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        logger.debug(TAG, "onDestroyView: ");
        presenter.viewDestroyed();
    }

    @Override
    public void hideCartNotification() {
        logger.debug(TAG, "hideCartNotification: ");
        if (callback != null) {
            callback.setCartNotificationDrawable(null);
        } else {
            logger.warn(TAG, "hideCartNotification: callback not available");
        }

    }

    @Override
    public void showPizzaLoader() {
        logger.debug(TAG, "showPizzaLoader");
        loaderView.setVisibility(View.VISIBLE);
        loaderView.show();
    }

    @Override
    public void hidePizzaLoader() {
        logger.debug(TAG, "hidePizzaLoader");
        loaderView.setVisibility(View.GONE);
        loaderView.hide();
    }

    public void onClickAddCustomPizza() {
        logger.debug(TAG, "onClickAddCustomPizza: ");
        navigator.navigateToCreateCustomPizza(getActivity());
    }

    public void onClickCart() {
        navigator.navigateToCartScreen(getActivity());
    }

    public interface Callback {
        void setCartNotificationDrawable(@Nullable Drawable drawable);
    }
}
