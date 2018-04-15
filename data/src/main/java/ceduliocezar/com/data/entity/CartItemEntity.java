package ceduliocezar.com.data.entity;

public class CartItemEntity {

    private String id;
    private String title;
    private double price;

    private PizzaEntity pizzaEntity;
    private DrinkEntity drinkEntity;


    public CartItemEntity(String id, String title, double price) {
        this.id = id;
        this.title = title;
        this.price = price;
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

    public PizzaEntity getPizzaEntity() {
        return pizzaEntity;
    }

    public void setPizzaEntity(PizzaEntity pizzaEntity) {
        this.pizzaEntity = pizzaEntity;
    }

    public DrinkEntity getDrinkEntity() {
        return drinkEntity;
    }

    public void setDrinkEntity(DrinkEntity drinkEntity) {
        this.drinkEntity = drinkEntity;
    }

    @Override
    public String toString() {
        return "CartItemEntity{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", pizzaEntity=" + pizzaEntity +
                ", drinkEntity=" + drinkEntity +
                '}';
    }
}
