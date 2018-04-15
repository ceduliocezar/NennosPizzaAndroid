package ceduliocezar.com.nennospizza.presentation.cart;


import android.app.Instrumentation;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ceduliocezar.com.domain.CartItemType;
import ceduliocezar.com.nennospizza.MockApplication;
import ceduliocezar.com.nennospizza.R;
import ceduliocezar.com.nennospizza.di.TestComponent;
import ceduliocezar.com.nennospizza.navigation.Navigator;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.clearInvocations;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
public class CartFragmentTest {

    @Rule
    public ActivityTestRule<CartActivity> activityTestRule = new ActivityTestRule<>(
            CartActivity.class,
            true,
            true);

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Inject
    @SuppressWarnings("WeakerAccess")
    CartContract.Presenter presenter;

    @Inject
    @SuppressWarnings("WeakerAccess")
    Navigator navigator;

    @Inject
    @SuppressWarnings("WeakerAccess")
    CartItemModel cartItemModel;

    private ArgumentCaptor<CartContract.View> viewArgumentCaptor;

    @Before
    public void setUp() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        MockApplication application = (MockApplication) instrumentation.getTargetContext().getApplicationContext();
        TestComponent component = (TestComponent) application.component();

        component.inject(this);

        viewArgumentCaptor = ArgumentCaptor.forClass((CartContract.View.class));
    }

    @After
    public void tearDown() {
        clearInvocations(presenter);
        clearInvocations(navigator);
        clearInvocations(cartItemModel);
    }

    @Test
    public void test_showCartItem() throws Throwable {


        final List<CartItemModel> cartItems = getCartItemModels(30);

        verify(presenter).setView(viewArgumentCaptor.capture());
        verify(presenter, times(1)).init();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                viewArgumentCaptor.getValue().showCart(cartItems);
            }
        });

        onView(withText("Cart Item 0")).check(matches(isDisplayed()));

        onView(withId(R.id.cart_item_recycler)).perform(RecyclerViewActions.scrollToPosition(9));
    }

    @Test
    public void test_onClickRemoveItem() throws Throwable {


        final List<CartItemModel> cartItems = getCartItemModels(1);

        verify(presenter).setView(viewArgumentCaptor.capture());
        verify(presenter, times(1)).init();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                viewArgumentCaptor.getValue().showCart(cartItems);
            }
        });

        onView(withId(R.id.cart_item_delete_iv)).perform(click());


        verify(presenter, times(1)).onClickDeleteItemFromCart(any(CartItemModel.class));
    }

    @Test
    public void test_onClickCheckout() throws Throwable {


        final List<CartItemModel> cartItems = getCartItemModels(30);

        verify(presenter).setView(viewArgumentCaptor.capture());
        verify(presenter, times(1)).init();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                viewArgumentCaptor.getValue().showCart(cartItems);
            }
        });

        onView(withId(R.id.cart_checkout_button)).perform(click());


        verify(presenter).onClickCheckout();
    }

    @Test
    public void test_showCheckoutTotalValue() throws Throwable {

        verify(presenter).setView(viewArgumentCaptor.capture());
        verify(presenter, times(1)).init();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                viewArgumentCaptor.getValue().showCheckoutValue(17.0d);
            }
        });

        String formattedString = String.format(getActivity().getString(R.string.checkout_format), 17.0d);
        onView(withText(formattedString)).check(matches(isDisplayed()));

    }

    @Test
    public void test_showLoadingCartItem() throws Throwable {

        verify(presenter).setView(viewArgumentCaptor.capture());
        verify(presenter, times(1)).init();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                viewArgumentCaptor.getValue().showLoadingCartItems();
            }
        });

        onView(withId(R.id.loading_container)).check(matches(isDisplayed()));
    }

    @Test
    public void test_hideLoadingCartItems() throws Throwable {

        verify(presenter).setView(viewArgumentCaptor.capture());
        verify(presenter, times(1)).init();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                viewArgumentCaptor.getValue().hideLoadingCartItems();
            }
        });

        onView(withId(R.id.loading_container)).check(matches(not(isDisplayed())));
    }

    @Test
    public void test_showErrorOnCheckout() throws Throwable {
        verify(presenter).setView(viewArgumentCaptor.capture());
        verify(presenter, times(1)).init();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                viewArgumentCaptor.getValue().showErrorOnCheckout();
            }
        });

        onView(withText(R.string.something_went_wrong_checkout))
                .inRoot(withDecorView(not(is(getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void test_showLoadingCheckout() throws Throwable {

        verify(presenter).setView(viewArgumentCaptor.capture());
        verify(presenter, times(1)).init();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                viewArgumentCaptor.getValue().showLoadingCheckout();
            }
        });

        onView(withId(R.id.loading_container)).check(matches(isDisplayed()));
    }

    @Test
    public void test_hideLoadingCheckout() throws Throwable {

        verify(presenter).setView(viewArgumentCaptor.capture());
        verify(presenter, times(1)).init();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                viewArgumentCaptor.getValue().hideLoadingCheckout();
            }
        });

        onView(withId(R.id.loading_container)).check(matches(not(isDisplayed())));
    }

    private CartActivity getActivity() {
        return activityTestRule.getActivity();
    }

    @NonNull
    private List<CartItemModel> getCartItemModels(int total) {
        List<CartItemModel> cartItems = new ArrayList<>();


        for (int i = 0; i < total; i++) {
            cartItems.add(new CartItemModel("id" + i, "Cart Item " + i, 10 * i, CartItemType.PIZZA));
        }
        return cartItems;
    }

    private void runOnUiThread(Runnable runnable) throws Throwable {
        activityTestRule.runOnUiThread(runnable);
    }
}