package ceduliocezar.com.nennospizza.presentation.pizza.list;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ceduliocezar.com.nennospizza.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class PizzaListFragment extends Fragment {

    public PizzaListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
}
