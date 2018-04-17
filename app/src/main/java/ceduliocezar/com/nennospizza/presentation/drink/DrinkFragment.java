package ceduliocezar.com.nennospizza.presentation.drink;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ceduliocezar.com.domain.logging.Logger;
import ceduliocezar.com.nennospizza.CustomApplication;
import ceduliocezar.com.nennospizza.R;


public class DrinkFragment extends Fragment implements DrinksContract.View {

    private static final String TAG = "DrinkFragment";

    @Inject
    DrinksContract.Presenter presenter;

    @Inject
    Logger logger;

    @BindView(R.id.drink_recycler)
    RecyclerView recyclerView;

    @BindView(R.id.loading_container)
    View loadingContainer;

    @BindView(R.id.loader)
    AVLoadingIndicatorView avLoadingIndicatorView;


    private DrinkAdapter drinkAdapter;


    public DrinkFragment() {
        // Required empty public constructor
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
        View view = inflater.inflate(R.layout.fragment_drink, container, false);
        ButterKnife.bind(this, view);
        initRecyclerView();

        return view;
    }

    private void initRecyclerView() {
        drinkAdapter = new DrinkAdapter(getActivity());
        drinkAdapter.setOnItemClick(new DrinkAdapter.OnItemClick() {
            @Override
            public void onClickAddDrink(DrinkModel drinkModel) {
                logger.debug(TAG, "onClickAddDrink:" + drinkModel.toString());
                presenter.onClickAddDrink(drinkModel);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(drinkAdapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        logger.debug(TAG, "onViewCreated");
        presenter.setView(this);
        presenter.init();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        logger.debug(TAG, "onDestroyView: ");
        presenter.viewDestroyed();
    }


    private void showLoader() {
        loadingContainer.setVisibility(View.VISIBLE);
        avLoadingIndicatorView.setVisibility(View.VISIBLE);
        avLoadingIndicatorView.show();
    }


    private void hideLoader() {
        loadingContainer.setVisibility(View.GONE);
        avLoadingIndicatorView.setVisibility(View.GONE);
        avLoadingIndicatorView.hide();
    }

    @Override
    public void showDrinks(List<DrinkModel> drinks) {
        logger.debug(TAG, "showDrinks");
        drinkAdapter.setDrinks(drinks);
    }

    @Override
    public void showLoadingDrinks() {
        logger.debug(TAG, "showLoadingDrinks");
        showLoader();
    }

    @Override
    public void hideLoadingDrinks() {
        logger.debug(TAG, "hideLoadingDrinks");
        hideLoader();
    }

    @Override
    public void showNotificationDrinkAdded() {
        logger.debug(TAG, "showNotificationDrinkAdded");

        Snackbar snackbar = Snackbar
                .make(getView(), R.string.add_to_cart, Snackbar.LENGTH_SHORT);


        snackbar.setDuration(3000);

        snackbar.show();
    }

    @Override
    public void showErrorMessageOnAddDrink() {
        logger.debug(TAG, "showErrorMessageOnAddDrink");
        Toast.makeText(getActivity(), getString(R.string.problem_adding_drink_to_cart), Toast.LENGTH_SHORT).show();
    }


}
