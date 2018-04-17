package ceduliocezar.com.data.repository.ingredient;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ceduliocezar.com.data.entity.IngredientEntity;
import ceduliocezar.com.domain.Ingredient;
import ceduliocezar.com.domain.logging.Logger;
import io.reactivex.Observable;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.TestObserver;

import static org.mockito.Mockito.when;

/**
 * Test suite for {@link IngredientDataRepository}
 */
public class IngredientDataRepositoryTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @InjectMocks
    private IngredientDataRepository ingredientDataRepository;

    @Mock
    private IngredientDataSource ingredientDataSource;

    @Mock
    private IngredientEntityMapper ingredientEntityMapper;


    @Mock
    @SuppressWarnings("unused")
    private Logger logger;

    @Test
    public void test_ingredientList() {
        List<IngredientEntity> ingredientsEntities = new ArrayList<>();
        final List<Ingredient> ingredients = new ArrayList<>();
        when(ingredientDataSource.ingredientList()).thenReturn(Observable.just(ingredientsEntities));
        when(ingredientEntityMapper.transform(ingredientsEntities)).thenReturn(ingredients);

        TestObserver<List<Ingredient>> observer = ingredientDataRepository.list().test();
        observer.awaitTerminalEvent();

        observer.assertNoErrors()
                .assertValue(new Predicate<List<Ingredient>>() {
                    @Override
                    public boolean test(List<Ingredient> ingredientList) {

                        return ingredientList == ingredients;
                    }
                });
    }

    @Test
    public void test_getIngredientsListById() {

        List<IngredientEntity> ingredientsEntities = new ArrayList<>();
        final List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient(10L, null, 10.0d));
        when(ingredientDataSource.ingredientList()).thenReturn(Observable.just(ingredientsEntities));
        when(ingredientEntityMapper.transform(ingredientsEntities)).thenReturn(ingredients);

        TestObserver<List<Ingredient>> observer = ingredientDataRepository.getIngredientsListById(Arrays.asList(10L, 40L, 50L)).test();
        observer.awaitTerminalEvent();

        observer.assertNoErrors()
                .assertValue(new Predicate<List<Ingredient>>() {
                    @Override
                    public boolean test(List<Ingredient> ingredientList) {
                        return ingredientList.size() == 1 && ingredientList.get(0).getId() == 10L;
                    }
                });

    }

}