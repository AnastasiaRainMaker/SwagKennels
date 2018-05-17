package com.project.swagkennels.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.project.swagkennels.DogItemActivity;
import com.project.swagkennels.NewsListActivity;
import com.project.swagkennels.pojo.Dog;
import com.project.swagkennels.R;


import java.util.ArrayList;

public class DogsFragmentAdapter extends RecyclerView.Adapter<DogsFragmentAdapter.ViewHolder> {
    private ArrayList<Dog> dataList;
    private Context context;

    public DogsFragmentAdapter(ArrayList<Dog> data, Context context) {
        this.dataList = data;
        this.context = context;
    }
    public void setData(ArrayList<Dog> data) {
        this.dataList = data;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewDescription;
        TextView titleView;
        CardView card;

        ViewHolder(View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.image);
            this.textViewDescription = itemView.findViewById(R.id.description);
            this.titleView = itemView.findViewById(R.id.title);
            this.card = itemView.findViewById(R.id.card);
        }
    }

    @NonNull
    @Override
    public DogsFragmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dogs_recyclerview_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DogsFragmentAdapter.ViewHolder holder, final int position) {
        final Dog item = dataList.get(position);
        if(item != null) {
            holder.card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DogItemActivity.class);
                    intent.putExtra("imageUrl", item.getImageUrl());
                    intent.putExtra("description", item.getDescription());
                    intent.putExtra("title", item.getTitle());
                    String transitionName = context.getString(R.string.transition_shared_element);

                    ActivityOptionsCompat options =
                            ActivityOptionsCompat.makeSceneTransitionAnimation((NewsListActivity) context,
                                    holder.imageView,   // Starting view
                                    transitionName    // The String
                            );
                    ActivityCompat.startActivity(context, intent, options.toBundle());
                }
            });

            if(item.getImageUrl() == null) {
                holder.imageView.setBackgroundResource(R.drawable.milady);
            } else {
                RequestOptions requestOptions = new RequestOptions();
                requestOptions = requestOptions.placeholder(new ColorDrawable(holder.imageView.getContext().getResources().getColor(R.color.lightGrey)));
                requestOptions = requestOptions.error(new ColorDrawable(holder.imageView.getContext().getResources().getColor(R.color.colorPrimary)));
                requestOptions = requestOptions.centerCrop();

                Glide.with(holder.imageView.getContext())
                        .setDefaultRequestOptions(requestOptions)
                        .load(item.getImageUrl())
                        .into(holder.imageView);
            }
            if (item.getTitle() == null) {
                holder.titleView.setVisibility(View.GONE);
            } else {
                holder.titleView.setText(item.getTitle());
            }
            if(item.getDescription() != null) {
                holder.textViewDescription.setText(item.getDescription());
            } else {
                holder.textViewDescription.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}

