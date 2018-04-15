package ceduliocezar.com.nennospizza.presentation.cart;

import java.util.List;

import ceduliocezar.com.domain.CartItem;

public interface CartItemPresentationMapper {

    List<CartItemModel> transform(List<CartItem> cartItems);
}
