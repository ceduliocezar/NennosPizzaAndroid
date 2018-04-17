package ceduliocezar.com.data.repository.drink;

import java.util.List;

import ceduliocezar.com.data.entity.DrinkEntity;
import ceduliocezar.com.domain.Drink;

public interface DrinkEntityMapper {

    List<Drink> transform(List<DrinkEntity> drinkEntities);
}
