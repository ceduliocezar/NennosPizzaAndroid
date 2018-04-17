package ceduliocezar.com.data.repository.drink;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ceduliocezar.com.data.entity.DrinkEntity;
import ceduliocezar.com.data.repository.drink.datasource.DrinkDataSource;
import ceduliocezar.com.domain.Drink;
import ceduliocezar.com.domain.logging.Logger;
import io.reactivex.Single;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.TestObserver;

import static org.mockito.Mockito.when;

/**
 * Test suite for {@link DrinkDataRepository}
 */
public class DrinkDataRepositoryTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @InjectMocks
    private DrinkDataRepository repository;

    @Mock
    private DrinkDataSource dataSource;

    @Mock
    private DrinkEntityMapper drinkEntityMapper;


    @Mock
    @SuppressWarnings("unused")
    private Logger logger;

    @Mock
    private Drink drink;
    @Mock
    private DrinkEntity drinkEntity;

    @Test
    public void test_list() {

        when(dataSource.list()).thenReturn(Single.just(Arrays.asList(drinkEntity, drinkEntity)));
        final List<Drink> drinksList = new ArrayList<>();
        drinksList.add(drink);
        drinksList.add(drink);
        when(drinkEntityMapper.transform(ArgumentMatchers.<DrinkEntity>anyList())).thenReturn(drinksList);

        TestObserver<List<Drink>> observer = repository.list().test();
        observer.awaitTerminalEvent();

        observer.assertNoErrors()
                .assertValue(new Predicate<List<Drink>>() {
                    @Override
                    public boolean test(List<Drink> drinks) {
                        return drinks == drinksList && drinks.size() == 2;
                    }
                });
    }

}