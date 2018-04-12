package ceduliocezar.com.data.repository.ingredient;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ceduliocezar.com.data.entity.IngredientEntity;
import ceduliocezar.com.domain.Ingredient;
import ceduliocezar.com.domain.logging.Logger;
import ceduliocezar.com.domain.repository.IngredientRepository;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

public class IngredientDataRepository implements IngredientRepository {

    private static final String TAG = "IngredientDataRepositor";

    private IngredientDataSource ingredientDataSource;
    private Logger logger;

    @Inject
    public IngredientDataRepository(IngredientDataSource ingredientDataSource,
                                    Logger logger) {
        this.ingredientDataSource = ingredientDataSource;
        this.logger = logger;
    }

    @Override
    public Observable<List<Ingredient>> list() {
        logger.debug(TAG, "list");
        return ingredientDataSource
                .ingredientList()
                .map(new Function<List<IngredientEntity>, List<Ingredient>>() {
                    @Override
                    public List<Ingredient> apply(List<IngredientEntity> ingredientEntities) {

                        List<Ingredient> ingredients = new ArrayList<>();

                        for (IngredientEntity ingredientEntity : ingredientEntities) {

                            ingredients.add(new Ingredient(ingredientEntity.getId(), ingredientEntity.getName(), ingredientEntity.getPrice()));
                        }

                        return ingredients;
                    }
                });
    }


    @Override
    public Single<List<Ingredient>> getIngredientsListById(final List<Long> ingredients) {
        return list()
                .flatMapIterable(new Function<List<Ingredient>, List<Ingredient>>() {

                    @Override
                    public List<Ingredient> apply(List<Ingredient> ingredients) {
                        return ingredients;
                    }
                }).filter(new Predicate<Ingredient>() {
                    @Override
                    public boolean test(Ingredient ingredient) {
                        return ingredients.contains(ingredient.getId());
                    }
                }).toList();
    }
}
