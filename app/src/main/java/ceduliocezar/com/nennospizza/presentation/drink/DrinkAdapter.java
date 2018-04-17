package ceduliocezar.com.nennospizza.presentation.drink;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import ceduliocezar.com.nennospizza.R;

/**
 * Pizza adapter that shows an image and a list of ingredients.
 * Created by cedulio.silva on 4/12/2018.
 */

public class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.ViewHolder> {

    private final Context context;
    private List<DrinkModel> drinks;

    @Nullable
    private OnItemClick onItemClick;

    @Inject
    public DrinkAdapter(@Named("applicationContext") Context context) {
        this.drinks = Collections.emptyList();
        this.context = context;
    }

    @NonNull
    @Override
    public DrinkAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_drink_view, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final DrinkModel drinkModel = drinks.get(position);

        holder.nameTv.setText(drinkModel.getDrink());
        holder.priceTv.setText(String.format(context.getString(R.string.price_format), drinkModel.getPrice()));


        holder.addImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick != null) {
                    onItemClick.onClickAddDrink(drinkModel);
                }
            }
        });
    }

    public void setOnItemClick(@Nullable OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @Override
    public int getItemCount() {
        return drinks.size();
    }

    public void setDrinks(List<DrinkModel> drinks) {
        this.drinks = drinks;
        notifyDataSetChanged();
    }

    interface OnItemClick {

        void onClickAddDrink(DrinkModel drinkModel);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameTv;
        TextView priceTv;
        ImageView addImageView;

        ViewHolder(View v) {
            super(v);
            nameTv = v.findViewById(R.id.drink_name_tv);
            priceTv = v.findViewById(R.id.drink_price_tv);
            addImageView = v.findViewById(R.id.drink_add_iv);
        }
    }
}
