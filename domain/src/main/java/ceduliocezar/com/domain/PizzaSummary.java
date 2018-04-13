package ceduliocezar.com.domain;

import java.util.List;

/**
 * Created by cedulio.silva on 4/12/2018.
 */

public class PizzaSummary {

    private String name;
    private List<String> ingredients;
    private double totalPrice;

    public PizzaSummary(String name, List<String> ingredients, double totalPrice) {
        this.name = name;
        this.ingredients = ingredients;
        this.totalPrice = totalPrice;
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

    @Override
    public String toString() {
        return "PizzaSummary{" +
                "name='" + name + '\'' +
                ", ingredients=" + ingredients +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
