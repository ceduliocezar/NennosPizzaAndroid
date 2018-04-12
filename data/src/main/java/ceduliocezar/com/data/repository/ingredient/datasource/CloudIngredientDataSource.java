package ceduliocezar.com.data.repository.ingredient.datasource;

import java.util.List;

import javax.inject.Inject;

import ceduliocezar.com.data.entity.IngredientEntity;
import ceduliocezar.com.data.remote.NennosService;
import ceduliocezar.com.data.repository.ingredient.IngredientDataSource;
import ceduliocezar.com.domain.logging.Logger;
import io.reactivex.Observable;

/**
 * Retrieves {@link IngredientEntity} from the cloud.
 */
public class CloudIngredientDataSource implements IngredientDataSource {

    private static final String TAG = "CloudIngredientDataSour";

    private NennosService nennosService;
    private Logger logger;

    @Inject
    public CloudIngredientDataSource(NennosService nennosService, Logger logger) {
        this.nennosService = nennosService;
        this.logger = logger;
    }

    @Override
    public Observable<List<IngredientEntity>> ingredientList() {
        logger.debug(TAG, "ingredientList");
        return nennosService
                .listIngredient();
    }
}
