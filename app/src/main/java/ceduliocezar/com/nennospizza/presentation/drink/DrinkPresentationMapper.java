package ceduliocezar.com.nennospizza.presentation.drink;

import java.util.List;

import ceduliocezar.com.domain.Drink;

public interface DrinkPresentationMapper {

    List<DrinkModel> transform(List<Drink> drinks);

    Drink transform(DrinkModel drinkModel);
}
