package ceduliocezar.com.nennospizza.presentation.cart;

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

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.ViewHolder> {

    private final Context context;
    private List<CartItemModel> cartItems;

    @Nullable
    private OnItemClick onItemClick;

    @Inject
    public CartItemAdapter(@Named("applicationContext") Context context) {
        this.cartItems = Collections.emptyList();
        this.context = context;
    }

    @NonNull
    @Override
    public CartItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_cart_item_view, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final CartItemModel cartItemModel = cartItems.get(position);

        holder.titleTv.setText(cartItemModel.getTitle());
        holder.priceTv.setText(String.format(context.getString(R.string.price_format), cartItemModel.getPrice()));


        holder.deleteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick != null) {
                    onItemClick.onClickDeleteItemFromCart(cartItemModel);
                }
            }
        });
    }

    public void setOnItemClick(@Nullable OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public void setCartItems(List<CartItemModel> cartItems) {
        this.cartItems = cartItems;
        notifyDataSetChanged();
    }

    interface OnItemClick {

        void onClickDeleteItemFromCart(CartItemModel cartItemModel);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView titleTv;
        TextView priceTv;
        ImageView deleteImageView;

        ViewHolder(View v) {
            super(v);
            titleTv = v.findViewById(R.id.cart_item_title_tv);
            priceTv = v.findViewById(R.id.cart_item_price_tv);
            deleteImageView = v.findViewById(R.id.cart_item_delete_iv);
        }
    }
}
