package ceduliocezar.com.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by cedulio.silva on 4/12/2018.
 */

public class PizzaEntity {

    @SerializedName("name")
    private String name;

    @SerializedName("ingredients")
    private List<Integer> ingredients;

    @SerializedName("imageUrl")
    private String imageUrl;

    public PizzaEntity(String name, List<Integer> ingredients, String imageUrl) {
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

    public List<Integer> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Integer> ingredients) {
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
        return "PizzaEntity{" +
                "name='" + name + '\'' +
                ", ingredients=" + ingredients +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
