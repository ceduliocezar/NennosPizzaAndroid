package ceduliocezar.com.data.repository.drink.datasource;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import ceduliocezar.com.data.entity.DrinkEntity;
import ceduliocezar.com.data.remote.MenuService;
import ceduliocezar.com.domain.logging.Logger;
import io.reactivex.Single;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.TestObserver;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test suite {@link CloudDrinkDataSource}
 */
public class CloudDrinkDataSourceTest {
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @InjectMocks
    private CloudDrinkDataSource dataSource;

    @Mock
    private MenuService menuService;

    @Mock
    @SuppressWarnings("unused")
    private Logger logger;

    @Mock
    private DrinkEntity drinkEntity;

    @Test
    public void test_list() {

        final List<DrinkEntity> list = new ArrayList<>();
        list.add(drinkEntity);
        list.add(drinkEntity);
        list.add(drinkEntity);


        when(menuService.listDrinks()).thenReturn(Single.just(list));

        TestObserver<List<DrinkEntity>> observer = dataSource.list().test();

        observer.awaitTerminalEvent();

        observer.assertNoErrors().assertValue(new Predicate<List<DrinkEntity>>() {
            @Override
            public boolean test(List<DrinkEntity> drinkEntities) {
                return drinkEntities == list;
            }
        });

        verify(menuService).listDrinks();
    }
}