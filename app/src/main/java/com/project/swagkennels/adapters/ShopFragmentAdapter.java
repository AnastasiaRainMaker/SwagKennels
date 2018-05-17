package com.project.swagkennels.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.swagkennels.pojo.Item;
import com.project.swagkennels.ItemDetailsActivity;
import com.project.swagkennels.NewsListActivity;
import com.project.swagkennels.R;

import java.util.ArrayList;

public class ShopFragmentAdapter extends RecyclerView.Adapter<ShopFragmentAdapter.ViewHolder> {

    private ArrayList<Item> items;
    private Context context;

    public ShopFragmentAdapter(ArrayList<Item> items, Context context) {
        this.items = items;
        this.context = context;
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
        final Item item = items.get(position);
        if (item != null) {
            holder.card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ItemDetailsActivity.class);

                    //  intent.putExtra("imageUrl", "");
                    intent.putExtra("description", item.getDescription());
                    intent.putExtra("price", item.getPrice());

                    String transitionName = context.getString(R.string.transition_shared_element);

                    ActivityOptionsCompat options =
                            ActivityOptionsCompat.makeSceneTransitionAnimation((NewsListActivity) context,
                                    holder.imageView,   // Starting view
                                    transitionName    // The String
                            );
                    ActivityCompat.startActivity(context, intent, options.toBundle());
                }
            });
            if (item.getImageUrl() != null) {
                //load image here;
            } else {
                //handle no url
                holder.imageView.setBackgroundResource(R.drawable.no_item_image);
            }
            if (item.getDescription() != null) {
                holder.textViewDescription.setText(item.getDescription());
            } else {
                holder.textViewDescription.setText(R.string.no_price_str);
            }
            if (item.getPrice() != null) {
                holder.price.setText(item.getPrice());
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
