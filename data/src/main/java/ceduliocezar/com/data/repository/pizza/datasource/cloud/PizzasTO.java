package ceduliocezar.com.data.repository.pizza.datasource.cloud;

import java.util.List;

import ceduliocezar.com.data.entity.PizzaEntity;

/**
 * Used by the webservice to transport a collection of {@link PizzaEntity}
 */
public class PizzasTO {

    private List<PizzaEntity> pizzas;

    private double basePrice;

    public PizzasTO(List<PizzaEntity> pizzas, double basePrice) {
        this.pizzas = pizzas;
        this.basePrice = basePrice;
    }

    public List<PizzaEntity> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<PizzaEntity> pizzas) {
        this.pizzas = pizzas;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    @Override
    public String toString() {
        return "PizzasTO{" +
                "pizzas=" + pizzas +
                ", basePrice=" + basePrice +
                '}';
    }
}