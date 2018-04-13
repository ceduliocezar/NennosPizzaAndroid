package ceduliocezar.com.nennospizza.presentation.pizza.list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import ceduliocezar.com.nennospizza.R;

/**
 * Pizza adapter that shows an image and a list of ingredients.
 * Created by cedulio.silva on 4/12/2018.
 */

public class PizzasAdapter extends RecyclerView.Adapter<PizzasAdapter.ViewHolder> {

    private final Context context;
    private List<PizzaModel> pizzas;

    @Nullable
    private OnItemClick onItemClick;

    @Inject
    public PizzasAdapter(@Named("applicationContext") Context context) {
        this.pizzas = Collections.emptyList();
        this.context = context;
    }

    @Override
    public PizzasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_pizza_view, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final PizzaModel pizzaModel = pizzas.get(position);

        holder.pizzaNameTv.setText(pizzaModel.getName());
        holder.ingredientsTv.setText(toCSV(pizzaModel.getIngredients()));
        holder.priceTv.setText(String.format(context.getString(R.string.price_format), pizzaModel.getTotalPrice()));

        Glide.with(context)
                .load(pizzaModel.getImageUrl())
                .into(holder.pizzaImageView);

        holder.addToCartView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClick != null) {
                    onItemClick.onClickAddToCart(pizzaModel);
                }
            }
        });
    }

    public void setOnItemClick(@Nullable OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    private String toCSV(List<String> ingredients) {
        StringBuilder csvBuilder = new StringBuilder();
        String separator = ",";
        for (String ingredient : ingredients) {
            csvBuilder.append(ingredient);
            csvBuilder.append(separator);
        }

        return csvBuilder.toString();
    }

    @Override
    public int getItemCount() {
        return pizzas.size();
    }

    public void setPizzas(List<PizzaModel> pizzas) {
        this.pizzas = pizzas;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView pizzaNameTv;
        TextView ingredientsTv;
        TextView priceTv;
        ImageView pizzaImageView;
        View addToCartView;

        ViewHolder(View v) {
            super(v);
            pizzaNameTv = v.findViewById(R.id.pizza_name_tv);
            ingredientsTv = v.findViewById(R.id.pizza_ingredients_tv);
            priceTv = v.findViewById(R.id.pizza_price_tv);
            pizzaImageView = v.findViewById(R.id.pizza_image);
            addToCartView = v.findViewById(R.id.add_to_cart_view);
        }
    }

    interface OnItemClick {

        void onClickAddToCart(PizzaModel pizzaModel);
    }
}
