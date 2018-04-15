package ceduliocezar.com.data.repository.cart;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import ceduliocezar.com.data.entity.DrinkEntity;
import ceduliocezar.com.data.entity.PizzaEntity;

public class CartTO {


    @SerializedName("pizzas")
    private List<PizzaEntity> pizzas;

    @SerializedName("drinks")
    private List<DrinkEntity> drinks;


    public CartTO(List<PizzaEntity> pizzas, List<DrinkEntity> drinks) {
        this.pizzas = pizzas;
        this.drinks = drinks;
    }

    public List<PizzaEntity> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<PizzaEntity> pizzas) {
        this.pizzas = pizzas;
    }

    public List<DrinkEntity> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<DrinkEntity> drinks) {
        this.drinks = drinks;
    }

    @Override
    public String toString() {
        return "CartTO{" +
                "pizzas=" + pizzas +
                ", drinks=" + drinks +
                '}';
    }
}
