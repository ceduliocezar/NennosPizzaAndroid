package ceduliocezar.com.nennospizza.presentation.drink;

public class DrinkModel {

    private long id;

    private String drink;

    private double price;

    public DrinkModel(long id, String drink, double price) {
        this.id = id;
        this.drink = drink;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public String getDrink() {
        return drink;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "DrinkModel{" +
                "id=" + id +
                ", drink='" + drink + '\'' +
                ", price=" + price +
                '}';
    }
}
