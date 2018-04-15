package ceduliocezar.com.data.repository.ingredient.datasource;

import java.util.List;

import javax.inject.Inject;

import ceduliocezar.com.data.entity.IngredientEntity;
import ceduliocezar.com.data.remote.MenuService;
import ceduliocezar.com.data.repository.ingredient.IngredientDataSource;
import ceduliocezar.com.domain.logging.Logger;
import io.reactivex.Observable;

/**
 * Retrieves {@link IngredientEntity} from the cloud.
 */
public class CloudIngredientDataSource implements IngredientDataSource {

    private static final String TAG = "CloudIngredientDataSour";

    private MenuService menuService;
    private Logger logger;

    @Inject
    public CloudIngredientDataSource(MenuService menuService, Logger logger) {
        this.menuService = menuService;
        this.logger = logger;
    }

    @Override
    public Observable<List<IngredientEntity>> ingredientList() {
        logger.debug(TAG, "ingredientList");
        return menuService
                .listIngredient();
    }
}
