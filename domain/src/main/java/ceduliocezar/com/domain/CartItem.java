package ceduliocezar.com.domain;

public class CartItem {

    private String id;
    private String title;
    private double price;
    private CartItemType cartItemType;

    public CartItem(String id, String title, double price, CartItemType cartItemType) {
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

    public CartItemType getCartItemType() {
        return cartItemType;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", cartItemType=" + cartItemType +
                '}';
    }
}
