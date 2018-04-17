package ceduliocezar.com.data.repository.ingredient;

import java.util.List;

import ceduliocezar.com.data.entity.IngredientEntity;
import ceduliocezar.com.domain.Ingredient;

/**
 * Mapper that transforms {@link IngredientEntity} into {@link Ingredient}
 * Created by cedulio.silva on 4/17/2018.
 */

public interface IngredientEntityMapper {

    List<Ingredient> transform(List<IngredientEntity> ingredientEntities);
}
