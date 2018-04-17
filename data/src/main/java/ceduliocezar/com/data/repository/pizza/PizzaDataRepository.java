package ceduliocezar.com.data.repository.pizza;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ceduliocezar.com.data.entity.PizzaEntity;
import ceduliocezar.com.domain.Ingredient;
import ceduliocezar.com.domain.Pizza;
import ceduliocezar.com.domain.logging.Logger;
import ceduliocezar.com.domain.repository.IngredientRepository;
import ceduliocezar.com.domain.repository.PizzaRepository;
import dagger.Lazy;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Retrieves information about {@link Pizza}. It can be loaded from Cloud or disk,
 * depending on the state of the internet connection.
 * Created by cedulio.silva on 4/12/2018.
 */

public class PizzaDataRepository implements PizzaRepository {
    private static final String TAG = "PizzaDataRepository";

    private PizzaDataSource cloudPizzaDataSource;
    private Lazy<IngredientRepository> ingredientRepository;
    private Logger logger;

    @Inject
    public PizzaDataRepository(PizzaDataSource cloudPizzaDataSource,
                               Lazy<IngredientRepository> ingredientRepository,
                               Logger logger) {
        this.cloudPizzaDataSource = cloudPizzaDataSource;
        this.ingredientRepository = ingredientRepository;
        this.logger = logger;
    }

    @Override
    public Observable<List<Pizza>> list() {
        logger.debug(TAG, "list");
        final List<Ingredient> ingredients = new ArrayList<>();

        return getDataSource()
                .listPizza()
                .map(new Function<List<PizzaEntity>, List<Pizza>>() {
                    @Override
                    public List<Pizza> apply(final List<PizzaEntity> pizzaEntities) {
                        return Observable.fromIterable(pizzaEntities)
                                .map(new Function<PizzaEntity, Pizza>() {
                                    @Override
                                    public Pizza apply(final PizzaEntity pizzaEntity) {

                                        if (ingredients.isEmpty()) {
                                            // must find a better way to do it with RX and don't request ingredients repeatedly.
                                            ingredients.addAll(ingredientRepository.get().list().blockingFirst());
                                        }

                                        return createPizza(pizzaEntity, ingredients);
                                    }
                                })
                                .toList()
                                .blockingGet();
                    }
                });
    }

    @NonNull
    private Pizza createPizza(PizzaEntity pizzaEntity, List<Ingredient> allIngredients) {
        logger.debug(TAG, "createPizza");

        List<Ingredient> pizzaIngredients = new ArrayList<>();

        List<Long> pizzaIngredientsIds = pizzaEntity.getIngredients();

        for (Ingredient ingredient : allIngredients) {

            if (pizzaIngredientsIds.contains(ingredient.getId())) {
                pizzaIngredients.add(ingredient);
            }
        }

        return new Pizza(pizzaEntity.getName(),
                pizzaIngredients,
                pizzaEntity.getImageUrl(),
                pizzaEntity.getBasePrice());
    }

    @Override
    public Observable<Pizza> getPizzaByName(final String pizzaName) {
        return list()
                .map(new Function<List<Pizza>, Pizza>() {
                    @Override
                    public Pizza apply(List<Pizza> pizzas) {

                        for (Pizza pizza : pizzas) {
                            if (pizza.getName().equals(pizzaName)) {
                                return pizza;
                            }
                        }
                        throw new RuntimeException("Pizza not found");
                    }
                });
    }


    private PizzaDataSource getDataSource() {
        return cloudPizzaDataSource;
    }
}
