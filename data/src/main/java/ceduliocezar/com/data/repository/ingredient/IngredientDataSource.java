package ceduliocezar.com.data.repository.ingredient;

import java.util.List;

import ceduliocezar.com.data.entity.IngredientEntity;
import io.reactivex.Observable;

/**
 * Data source of {@link IngredientEntity}.
 */
public interface IngredientDataSource {

    /**
     * Retrieves all available {@link IngredientEntity}
     *
     * @return {@link List} of {@link IngredientEntity}
     */
    Observable<List<IngredientEntity>> ingredientList();

}
