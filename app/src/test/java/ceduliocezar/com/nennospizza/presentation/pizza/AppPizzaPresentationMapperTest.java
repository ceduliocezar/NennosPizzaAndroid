package ceduliocezar.com.nennospizza.presentation.pizza;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import ceduliocezar.com.domain.Ingredient;
import ceduliocezar.com.domain.Pizza;
import ceduliocezar.com.nennospizza.presentation.pizza.list.PizzaModel;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Test suite for {@link AppPizzaPresentationMapper}
 */
public class AppPizzaPresentationMapperTest {


    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @InjectMocks
    private AppPizzaPresentationMapper appPizzaPresentationMapper;

    @Mock
    private Pizza pizza;
    @Mock
    private Pizza pizza2;

    @Mock
    private Ingredient ingredient;


    @Test
    public void transform() {
        when(ingredient.getName()).thenReturn("Ingredient1");
        when(ingredient.getPrice()).thenReturn(10.0);

        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient);

        when(pizza.getId()).thenReturn("idPizza1");
        when(pizza2.getId()).thenReturn("idPizza2");
        when(pizza.getName()).thenReturn("pizzaName1");
        when(pizza2.getName()).thenReturn("pizzaName2");
        when(pizza.getBasePrice()).thenReturn(11.0);
        when(pizza2.getBasePrice()).thenReturn(12.0);
        when(pizza.getImageUrl()).thenReturn("fakeUrl1");
        when(pizza2.getImageUrl()).thenReturn("fakeUrl2");
        when(pizza.getIngredients()).thenReturn(ingredients);
        when(pizza2.getIngredients()).thenReturn(null);

        List<Pizza> pizzas = new ArrayList<>();
        pizzas.add(pizza);
        pizzas.add(pizza2);

        List<PizzaModel> models = appPizzaPresentationMapper.transform(pizzas);

        assertEquals(2, models.size());

        assertEquals("idPizza1", models.get(0).getId());
        assertEquals("pizzaName1", models.get(0).getName());
        assertEquals("fakeUrl1", models.get(0).getImageUrl());
        assertEquals("Ingredient1", models.get(0).getIngredients().get(0));

        assertEquals("idPizza2", models.get(1).getId());
        assertEquals("pizzaName2", models.get(1).getName());
        assertEquals("fakeUrl2", models.get(1).getImageUrl());
        assertTrue(models.get(1).getIngredients().isEmpty());
    }
}