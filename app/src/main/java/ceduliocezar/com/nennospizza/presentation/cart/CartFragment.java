package ceduliocezar.com.nennospizza.presentation.cart;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ceduliocezar.com.domain.logging.Logger;
import ceduliocezar.com.nennospizza.CustomApplication;
import ceduliocezar.com.nennospizza.R;
import ceduliocezar.com.nennospizza.navigation.Navigator;

/**
 * Fragment that shows current items on cart.
 */
public class CartFragment extends Fragment implements CartContract.View {

    private static final String TAG = "CartFragment";

    @Inject
    CartContract.Presenter presenter;

    @Inject
    Logger logger;

    @Inject
    Navigator navigator;

    @BindView(R.id.cart_item_recycler)
    RecyclerView recyclerView;

    @BindView(R.id.cart_checkout_button)
    Button checkoutButton;

    @BindView(R.id.loading_container)
    View loadingContainer;

    @BindView(R.id.loader)
    AVLoadingIndicatorView avLoadingIndicatorView;

    private CartItemAdapter cartItemAdapter;


    public CartFragment() {
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
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        ButterKnife.bind(this, view);
        initRecyclerView();
        initButton();

        return view;
    }

    private void initButton() {
        // not able to use butterknife onitemclick due usage of relative layout. butter knife messes with ids.
        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickCartCheckout();
            }
        });
    }

    private void initRecyclerView() {
        cartItemAdapter = new CartItemAdapter(getActivity());
        cartItemAdapter.setOnItemClick(new CartItemAdapter.OnItemClick() {

            @Override
            public void onClickDeleteItemFromCart(CartItemModel cartItemModel) {
                logger.debug(TAG, "onClickDeleteItemFromCart: " + cartItemModel);
                presenter.onClickDeleteItemFromCart(cartItemModel);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(cartItemAdapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        logger.debug(TAG, "onViewCreated");
        presenter.setView(this);
        presenter.init();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        logger.debug(TAG, "onDestroyView: ");
        presenter.viewDestroyed();
    }

    private void onClickCartCheckout() {
        logger.debug(TAG, "onClickCartCheckout: ");
        presenter.onClickCheckout();
    }

    @Override
    public void showCart(List<CartItemModel> cartItems) {
        logger.debug(TAG, "showCart: " + cartItems.toString());
        cartItemAdapter.setCartItems(cartItems);
    }

    @Override
    public void navigateToAfterCheckoutScreen() {
        logger.debug(TAG, "showAfterCheckoutMessage: ");
        navigator.navigateToAfterCheckoutScreen(getActivity());
    }

    @Override
    public void showCheckoutValue(double price) {
        logger.debug(TAG, "showCheckoutValue: price:" + price);
        checkoutButton.setText(String.format(getString(R.string.checkout_format), price));
    }

    @Override
    public void showLoadingCartItems() {
        logger.debug(TAG, "showLoadingCartItems");
        showLoader();
    }

    private void showLoader() {
        loadingContainer.setVisibility(View.VISIBLE);
        avLoadingIndicatorView.show();
    }

    @Override
    public void hideLoadingCartItems() {
        logger.debug(TAG, "hideLoadingCartItems");
        hideLoader();
    }

    private void hideLoader() {
        loadingContainer.setVisibility(View.GONE);
        avLoadingIndicatorView.hide();
    }

    @Override
    public void showLoadingCheckout() {
        logger.debug(TAG, "showLoadingCheckout");
        showLoader();
    }

    @Override
    public void showErrorOnCheckout() {
        logger.debug(TAG, "showErrorOnCheckout: ");
        // not defined in UX/UI, lets just show a toast by now.

        Toast.makeText(getActivity(), getString(R.string.something_went_wrong_checkout), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoadingCheckout() {
        logger.debug(TAG, "hideLoadingCheckout");
        hideLoader();
    }

    @Override
    public void showTotalPrice(Double price) {
        logger.debug(TAG, "showTotalPrice: " + price);
        checkoutButton.setText(String.format(getString(R.string.checkout_format), price));
    }
}
