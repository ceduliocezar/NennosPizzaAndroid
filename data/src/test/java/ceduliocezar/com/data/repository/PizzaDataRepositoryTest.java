package ceduliocezar.com.data.repository;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import ceduliocezar.com.data.entity.PizzaEntity;
import ceduliocezar.com.data.internet.InternetChecker;
import ceduliocezar.com.data.repository.datasource.PizzaDataSource;
import io.reactivex.Observable;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test suite for {@link PizzaDataRepository}
 * Created by cedulio.silva on 4/12/2018.
 */

public class PizzaDataRepositoryTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @InjectMocks
    private PizzaDataRepository pizzaDataRepository;

    @Mock
    @Named("disk")
    private PizzaDataSource diskPizzaDataSource;

    @Mock
    @Named("cloud")
    private PizzaDataSource cloudPizzaDataSource;

    @Mock
    private InternetChecker internetChecker;

    @Mock
    private Observable<List<PizzaEntity>> observable;

    @Mock
    private PizzaEntity pizzaEntity;

    @Test
    public void test_listOnline() {
        List<PizzaEntity> pizzas = new ArrayList<>();
        pizzas.add(pizzaEntity);
        pizzas.add(pizzaEntity);
        pizzas.add(pizzaEntity);

        when(internetChecker.isDeviceOnline()).thenReturn(true);
        when(cloudPizzaDataSource.pizzaList()).thenReturn(Observable.just(pizzas));

        pizzaDataRepository.list();

        verify(cloudPizzaDataSource, only()).pizzaList();
        verify(cloudPizzaDataSource).pizzaList();

    }

    @Test
    public void test_listOffline() {

        List<PizzaEntity> pizzas = new ArrayList<>();
        pizzas.add(pizzaEntity);
        pizzas.add(pizzaEntity);
        pizzas.add(pizzaEntity);

        when(internetChecker.isDeviceOnline()).thenReturn(false);
        when(diskPizzaDataSource.pizzaList()).thenReturn(Observable.just(pizzas));


        pizzaDataRepository.list();

        verify(diskPizzaDataSource, only()).pizzaList();
        assertEquals(this.observable, observable);
    }

}
