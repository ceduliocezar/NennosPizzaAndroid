package ceduliocezar.com.domain;

import java.util.List;

/**
 * Created by cedulio.silva on 4/12/2018.
 */

public class Pizza {

    private String name;
    private List<Ingredient> ingredients;
    private String imageUrl;

    public Pizza(String name, List<Ingredient> ingredients, String imageUrl) {
        this.name = name;
        this.ingredients = ingredients;
        this.imageUrl = imageUrl;
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

    @Override
    public String toString() {
        return "Pizza{" +
                "name='" + name + '\'' +
                ", ingredients=" + ingredients +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
