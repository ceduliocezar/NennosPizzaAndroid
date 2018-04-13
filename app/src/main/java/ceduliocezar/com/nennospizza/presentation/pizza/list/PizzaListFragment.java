package ceduliocezar.com.nennospizza.presentation.pizza.list;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ceduliocezar.com.domain.logging.AppLogger;
import ceduliocezar.com.nennospizza.CustomApplication;
import ceduliocezar.com.nennospizza.R;

/**
 * Fragment that shows a list of Pizzas.
 * Created by cedulio.silva on 4/12/2018.
 */
public class PizzaListFragment extends Fragment implements PizzaListContract.View {

    private static final String TAG = "PizzaListFragment";
    private static final int PERMISSIONS_REQUEST_INTERNET = 1;

    @Inject
    PizzaListContract.Presenter presenter;

    @Inject
    PizzasAdapter pizzasAdapter;

    @Inject
    AppLogger logger;

    @BindView(R.id.pizzaRecyclerView)
    RecyclerView recyclerView;

    public PizzaListFragment() {
        // mandatory android constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((CustomApplication) getActivity().getApplication()).component().inject(this);
        logger.debug(TAG, "onCreate");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        logger.debug(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        initRecyclerView();
        return view;
    }

    private void initRecyclerView() {
        List<PizzaModel> pizzas = new ArrayList<>();
        List<String> ingredients = new ArrayList<>();
        ingredients.add("ABCS");
        ingredients.add("ASDASKK");
        ingredients.add("dasdds");
        ingredients.add("Acoroerjdm");


        PizzaModel pizzaModel = new PizzaModel("id", "NAmsadasmna Name", ingredients, 25.50, "http://via.placeholder.com/350x150");

        pizzas.add(pizzaModel);
        pizzas.add(pizzaModel);
        pizzas.add(pizzaModel);
        pizzas.add(pizzaModel);

        pizzasAdapter.setPizzas(pizzas);
        pizzasAdapter.setOnItemClick(new PizzasAdapter.OnItemClick() {
            @Override
            public void onClickAddToCart(PizzaModel pizzaModel) {
                logger.debug(TAG, "onClickAddToCart:" + pizzaModel.toString());
                // TODO: 4/13/2018
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(pizzasAdapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        logger.debug(TAG, "onViewCreated");
        presenter.setView(this);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (PERMISSIONS_REQUEST_INTERNET == requestCode) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                presenter.init();
            }
        }
    }

    @Override
    public void showPizzas(List<PizzaModel> pizzas) {
        logger.debug(TAG, "showPizzas: " + pizzas.toString());
        pizzasAdapter.setPizzas(pizzas);
    }
}
