package ceduliocezar.com.domain.repository;

import java.util.List;

import ceduliocezar.com.domain.Ingredient;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface IngredientRepository {

    Observable<List<Ingredient>> list();

    Single<List<Ingredient>> getIngredientsListById(List<Long> ingredients);
}
