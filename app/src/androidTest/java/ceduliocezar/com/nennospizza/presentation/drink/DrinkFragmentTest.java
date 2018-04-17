package ceduliocezar.com.nennospizza.presentation.drink;

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

import ceduliocezar.com.nennospizza.MockApplication;
import ceduliocezar.com.nennospizza.R;
import ceduliocezar.com.nennospizza.di.TestComponent;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static org.hamcrest.CoreMatchers.not;
import static org.mockito.Mockito.clearInvocations;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
public class DrinkFragmentTest {

    @Rule
    public ActivityTestRule<DrinkActivity> activityTestRule = new ActivityTestRule<>(
            DrinkActivity.class,
            true,
            true);

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Inject
    @SuppressWarnings("WeakerAccess")
    DrinksContract.Presenter presenter;

    private ArgumentCaptor<DrinksContract.View> viewArgumentCaptor;

    @Before
    public void setUp() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        MockApplication application = (MockApplication) instrumentation.getTargetContext().getApplicationContext();
        TestComponent component = (TestComponent) application.component();

        component.inject(this);

        viewArgumentCaptor = ArgumentCaptor.forClass((DrinksContract.View.class));
    }

    @After
    public void tearDown() {
        clearInvocations(presenter);
    }

    @Test
    public void test_showDrinks() throws Throwable {


        final List<DrinkModel> drinks = getDrinks(30);

        verify(presenter).setView(viewArgumentCaptor.capture());
        verify(presenter, times(1)).init();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                viewArgumentCaptor.getValue().showDrinks(drinks);
            }
        });

        onView(withText("drink1")).check(matches(isDisplayed()));

        onView(withId(R.id.drink_recycler)).perform(RecyclerViewActions.scrollToPosition(29));
    }

    @Test
    public void test_clickAddDrink() throws Throwable {


        final List<DrinkModel> drinks = getDrinks(1);

        verify(presenter).setView(viewArgumentCaptor.capture());
        verify(presenter, times(1)).init();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                viewArgumentCaptor.getValue().showDrinks(drinks);
            }
        });

        onView(withId(R.id.drink_add_iv)).perform(click());
    }

    @Test
    public void test_showLoadingDrinks() throws Throwable {


        verify(presenter).setView(viewArgumentCaptor.capture());
        verify(presenter, times(1)).init();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                viewArgumentCaptor.getValue().showLoadingDrinks();
            }
        });

        onView(withId(R.id.loader)).check(matches(isDisplayed()));
    }

    @Test
    public void test_hideLoadingDrinks() throws Throwable {


        verify(presenter).setView(viewArgumentCaptor.capture());
        verify(presenter, times(1)).init();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                viewArgumentCaptor.getValue().showLoadingDrinks();
                viewArgumentCaptor.getValue().hideLoadingDrinks();
            }
        });

        onView(withId(R.id.loader)).check(matches(not(isDisplayed())));
    }

    @NonNull
    private List<DrinkModel> getDrinks(int total) {
        List<DrinkModel> drinkModels = new ArrayList<>();


        for (int i = 0; i < total; i++) {
            drinkModels.add(new DrinkModel(i, "drink" + i, 10 * i));
        }
        return drinkModels;
    }
}