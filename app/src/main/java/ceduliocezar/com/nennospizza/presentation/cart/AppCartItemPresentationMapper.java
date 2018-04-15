package ceduliocezar.com.nennospizza.presentation.cart;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ceduliocezar.com.domain.CartItem;

public class AppCartItemPresentationMapper implements CartItemPresentationMapper {

    @Inject
    public AppCartItemPresentationMapper() {
        //mandatory di constructor
    }

    @Override
    public List<CartItemModel> transform(List<CartItem> cartItems) {

        List<CartItemModel> cartItemModels = new ArrayList<>();

        for (CartItem cartItem : cartItems) {
            cartItemModels.add(new CartItemModel(cartItem.getId(), cartItem.getTitle(), cartItem.getPrice(), cartItem.getCartItemType()));
        }

        return cartItemModels;
    }
}
