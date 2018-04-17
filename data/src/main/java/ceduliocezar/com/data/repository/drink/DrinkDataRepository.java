package ceduliocezar.com.data.repository.drink;

import java.util.List;

import javax.inject.Inject;

import ceduliocezar.com.data.entity.DrinkEntity;
import ceduliocezar.com.data.repository.drink.datasource.DrinkDataSource;
import ceduliocezar.com.domain.Drink;
import ceduliocezar.com.domain.logging.Logger;
import ceduliocezar.com.domain.repository.DrinkRepository;
import io.reactivex.Single;
import io.reactivex.functions.Function;

public class DrinkDataRepository implements DrinkRepository {
    private static final String TAG = "DrinkDataRepository";
    private Logger logger;
    private DrinkDataSource drinkDataSource;
    private DrinkEntityMapper drinkEntityMapper;

    @Inject
    public DrinkDataRepository(Logger logger,
                               DrinkDataSource drinkDataSource,
                               DrinkEntityMapper drinkEntityMapper) {
        this.logger = logger;
        this.drinkDataSource = drinkDataSource;
        this.drinkEntityMapper = drinkEntityMapper;
    }

    @Override
    public Single<List<Drink>> list() {
        logger.debug(TAG, "list");
        return drinkDataSource
                .list()
                .map(new Function<List<DrinkEntity>, List<Drink>>() {
                    @Override
                    public List<Drink> apply(List<DrinkEntity> drinkEntities) {
                        return drinkEntityMapper.transform(drinkEntities);
                    }
                });
    }
}
