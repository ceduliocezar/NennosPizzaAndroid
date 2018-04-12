package ceduliocezar.com.data.repository;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import ceduliocezar.com.data.entity.PizzaEntity;
import ceduliocezar.com.data.repository.pizza.PizzaDataRepository;
import ceduliocezar.com.data.repository.pizza.PizzaDataSource;
import ceduliocezar.com.domain.Ingredient;
import ceduliocezar.com.domain.Pizza;
import ceduliocezar.com.domain.logging.Logger;
import ceduliocezar.com.domain.repository.IngredientRepository;
import dagger.Lazy;
import io.reactivex.Observable;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.TestObserver;

import static org.mockito.Mockito.when;

/**
 * Test suite for {@link PizzaDataRepository}
 * Created by cedulio.silva on 4/12/2018.
 */

public class PizzaDataRepositoryTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @InjectMocks
    private PizzaDataRepository pizzaDataRepository;

    @Mock
    private PizzaDataSource pizzaDataSource;

    @Mock
    private Lazy<IngredientRepository> ingredientRepositoryLazy;

    @Mock
    private IngredientRepository ingredientsRepository;

    @Mock
    @SuppressWarnings("unused")
    private Logger logger;

    @Test
    public void test_list() {
        List<PizzaEntity> pizzas = new ArrayList<>();
        pizzas.add(new PizzaEntity("Pizza1", Arrays.asList(10L), "fakeURL"));
        pizzas.add(new PizzaEntity("Pizza2", Arrays.asList(10L, 20L), "fakeURL"));

        when(pizzaDataSource.listPizza()).thenReturn(Observable.just(pizzas));
        when(ingredientRepositoryLazy.get()).thenReturn(ingredientsRepository);

        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient(10L, "ingredient1", 10.0d));
        ingredients.add(new Ingredient(20L, "ingredient2", 20.0d));
        ingredients.add(new Ingredient(30L, "ingredient3", 30.0d));

        when(ingredientsRepository.list()).thenReturn(Observable.just(ingredients));

        TestObserver<List<Pizza>> observer = pizzaDataRepository.list().test();

        observer.awaitTerminalEvent();

        observer.assertNoErrors().assertValue(new Predicate<List<Pizza>>() {
            @Override
            public boolean test(List<Pizza> pizzas) {
                if (pizzas.size() != 2
                        || !pizzas.get(0).getName().equals("Pizza1")
                        || pizzas.get(0).getIngredients().size() != 1
                        || !pizzas.get(1).getName().equals("Pizza2")
                        || pizzas.get(1).getIngredients().size() != 2) {

                    return false;
                }
                return true;
            }
        });
    }

    @Test
    public void test_getPizzaByName() {


        List<PizzaEntity> pizzas = new ArrayList<>();
        pizzas.add(new PizzaEntity("Pizza1", Collections.<Long>emptyList(), "fakeURL"));
        PizzaEntity pizzaEntity = new PizzaEntity("My Favourite Pizza", Arrays.asList(10L, 20L), "fakeURL2");
        pizzaEntity.setBasePrice(10.0d);
        pizzas.add(pizzaEntity);

        when(pizzaDataSource.listPizza()).thenReturn(Observable.just(pizzas));

        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient(10L, "ingredient1", 10.0d));
        ingredients.add(new Ingredient(20L, "ingredient2", 20.0d));
        ingredients.add(new Ingredient(30L, "ingredient3", 30.0d));

        when(ingredientRepositoryLazy.get()).thenReturn(ingredientsRepository);
        when(ingredientsRepository.list()).thenReturn(Observable.just(ingredients));

        TestObserver<Pizza> observer = pizzaDataRepository.getPizzaByName("My Favourite Pizza").test();

        observer.awaitTerminalEvent();

        observer.assertNoErrors()
                .assertValue(new Predicate<Pizza>() {
                    @Override
                    public boolean test(Pizza pizza) {

                        if (!pizza.getName().equals("My Favourite Pizza")
                                || pizza.getIngredients().size() != 2
                                || !pizza.getImageUrl().equals("fakeURL2")
                                || pizza.getBasePrice() != 10.0d) {
                            return false;
                        }

                        return true;
                    }
                });
    }
}
