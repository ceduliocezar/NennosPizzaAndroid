package ceduliocezar.com.domain.interactor.drink;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Arrays;
import java.util.List;

import ceduliocezar.com.domain.Drink;
import ceduliocezar.com.domain.repository.DrinkRepository;
import ceduliocezar.com.domain.threading.PostExecutionThread;
import ceduliocezar.com.domain.threading.ThreadExecutor;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.TestObserver;

import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test suite for {@link GetDrinkList}
 */
public class GetDrinkListTest {
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @InjectMocks
    private GetDrinkList getDrinkList;

    @Mock
    private DrinkRepository repository;

    @Mock
    @SuppressWarnings("unused")
    private ThreadExecutor threadExecutor;

    @Mock
    @SuppressWarnings("unused")
    private PostExecutionThread postExecutionThread;

    @Mock
    @SuppressWarnings("unused")
    private Scheduler scheduler;

    @Mock
    private Drink drink;


    @Test
    public void test_buildUseCaseObservable() {

        when(repository.list()).thenReturn(Single.just(Arrays.asList(drink, drink)));

        TestObserver<List<Drink>> observer = getDrinkList.buildUseCaseObservable(null).test();

        observer.awaitTerminalEvent();

        observer.assertNoErrors().assertValue(new Predicate<List<Drink>>() {
            @Override
            public boolean test(List<Drink> drinks) {
                return drinks.size() == 2;
            }
        });

        verify(repository, only()).list();
    }
}
