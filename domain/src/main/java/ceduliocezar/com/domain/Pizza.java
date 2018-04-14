package ceduliocezar.com.domain;

import java.util.List;

/**
 * Created by cedulio.silva on 4/12/2018.
 */

public class Pizza {

    private String id;
    private String name;
    private List<Ingredient> ingredients;
    private String imageUrl;
    private double basePrice;


    public Pizza(String id,
                 String name,
                 List<Ingredient> ingredients,
                 String imageUrl,
                 double basePrice) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.imageUrl = imageUrl;
        this.basePrice = basePrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", ingredients=" + ingredients +
                ", imageUrl='" + imageUrl + '\'' +
                ", basePrice=" + basePrice +
                '}';
    }
}
