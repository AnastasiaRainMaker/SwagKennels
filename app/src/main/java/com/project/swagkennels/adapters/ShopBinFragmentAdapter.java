package com.project.swagkennels.adapters;

import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.project.swagkennels.R;
import com.project.swagkennels.room.PurchasedShopItem;

import java.util.ArrayList;

public class ShopBinFragmentAdapter extends RecyclerView.Adapter<ShopBinFragmentAdapter.ViewHolder> {
    private ArrayList<PurchasedShopItem> dataList;
    private ShopBinAdapterCallback listener;

    public ShopBinFragmentAdapter(ArrayList<PurchasedShopItem> data, ShopBinAdapterCallback listener) {
        this.dataList = data;
        this.listener = listener;
    }

    public void setData(ArrayList<PurchasedShopItem> data) {
        this.dataList = data;
    }

    public void removeItem(PurchasedShopItem item) {
        dataList.remove(item);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView, imageViewBin;
        TextView textViewTitle, textViewSize, textViewPrice;
        CardView card;

        ViewHolder(View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.imageView);
            this.imageViewBin = itemView.findViewById(R.id.imageViewBin);
            this.textViewTitle = itemView.findViewById(R.id.textViewTitle);
            this.textViewSize = itemView.findViewById(R.id.textViewSize);
            this.textViewPrice = itemView.findViewById(R.id.textViewPrice);
            this.card = itemView.findViewById(R.id.card);
        }
    }

    @NonNull
    @Override
    public ShopBinFragmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_item_recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ShopBinFragmentAdapter.ViewHolder holder, final int position) {
        final PurchasedShopItem item = dataList.get(position);
        if(item != null) {

            holder.imageViewBin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onBinIconClicked(item);
                }
            });

            if(item.getImage() == null) {
                holder.imageView.setBackgroundResource(R.drawable.dog_img);
            } else {
                RequestOptions requestOptions = new RequestOptions();
                requestOptions = requestOptions.placeholder(new ColorDrawable(holder.imageView.getContext().getResources().getColor(R.color.lightGrey)));
                requestOptions = requestOptions.error(new ColorDrawable(holder.imageView.getContext().getResources().getColor(R.color.colorPrimary)));
                requestOptions = requestOptions.centerCrop();

                Glide.with(holder.imageView.getContext())
                        .setDefaultRequestOptions(requestOptions)
                        .load(item.getImage())
                        .into(holder.imageView);
            }

            if (item.getTitle() == null) {
                holder.textViewTitle.setVisibility(View.GONE);
            } else {
                holder.textViewTitle.setText(item.getTitle());
            }
            if(item.getSize() != null) {
                holder.textViewSize.setText(item.getSize());
            } else {
                holder.textViewSize.setVisibility(View.GONE);
            }
            if (item.getPrice() != null) {
                holder.textViewPrice.setText(item.getPrice());
            } else {
                holder.textViewPrice.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public interface ShopBinAdapterCallback {
        void onBinIconClicked(PurchasedShopItem item);
    }
}

