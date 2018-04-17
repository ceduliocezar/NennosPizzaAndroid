package ceduliocezar.com.nennospizza.presentation.drink;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import ceduliocezar.com.domain.Drink;
import ceduliocezar.com.domain.interactor.cart.AddDrinkToCart;
import ceduliocezar.com.domain.interactor.drink.GetDrinkList;
import ceduliocezar.com.domain.logging.Logger;
import io.reactivex.observers.DisposableObserver;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test suite for {@link DrinksPresenter}
 */
public class DrinksPresenterTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @InjectMocks
    private DrinksPresenter presenter;

    @Mock
    private DrinksContract.View view;

    @Mock
    private Logger logger;

    @Mock
    private Throwable throwable;

    @Mock
    private GetDrinkList getDrinkList;

    @Mock
    private AddDrinkToCart addDrinkToCart;

    @Captor
    private ArgumentCaptor<DisposableObserver<List<Drink>>> disposableDrinksCaptor;

    @Captor
    private ArgumentCaptor<DisposableObserver<Void>> disposableVoidCaptor;

    @Mock
    private Drink drink;

    @Mock
    private DrinkPresentationMapper drinkPresentationMapper;

    @Mock
    private DrinkModel drinkModel;

    @Test
    public void test_setView() {
        assertNull(presenter.view);

        presenter.setView(view);

        assertNotNull(presenter.view);
    }

    @Test
    public void test_initNotSetView() {

        presenter.init();

        verify(logger).warn(anyString(), anyString());
        verify(getDrinkList).execute(disposableDrinksCaptor.capture(), ArgumentMatchers.<Void>isNull());
    }

    @Test
    public void viewDestroyed() {

        presenter.setView(view);
        presenter.viewDestroyed();
        assertNull(presenter.view);
    }

    @Test
    public void test_loadDrinks() {
        List<Drink> drinks = new ArrayList<>();
        drinks.add(drink);
        drinks.add(drink);
        drinks.add(drink);
        List<DrinkModel> drinksModel = new ArrayList<>();
        drinksModel.add(drinkModel);
        drinksModel.add(drinkModel);
        drinksModel.add(drinkModel);
        when(drinkPresentationMapper.transform(drinks)).thenReturn(drinksModel);

        presenter.setView(view);
        presenter.loadDrinks();

        verify(getDrinkList).execute(disposableDrinksCaptor.capture(), ArgumentMatchers.<Void>isNull());
        disposableDrinksCaptor.getValue().onNext(drinks);
        disposableDrinksCaptor.getValue().onComplete();

        verify(view).showDrinks(drinksModel);
    }

    @Test
    public void test_loadDrinksError() {

        presenter.setView(view);
        presenter.loadDrinks();

        verify(getDrinkList).execute(disposableDrinksCaptor.capture(), ArgumentMatchers.<Void>isNull());
        disposableDrinksCaptor.getValue().onError(throwable);
    }


    @Test
    public void test_onClickAddDrink() {

        when(drinkPresentationMapper.transform(drinkModel)).thenReturn(drink);

        presenter.setView(view);
        presenter.onClickAddDrink(drinkModel);

        verify(addDrinkToCart).execute(disposableVoidCaptor.capture(), eq(drink));


        disposableVoidCaptor.getValue().onComplete();

        verify(view).showNotificationDrinkAdded();
    }

    @Test
    public void test_onClickAddDrinkError() {

        when(drinkPresentationMapper.transform(drinkModel)).thenReturn(drink);

        presenter.setView(view);
        presenter.onClickAddDrink(drinkModel);

        verify(addDrinkToCart).execute(disposableVoidCaptor.capture(), eq(drink));


        disposableVoidCaptor.getValue().onError(throwable);

        verify(view).showErrorMessageOnAddDrink();
    }
}