package ceduliocezar.com.domain.repository;

import java.util.List;

import ceduliocezar.com.domain.Drink;
import io.reactivex.Single;

public interface DrinkRepository {
    Single<List<Drink>> list();
}
