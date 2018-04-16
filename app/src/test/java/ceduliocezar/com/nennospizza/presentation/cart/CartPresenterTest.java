package ceduliocezar.com.nennospizza.presentation.cart;

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

import ceduliocezar.com.domain.CartItem;
import ceduliocezar.com.domain.interactor.cart.GetCartItemById;
import ceduliocezar.com.domain.interactor.cart.GetCartItems;
import ceduliocezar.com.domain.interactor.cart.GetCartTotalPrice;
import ceduliocezar.com.domain.interactor.cart.ProceedCheckout;
import ceduliocezar.com.domain.interactor.cart.RemoveItemFromCart;
import ceduliocezar.com.domain.logging.Logger;
import io.reactivex.observers.DisposableObserver;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * Test suite for {@link CartPresenter}
 */
public class CartPresenterTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @InjectMocks
    private CartPresenter cartPresenter;

    @Mock
    private GetCartItems getCartItems;

    @Mock
    private CartContract.View view;

    @Mock
    private Logger logger;
    @Captor
    private ArgumentCaptor<DisposableObserver<List<CartItem>>> disposableItemsCaptor;

    @Mock
    private CartItem cartItem;

    @Mock
    private CartItemModel cartItemModel;

    @Mock
    private CartItemPresentationMapper cartItemPresentationMapper;

    @Mock
    private Throwable throwable;

    @Mock
    private RemoveItemFromCart removeItemFromCart;

    @Mock
    private GetCartItemById getCartItemById;

    @Captor
    private ArgumentCaptor<DisposableObserver<CartItem>> cartItemDisposableCaptor;

    @Captor
    private ArgumentCaptor<DisposableObserver<Void>> voidDisposableCaptor;

    @Mock
    private ProceedCheckout proceedCheckout;

    @Mock
    private GetCartTotalPrice getCartTotalPrice;

    @Captor
    private ArgumentCaptor<DisposableObserver<Double>> disposableDoubleCaptor;

    @Test
    public void test_setView() {
        assertNull(cartPresenter.view);

        cartPresenter.setView(view);

        assertNotNull(cartPresenter.view);
    }

    @Test
    public void test_initNotSetView() {

        cartPresenter.init();

        verify(logger).warn(anyString(), anyString());
        verify(getCartItems).execute(disposableItemsCaptor.capture(), ArgumentMatchers.<Void>isNull());
        verify(getCartTotalPrice).execute(disposableDoubleCaptor.capture(), ArgumentMatchers.<Void>isNull());
    }

    @Test
    public void test_loadTotalPrice() throws Exception {
        cartPresenter.setView(view);
        cartPresenter.loadTotalPrice();

        verify(getCartTotalPrice).execute(disposableDoubleCaptor.capture(), ArgumentMatchers.<Void>isNull());

        disposableDoubleCaptor.getValue().onNext(10.0d);
        disposableDoubleCaptor.getValue().onComplete();

        verify(view).showTotalPrice(10.0);
    }

    @Test
    public void test_loadCartItems() {
        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(cartItem);
        cartItems.add(cartItem);
        cartItems.add(cartItem);

        List<CartItemModel> cartItemModels = new ArrayList<>();
        cartItemModels.add(cartItemModel);
        cartItemModels.add(cartItemModel);
        cartItemModels.add(cartItemModel);


        when(cartItemPresentationMapper.transform(cartItems)).thenReturn(cartItemModels);

        cartPresenter.setView(view);
        cartPresenter.loadCartItems();

        verify(getCartItems).execute(disposableItemsCaptor.capture(), ArgumentMatchers.<Void>isNull());
        disposableItemsCaptor.getValue().onNext(cartItems);
        disposableItemsCaptor.getValue().onComplete();

        verify(view).showLoadingCartItems();
        verify(view).hideLoadingCartItems();
        verify(view).showCart(cartItemModels);
    }

    @Test
    public void test_loadCartItemsNoViewAttached() {
        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(cartItem);
        cartItems.add(cartItem);
        cartItems.add(cartItem);

        cartPresenter.loadCartItems();

        verify(getCartItems).execute(disposableItemsCaptor.capture(), ArgumentMatchers.<Void>isNull());
        disposableItemsCaptor.getValue().onNext(cartItems);
        disposableItemsCaptor.getValue().onComplete();
    }

    @Test
    public void test_loadCartItemsError() {
        cartPresenter.setView(view);
        cartPresenter.loadCartItems();

        verify(getCartItems).execute(disposableItemsCaptor.capture(), ArgumentMatchers.<Void>isNull());
        disposableItemsCaptor.getValue().onError(throwable);
        disposableItemsCaptor.getValue().onComplete();

        verify(view).showLoadingCartItems();
        verify(view).hideLoadingCartItems();
    }

    @Test
    public void test_viewDestroyed() {

        cartPresenter.setView(view);
        assertNotNull(cartPresenter.view);
        cartPresenter.viewDestroyed();

        assertNull(cartPresenter.view);
    }

    @Test
    public void test_onClickDeleteItemFromCart() {

        when(cartItemModel.getId()).thenReturn("id");

        cartPresenter.setView(view);
        cartPresenter.onClickDeleteItemFromCart(cartItemModel);

        verify(getCartItemById).execute(cartItemDisposableCaptor.capture(), eq("id"));
    }

    @Test
    public void test_onClickDeleteItemFromCartComplete() {

        when(cartItemModel.getId()).thenReturn("id");

        cartPresenter.setView(view);
        cartPresenter.onClickDeleteItemFromCart(cartItemModel);

        verify(getCartItemById).execute(cartItemDisposableCaptor.capture(), eq("id"));

        cartItemDisposableCaptor.getValue().onNext(cartItem);
        cartItemDisposableCaptor.getValue().onComplete();

        verify(removeItemFromCart).execute(voidDisposableCaptor.capture(), any(CartItem.class));
    }

    @Test
    public void test_onClickDeleteItemFromCartError() {

        when(cartItemModel.getId()).thenReturn("id");

        cartPresenter.setView(view);
        cartPresenter.onClickDeleteItemFromCart(cartItemModel);

        verify(getCartItemById).execute(cartItemDisposableCaptor.capture(), eq("id"));

        cartItemDisposableCaptor.getValue().onError(throwable);
        cartItemDisposableCaptor.getValue().onComplete();

        verifyZeroInteractions(removeItemFromCart);
        verifyZeroInteractions(view);
    }

    @Test
    public void test_removeItemFromCart() {

        cartPresenter.setView(view);
        cartPresenter.removeItemFromCart(cartItem);

        verify(removeItemFromCart).execute(voidDisposableCaptor.capture(), eq(cartItem));

        voidDisposableCaptor.getValue().onNext(null);
        voidDisposableCaptor.getValue().onComplete();

        verify(getCartItems).execute(disposableItemsCaptor.capture(), ArgumentMatchers.<Void>isNull());
    }

    @Test
    public void test_removeItemFromCartError() {

        cartPresenter.setView(view);
        cartPresenter.removeItemFromCart(cartItem);

        verify(removeItemFromCart).execute(voidDisposableCaptor.capture(), eq(cartItem));

        voidDisposableCaptor.getValue().onError(null);
        voidDisposableCaptor.getValue().onComplete();

        verify(getCartItems).execute(disposableItemsCaptor.capture(), ArgumentMatchers.<Void>isNull());
    }

    @Test
    public void test_onClickCheckout() {

        cartPresenter.setView(view);
        cartPresenter.onClickCheckout();

        verify(view).showLoadingCheckout();
        verify(proceedCheckout).execute(voidDisposableCaptor.capture(), ArgumentMatchers.<Void>isNull());
    }

    @Test
    public void test_onClickCheckoutSuccess() {

        cartPresenter.setView(view);
        cartPresenter.onClickCheckout();

        verify(view).showLoadingCheckout();
        verify(proceedCheckout).execute(voidDisposableCaptor.capture(), ArgumentMatchers.<Void>isNull());

        voidDisposableCaptor.getValue().onNext(null);
        voidDisposableCaptor.getValue().onComplete();

        verify(view).navigateToAfterCheckoutScreen();
        verify(view).hideLoadingCheckout();
    }

    @Test
    public void test_onClickCheckoutError() {

        cartPresenter.setView(view);
        cartPresenter.onClickCheckout();

        verify(view).showLoadingCheckout();
        verify(proceedCheckout).execute(voidDisposableCaptor.capture(), ArgumentMatchers.<Void>isNull());

        voidDisposableCaptor.getValue().onError(throwable);

        verify(view).showErrorOnCheckout();
        verify(view).hideLoadingCheckout();
    }
}