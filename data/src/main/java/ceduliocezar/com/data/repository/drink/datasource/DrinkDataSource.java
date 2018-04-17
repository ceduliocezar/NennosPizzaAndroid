package ceduliocezar.com.data.repository.drink.datasource;

import java.util.List;

import ceduliocezar.com.data.entity.DrinkEntity;
import io.reactivex.Single;

public interface DrinkDataSource {

    Single<List<DrinkEntity>> list();
}
