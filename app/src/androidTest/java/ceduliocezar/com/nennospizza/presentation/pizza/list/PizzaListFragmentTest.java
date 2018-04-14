package ceduliocezar.com.nennospizza.presentation.pizza.list;

import android.app.Instrumentation;
import android.net.Uri;
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
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import ceduliocezar.com.nennospizza.MockApplication;
import ceduliocezar.com.nennospizza.R;
import ceduliocezar.com.nennospizza.di.TestComponent;
import ceduliocezar.com.nennospizza.navigation.Navigator;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static ceduliocezar.com.nennospizza.matchers.ImageViewHasDrawableMatcher.hasDrawable;
import static ceduliocezar.com.nennospizza.matchers.ImageViewNullDrawableMatcher.nullDrawable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.clearInvocations;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
public class PizzaListFragmentTest {

    @Rule
    public ActivityTestRule<PizzaListActivity> activityTestRule = new ActivityTestRule<>(
            PizzaListActivity.class,
            true,
            true
    );

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Inject
    PizzaListContract.Presenter presenter;

    @Inject
    Navigator navigator;

    private ArgumentCaptor<PizzaListContract.View> viewArgumentCaptor;


    @Before
    public void setUp() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        MockApplication application = (MockApplication) instrumentation.getTargetContext().getApplicationContext();
        TestComponent component = (TestComponent) application.component();

        component.inject(this);

        viewArgumentCaptor = ArgumentCaptor.forClass((PizzaListContract.View.class));
    }

    @After
    public void tearDown() {
        clearInvocations(presenter);
    }

    @Test
    public void test_showPizzas() {

        final List<PizzaModel> pizzas = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            pizzas.add(new PizzaModel("pizza" + i,
                    "Pizza number " + i,
                    Arrays.asList("Ingredient1", "Ingredient2"),
                    100,
                    Uri.parse("file:///android_asset/place_holder.png").toString()));
        }


        verify(presenter).setView(viewArgumentCaptor.capture());
        verify(presenter, times(1)).init();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                viewArgumentCaptor.getValue().showPizzas(pizzas);
            }
        });

        onView(withText("Pizza number 0")).check(matches(isDisplayed()));

        onView(withId(R.id.pizza_recycler_view)).perform(RecyclerViewActions.scrollToPosition(9));
    }

    @Test
    public void test_navigateToCreateCustomPizzaScreen() {

        onView(withId(R.id.fab)).perform(click());
        verify(navigator, times(1)).navigateToCreateCustomPizza(activityTestRule.getActivity());

    }

    @Test
    public void test_navigateToCartScreen() {

        onView(withId(R.id.tool_bar_cart_image)).perform(click());
        verify(navigator, times(1)).navigateToCartScreen(activityTestRule.getActivity());

    }

    @Test
    public void test_selectPizza() {

        onView(withId(R.id.pizza_recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        verify(presenter).userSelectedPizza(any(PizzaModel.class));

    }

    @Test
    public void test_addPizzaToCart() {


        final List<PizzaModel> pizzas = new ArrayList<>();

        for (int i = 0; i < 1; i++) {
            pizzas.add(new PizzaModel("pizza" + i,
                    "Pizza number " + i,
                    Arrays.asList("Ingredient1", "Ingredient2"),
                    100,
                    Uri.parse("file:///android_asset/place_holder.png").toString()));
        }


        verify(presenter).setView(viewArgumentCaptor.capture());

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                viewArgumentCaptor.getValue().showPizzas(pizzas);
            }
        });


        onView(withId(R.id.add_to_cart_view)).perform(click());

        onView(withId(R.id.tool_bar_cart_image)).check(matches(isDisplayed()));

        verify(presenter).userSelectedAddPizzaToCart(any(PizzaModel.class));

    }

    @Test
    public void test_showCreateCustomPizzaScreen() {

        verify(presenter).setView(viewArgumentCaptor.capture());

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                viewArgumentCaptor.getValue().showCreateCustomPizzaScreen();
            }
        });

        onView(withId(R.id.pizza_recycler_view)).check(matches(isDisplayed())); // wait ui thread looper
        verify(navigator).navigateToCreateCustomPizza(activityTestRule.getActivity());
    }

    @Test
    public void test_showDetailPizzaScreen() {

        verify(presenter).setView(viewArgumentCaptor.capture());

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                viewArgumentCaptor.getValue().showDetailPizzaScreen();
            }
        });

        onView(withId(R.id.pizza_recycler_view)).check(matches(isDisplayed())); // wait ui thread looper
        verify(navigator).navigateToPizzaDetailScreen(activityTestRule.getActivity());
    }

    @Test
    public void test_showItemsInCart() {

        verify(presenter).setView(viewArgumentCaptor.capture());

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                viewArgumentCaptor.getValue().showCartNotification(10);
            }
        });

        onView(withId(R.id.badge)).check(matches(hasDrawable()));
    }

    @Test
    public void test_showItemsNoItemInCart() {

        verify(presenter).setView(viewArgumentCaptor.capture());

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                viewArgumentCaptor.getValue().hideCartNotification();
            }
        });

        onView(withId(R.id.badge)).check(matches(nullDrawable()));
    }

    private void runOnUiThread(Runnable runnable) {
        activityTestRule.getActivity().runOnUiThread(runnable);
    }
}