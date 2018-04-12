package ceduliocezar.com.data.repository.ingredient.datasource;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import ceduliocezar.com.data.entity.IngredientEntity;
import ceduliocezar.com.data.remote.NennosService;
import ceduliocezar.com.domain.logging.Logger;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test suite for {@link CloudIngredientDataSource}
 */
public class CloudIngredientDataSourceTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @InjectMocks
    private
    CloudIngredientDataSource cloudIngredientDataSource;

    @Mock
    private NennosService nennosService;

    @Mock
    private Logger logger;

    @Mock
    private IngredientEntity ingredient;

    @Test
    public void test_ingredientList() {

        List<IngredientEntity> ingredientEntities = new ArrayList<>();
        ingredientEntities.add(ingredient);
        ingredientEntities.add(ingredient);
        ingredientEntities.add(ingredient);


        when(nennosService.listIngredient()).thenReturn(Observable.just(ingredientEntities));

        TestObserver<List<IngredientEntity>> observer = cloudIngredientDataSource.ingredientList().test();

        observer.awaitTerminalEvent();

        verify(nennosService).listIngredient();
    }
}