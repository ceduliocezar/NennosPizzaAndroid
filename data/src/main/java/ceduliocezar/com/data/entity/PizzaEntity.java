package ceduliocezar.com.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by cedulio.silva on 4/12/2018.
 */

public class PizzaEntity {

    @SerializedName("name")
    private String name;

    @SerializedName("ingredients")
    private List<Long> ingredients;

    @SerializedName("imageUrl")
    private String imageUrl;

    @Expose(deserialize = false)
    private transient double basePrice;

    public PizzaEntity(String name, List<Long> ingredients, String imageUrl) {
        this.name = name;
        this.ingredients = ingredients;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public List<Long> getIngredients() {
        return ingredients;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setIngredients(List<Long> ingredients) {
        this.ingredients = ingredients;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PizzaEntity{" +
                "name='" + name + '\'' +
                ", ingredients=" + ingredients +
                ", imageUrl='" + imageUrl + '\'' +
                ", basePrice=" + basePrice +
                '}';
    }
}
