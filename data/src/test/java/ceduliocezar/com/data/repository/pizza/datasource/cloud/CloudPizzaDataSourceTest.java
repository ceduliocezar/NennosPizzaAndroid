package ceduliocezar.com.data.repository.pizza.datasource.cloud;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ceduliocezar.com.data.entity.PizzaEntity;
import ceduliocezar.com.data.remote.MenuService;
import ceduliocezar.com.domain.logging.Logger;
import io.reactivex.Observable;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.TestObserver;

import static org.mockito.Mockito.when;

public class CloudPizzaDataSourceTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @InjectMocks
    private CloudPizzaDataSource cloudPizzaDataSource;


    @Mock
    private MenuService menuService;

    @Mock
    @SuppressWarnings("unused")
    private Logger logger;

    @Mock
    private PizzasTO pizzaTO;

    @Test
    public void test_listPizza() {

        List<PizzaEntity> pizzas = new ArrayList<>();
        pizzas.add(new PizzaEntity("pizza2", Arrays.asList(10L, 20L), "fakeURL"));
        pizzas.add(new PizzaEntity("pizza3", Arrays.asList(10L, 20L), "fakeURL"));

        when(menuService.listPizzas()).thenReturn(Observable.just(pizzaTO));
        when(pizzaTO.getPizzas()).thenReturn(pizzas);
        when(pizzaTO.getBasePrice()).thenReturn(33.0d);

        TestObserver<List<PizzaEntity>> observer = cloudPizzaDataSource.listPizza().test();

        observer.awaitTerminalEvent();

        observer.assertNoErrors().assertValue(new Predicate<List<PizzaEntity>>() {
            @Override
            public boolean test(List<PizzaEntity> pizzaEntities) {

                if (pizzaEntities.size() != 2
                        || pizzaEntities.get(0).getBasePrice() != 33.0d
                        || pizzaEntities.get(1).getBasePrice() != 33.0d) {
                    return false;
                }

                return true;
            }
        });


    }
}