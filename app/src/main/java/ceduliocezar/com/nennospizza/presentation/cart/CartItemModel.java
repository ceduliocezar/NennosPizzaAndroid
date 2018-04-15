package ceduliocezar.com.nennospizza.presentation.cart;

import ceduliocezar.com.domain.CartItemType;

/**
 * Class that represents a Cart Item.
 */
public class CartItemModel {

    private String id;
    private String title;
    private double price;
    private CartItemType cartItemType;

    public CartItemModel(String id, String title, double price, CartItemType cartItemType) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.cartItemType = cartItemType;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "CartItemModel{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", cartItemType=" + cartItemType +
                '}';
    }
}
