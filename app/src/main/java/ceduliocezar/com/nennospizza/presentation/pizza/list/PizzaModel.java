package ceduliocezar.com.nennospizza.presentation.pizza.list;

import java.util.List;

/**
 * Created by cedulio.silva on 4/12/2018.
 */

public class PizzaModel {

    private String id;
    private String name;
    private List<String> ingredients;
    private double totalPrice;
    private String imageUrl;


    public PizzaModel(String id,
                      String name,
                      List<String> ingredients,
                      double totalPrice, String imageUrl) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.totalPrice = totalPrice;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public String toString() {
        return "PizzaModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", ingredients=" + ingredients +
                ", totalPrice=" + totalPrice +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
