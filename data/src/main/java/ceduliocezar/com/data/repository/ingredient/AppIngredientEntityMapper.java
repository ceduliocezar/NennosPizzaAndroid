package ceduliocezar.com.data.repository.ingredient;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ceduliocezar.com.data.entity.IngredientEntity;
import ceduliocezar.com.domain.Ingredient;

/**
 * App implementation for {@link IngredientEntityMapper}
 * Created by cedulio.silva on 4/17/2018.
 */

public class AppIngredientEntityMapper implements IngredientEntityMapper {

    @Inject
    public AppIngredientEntityMapper() {
        // mandatory ci constructor
    }

    @Override
    public List<Ingredient> transform(List<IngredientEntity> ingredientEntities) {


        List<Ingredient> ingredients = new ArrayList<>();
        for (IngredientEntity ingredientEntity : ingredientEntities) {
            ingredients.add(new Ingredient(ingredientEntity.getId(), ingredientEntity.getName(), ingredientEntity.getPrice()));
        }


        return ingredients;
    }
}
