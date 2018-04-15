package ceduliocezar.com.data.serialization;

import com.google.gson.GsonBuilder;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ceduliocezar.com.data.entity.DrinkEntity;
import ceduliocezar.com.data.entity.PizzaEntity;
import ceduliocezar.com.data.repository.cart.CartTO;

import static junit.framework.Assert.assertEquals;

public class CartTOSerializerTest {

    @Test
    public void test_serializeCart() throws Exception {
        List<PizzaEntity> pizzas = new ArrayList<>();
        pizzas.add(new PizzaEntity("Pizza1", Arrays.asList(1L, 2L, 3L), "www1.google.com"));
        pizzas.add(new PizzaEntity("Pizza2", Arrays.asList(4L, 5L, 6L), "www2.google.com"));
        pizzas.add(new PizzaEntity("Pizza3", Arrays.asList(7L, 8L, 9L), "www3.google.com"));

        List<DrinkEntity> drinkEntities = new ArrayList<>();
        drinkEntities.add(new DrinkEntity(1, "name1", 10.0d));
        drinkEntities.add(new DrinkEntity(2, "name2", 10.0d));
        drinkEntities.add(new DrinkEntity(3, "name3", 10.0d));

        CartTO cartTO = new CartTO(pizzas, drinkEntities);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(CartTO.class, new CartTOSerializer());
        String jsonResult = gsonBuilder.create().toJson(cartTO);

        String checkoutSample = readCheckoutSample();

        assertEquals(checkoutSample, jsonResult);
    }

    private String readCheckoutSample() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("sample_checkout.json");
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);

        return new String(bytes);
    }
}