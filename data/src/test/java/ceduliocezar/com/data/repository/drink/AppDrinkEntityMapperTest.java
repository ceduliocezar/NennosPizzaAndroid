package ceduliocezar.com.data.repository.drink;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import ceduliocezar.com.data.entity.DrinkEntity;
import ceduliocezar.com.domain.Drink;

import static junit.framework.Assert.assertEquals;

/**
 * Test suite for {@link AppDrinkEntityMapper}
 * Created by cedulio.silva on 4/17/2018.
 */
public class AppDrinkEntityMapperTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @InjectMocks
    private AppDrinkEntityMapper appDrinkEntityMapper;

    @Test
    public void test() throws Exception {
        List<DrinkEntity> entities = new ArrayList<>();
        entities.add(new DrinkEntity(10L, "name1", 10.0d));
        entities.add(new DrinkEntity(20L, "name2", 20.0d));
        entities.add(new DrinkEntity(20L, "name3", 30.0d));

        List<Drink> drinks = appDrinkEntityMapper.transform(entities);

        assertEquals(3, drinks.size());
        assertEquals(10L, drinks.get(0).getId());
        assertEquals("name1", drinks.get(0).getName());
        assertEquals(10.0d, drinks.get(0).getPrice());
    }

}