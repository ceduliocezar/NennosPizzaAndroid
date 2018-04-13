package ceduliocezar.com.data.repository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import ceduliocezar.com.data.entity.PizzaEntity;
import ceduliocezar.com.data.entity.mapper.PizzaMapper;
import ceduliocezar.com.data.internet.InternetChecker;
import ceduliocezar.com.data.repository.datasource.PizzaDataSource;
import ceduliocezar.com.domain.Pizza;
import ceduliocezar.com.domain.repository.PizzaRepository;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Retrieves information about {@link Pizza}. It can be loaded from Cloud or disk,
 * depending on the state of the internet connection.
 * Created by cedulio.silva on 4/12/2018.
 */

public class PizzaDataRepository implements PizzaRepository {

    private PizzaDataSource diskPizzaDataSource;
    private PizzaDataSource cloudPizzaDataSource;
    private InternetChecker internetChecker;
    private PizzaMapper pizzaMapper;

    @Inject
    public PizzaDataRepository(@Named("disk") PizzaDataSource diskPizzaDataSource,
                               @Named("cloud") PizzaDataSource cloudPizzaDataSource,
                               InternetChecker internetChecker,
                               PizzaMapper pizzaMapper) {
        this.diskPizzaDataSource = diskPizzaDataSource;
        this.cloudPizzaDataSource = cloudPizzaDataSource;
        this.internetChecker = internetChecker;
        this.pizzaMapper = pizzaMapper;
    }

    @Override
    public Observable<List<Pizza>> list() {
        return getDataSource()
                .pizzaList()
                .map(new Function<List<PizzaEntity>, List<Pizza>>() {
                    @Override
                    public List<Pizza> apply(List<PizzaEntity> pizzaEntities) throws Exception {
                        return pizzaMapper.transform(pizzaEntities);
                    }
                });
    }

    private PizzaDataSource getDataSource() {

        if (internetChecker.isDeviceOnline()) {
            return cloudPizzaDataSource;
        }

        return diskPizzaDataSource;
    }
}
