package com.project.swagkennels.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.swagkennels.Item;
import com.project.swagkennels.R;

import java.util.ArrayList;

public class ShopFragmentAdapter extends RecyclerView.Adapter<ShopFragmentAdapter.ViewHolder> {

    private ArrayList<Item> items;

    public ShopFragmentAdapter(ArrayList<Item> items) {
        this.items = items;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewDescription;
        TextView price;
        CardView card;

        ViewHolder(View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.image);
            this.textViewDescription = itemView.findViewById(R.id.description);
            this.price = itemView.findViewById(R.id.price);
            this.card = itemView.findViewById(R.id.card);
        }
    }

    @NonNull
    @Override
    public ShopFragmentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_recyclerview_item, parent, false);
        return new ShopFragmentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ShopFragmentAdapter.ViewHolder holder, final int position) {
        final Item i = items.get(position);
        if (i != null) {
            if (i.getImageUrl() != null) {
                //load image here;
            } else {
                //handle no url
                holder.imageView.setBackgroundResource(R.drawable.no_item_image);
            }
            if (i.getDescription() != null) {
                holder.textViewDescription.setText(i.getDescription());
            } else {
                holder.textViewDescription.setText(R.string.no_price_str);
            }
            if (i.getPrice() != null) {
                holder.price.setText(i.getPrice());
            } else {
                holder.price.setText(R.string.no_price_str);
            }
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
