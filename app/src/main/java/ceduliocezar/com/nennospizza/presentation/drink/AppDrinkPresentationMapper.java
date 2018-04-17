package ceduliocezar.com.nennospizza.presentation.drink;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ceduliocezar.com.domain.Drink;

public class AppDrinkPresentationMapper implements DrinkPresentationMapper {

    @Inject
    public AppDrinkPresentationMapper() {
        // mandatory DI constructor
    }

    @Override
    public List<DrinkModel> transform(List<Drink> drinks) {

        List<DrinkModel> models = new ArrayList<>();


        for (Drink drink : drinks) {
            models.add(new DrinkModel(drink.getId(), drink.getName(), drink.getPrice()));
        }

        return models;
    }

    @Override
    public Drink transform(DrinkModel drinkModel) {
        return new Drink(drinkModel.getId(), drinkModel.getDrink(), drinkModel.getPrice());
    }
}
