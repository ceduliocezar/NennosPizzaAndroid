package ceduliocezar.com.nennospizza.presentation.pizza.list;

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

import ceduliocezar.com.domain.Pizza;
import ceduliocezar.com.domain.interactor.cart.AddPizzaToCart;
import ceduliocezar.com.domain.interactor.cart.GetNumOfItemsOnCart;
import ceduliocezar.com.domain.interactor.pizza.GetPizzaByName;
import ceduliocezar.com.domain.interactor.pizza.GetPizzaMenu;
import ceduliocezar.com.domain.logging.Logger;
import ceduliocezar.com.nennospizza.presentation.pizza.PizzaPresentationMapper;
import io.reactivex.observers.DisposableObserver;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * Test suite for {@link PizzaListPresenter}
 */
public class PizzaListPresenterTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @InjectMocks
    private PizzaListPresenter pizzaListPresenter;

    @Mock
    @SuppressWarnings("unused")
    private Logger logger;

    @Mock
    private PizzaListContract.View view;

    @Mock
    private PizzaModel pizzaModel;

    @Mock
    private AddPizzaToCart addPizzaToCart;

    @Captor
    private ArgumentCaptor<DisposableObserver<Integer>> disposableAddPizzaCaptor;

    @Captor
    private ArgumentCaptor<DisposableObserver<Pizza>> disposableGetPizzaByNameCaptor;

    @Mock
    private Throwable throwable;

    @Mock
    private GetPizzaMenu getPizzaMenu;

    @Captor
    private ArgumentCaptor<DisposableObserver<List<Pizza>>> disposableListCaptor;

    @Mock
    private Pizza pizza;

    @Mock
    private PizzaPresentationMapper pizzaPresentationMapper;

    @Mock
    private GetPizzaByName getPizzaByName;

    @Mock
    private GetNumOfItemsOnCart getNumOfItemsOnCart;

    @Captor
    private ArgumentCaptor<DisposableObserver<Integer>> disposableIntCaptor;

    @Test
    public void test_setView() {

        assertNull(pizzaListPresenter.view);
        pizzaListPresenter.setView(view);

        assertNotNull(pizzaListPresenter.view);
    }

    @Test
    public void test_viewDestroyed() {
        pizzaListPresenter.viewDestroyed();

        assertNull(pizzaListPresenter.view);
    }

    @Test
    public void test_userSelectedPizza() {
        pizzaListPresenter.setView(view);
        pizzaListPresenter.userSelectedPizza(pizzaModel);

        verify(view).showDetailPizzaScreen(pizzaModel);
    }

    @Test
    public void test_userSelectedAddPizzaToCart() {

        when(pizzaModel.getName()).thenReturn("pizza");
        pizzaListPresenter.setView(view);
        pizzaListPresenter.userSelectedAddPizzaToCart(pizzaModel);

        verify(getPizzaByName).execute(any(DisposableObserver.class), eq("pizza"));
    }

    @Test
    public void test_addPizzaToCart() {

        when(pizzaModel.getName()).thenReturn("pizzaName");
        pizzaListPresenter.setView(view);
        pizzaListPresenter.addPizzaToCart(pizzaModel);

        verify(getPizzaByName).execute(disposableGetPizzaByNameCaptor.capture(), anyString());
        disposableGetPizzaByNameCaptor.getValue().onNext(pizza);
        disposableGetPizzaByNameCaptor.getValue().onComplete();
        verify(addPizzaToCart).execute(disposableAddPizzaCaptor.capture(), eq(pizza));

        disposableAddPizzaCaptor.getValue().onNext(1);
        verify(view).showCartNotification(1);
    }

    @Test
    public void test_addPizzaToCartNoItems() {

        when(pizzaModel.getName()).thenReturn("pizzaName");

        pizzaListPresenter.setView(view);
        pizzaListPresenter.addPizzaToCart(pizzaModel);

        verify(getPizzaByName).execute(disposableGetPizzaByNameCaptor.capture(), anyString());
        disposableGetPizzaByNameCaptor.getValue().onNext(pizza);
        disposableGetPizzaByNameCaptor.getValue().onComplete();
        verify(addPizzaToCart).execute(disposableAddPizzaCaptor.capture(), eq(pizza));

        disposableAddPizzaCaptor.getValue().onNext(0);
        disposableAddPizzaCaptor.getValue().onComplete();
        verify(view).hideCartNotification();
    }

    @Test
    public void test_addPizzaToCartError() {

        when(pizzaModel.getName()).thenReturn("pizzaName");

        pizzaListPresenter.setView(view);
        pizzaListPresenter.addPizzaToCart(pizzaModel);

        verify(getPizzaByName).execute(disposableGetPizzaByNameCaptor.capture(), anyString());

        disposableGetPizzaByNameCaptor.getValue().onError(throwable);
        disposableGetPizzaByNameCaptor.getValue().onComplete();
        verifyZeroInteractions(view);
    }

    @Test
    public void test_initSetViewNotCalled() {

        pizzaListPresenter.init();

        verify(logger).warn(any(String.class), any(String.class));
    }

    @Test
    public void test_init() {

        pizzaListPresenter.setView(view);
        pizzaListPresenter.init();

        verify(getPizzaMenu).execute(any(DisposableObserver.class), ArgumentMatchers.<Void>isNull());
    }

    @Test
    public void test_loadPizzaList() {

        List<Pizza> pizzas = new ArrayList<>();
        pizzas.add(pizza);
        pizzas.add(pizza);
        pizzas.add(pizza);

        List<PizzaModel> pizzaModels = new ArrayList<>();
        pizzaModels.add(pizzaModel);
        pizzaModels.add(pizzaModel);
        pizzaModels.add(pizzaModel);

        when(pizzaPresentationMapper.transform(pizzas)).thenReturn(pizzaModels);

        pizzaListPresenter.setView(view);
        pizzaListPresenter.loadPizzaList();

        verify(getPizzaMenu).execute(disposableListCaptor.capture(), ArgumentMatchers.<Void>isNull());


        disposableListCaptor.getValue().onNext(pizzas);
        disposableListCaptor.getValue().onComplete();

        verify(view).showPizzas(pizzaModels);
    }

    @Test
    public void test_viewResumed() throws Exception {
        pizzaListPresenter.viewResumed();

        verify(getNumOfItemsOnCart).execute(disposableIntCaptor.capture(), ArgumentMatchers.<Void>isNull());
    }

    @Test
    public void test_loadNumItemsOnCart() throws Exception {
        pizzaListPresenter.setView(view);
        pizzaListPresenter.loadNumItemsOnCart();

        verify(getNumOfItemsOnCart).execute(disposableIntCaptor.capture(), ArgumentMatchers.<Void>isNull());

        disposableIntCaptor.getValue().onNext(10);
        disposableIntCaptor.getValue().onComplete();

        verify(view).showCartNotification(10);
    }

    @Test
    public void test_loadNumItemsOnCartZero() throws Exception {
        pizzaListPresenter.setView(view);
        pizzaListPresenter.loadNumItemsOnCart();

        verify(getNumOfItemsOnCart).execute(disposableIntCaptor.capture(), ArgumentMatchers.<Void>isNull());

        disposableIntCaptor.getValue().onNext(0);
        disposableIntCaptor.getValue().onComplete();

        verify(view).hideCartNotification();
    }

    @Test
    public void test_loadPizzaListError() {


        pizzaListPresenter.setView(view);
        pizzaListPresenter.loadPizzaList();

        verify(getPizzaMenu).execute(disposableListCaptor.capture(), ArgumentMatchers.<Void>isNull());


        disposableListCaptor.getValue().onError(throwable);
        disposableListCaptor.getValue().onComplete();

        verify(logger).error(any(String.class), eq(throwable));
    }
}