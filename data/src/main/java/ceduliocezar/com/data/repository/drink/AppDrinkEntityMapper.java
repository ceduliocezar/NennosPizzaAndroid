package ceduliocezar.com.data.repository.drink;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ceduliocezar.com.data.entity.DrinkEntity;
import ceduliocezar.com.domain.Drink;

public class AppDrinkEntityMapper implements DrinkEntityMapper {

    @Inject
    public AppDrinkEntityMapper() {
        // mandatory DI constructor
    }

    @Override
    public List<Drink> transform(List<DrinkEntity> drinkEntities) {

        List<Drink> drinks = new ArrayList<>();

        for (DrinkEntity drinkEntity : drinkEntities) {
            drinks.add(new Drink(drinkEntity.getId(), drinkEntity.getName(), drinkEntity.getPrice()));
        }

        return drinks;
    }
}
