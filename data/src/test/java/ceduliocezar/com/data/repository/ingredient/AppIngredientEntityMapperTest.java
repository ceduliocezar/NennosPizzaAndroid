package ceduliocezar.com.data.repository.ingredient;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import ceduliocezar.com.data.entity.DrinkEntity;
import ceduliocezar.com.data.repository.drink.AppDrinkEntityMapper;
import ceduliocezar.com.domain.Drink;

import static org.junit.Assert.assertEquals;

/**
 * Test suite for {@link AppIngredientEntityMapper}
 * Created by cedulio.silva on 4/17/2018.
 */
public class AppIngredientEntityMapperTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @InjectMocks
    private AppDrinkEntityMapper mapper;

    @Test
    public void test_transform() throws Exception {

        List<DrinkEntity> drinksEntity = new ArrayList<>();
        drinksEntity.add(new DrinkEntity(10L, "name1", 10.0d));
        drinksEntity.add(new DrinkEntity(20L, "name2", 20.0d));
        drinksEntity.add(new DrinkEntity(30L, "name3", 30.0d));

        List<Drink> drinks = mapper.transform(drinksEntity);

        assertEquals(3, drinks.size());
        assertEquals(10L, drinks.get(0).getId());
        assertEquals("name1", drinks.get(0).getName());
        assertEquals(10.0d, drinks.get(0).getPrice(), 0);
    }

}