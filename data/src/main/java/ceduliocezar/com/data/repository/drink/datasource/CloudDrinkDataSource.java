package ceduliocezar.com.data.repository.drink.datasource;

import java.util.List;

import javax.inject.Inject;

import ceduliocezar.com.data.entity.DrinkEntity;
import ceduliocezar.com.data.remote.MenuService;
import ceduliocezar.com.domain.logging.Logger;
import io.reactivex.Single;

public class CloudDrinkDataSource implements DrinkDataSource {

    private static final String TAG = "CloudDrinkDataSource";

    private MenuService menuService;
    private Logger logger;

    @Inject
    public CloudDrinkDataSource(MenuService menuService, Logger logger) {
        this.menuService = menuService;
        this.logger = logger;
    }

    @Override
    public Single<List<DrinkEntity>> list() {
        logger.debug(TAG, "list");
        return menuService.listDrinks();
    }
}
