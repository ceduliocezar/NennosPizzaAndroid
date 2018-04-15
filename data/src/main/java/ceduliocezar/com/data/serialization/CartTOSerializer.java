package ceduliocezar.com.data.serialization;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

import javax.inject.Inject;

import ceduliocezar.com.data.entity.DrinkEntity;
import ceduliocezar.com.data.entity.PizzaEntity;
import ceduliocezar.com.data.repository.cart.CartTO;

public class CartTOSerializer implements JsonSerializer<CartTO> {


    @Inject
    public CartTOSerializer() {
        // mandatory di constructor
    }

    @Override
    public JsonElement serialize(CartTO src,
                                 Type typeOfSrc,
                                 JsonSerializationContext context) {


        JsonObject jsonObject = new JsonObject();


        JsonArray jsonArrayPizzas = new JsonArray();
        for (PizzaEntity pizza : src.getPizzas()) {
            jsonArrayPizzas.add(context.serialize(pizza, PizzaEntity.class));
        }

        jsonObject.add("pizzas", jsonArrayPizzas);


        JsonArray jsonArrayDrinks = new JsonArray();
        for (DrinkEntity drink : src.getDrinks()) {
            jsonArrayDrinks.add(drink.getId());
        }

        jsonObject.add("drinks", jsonArrayDrinks);
        return jsonObject;
    }
}
