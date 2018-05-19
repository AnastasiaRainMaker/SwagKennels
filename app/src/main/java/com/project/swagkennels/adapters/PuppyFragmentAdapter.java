package com.project.swagkennels.adapters;

import android.content.Context;
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
import com.project.swagkennels.pojo.Dog;
import com.project.swagkennels.R;
import com.project.swagkennels.pojo.News;
import com.project.swagkennels.pojo.Puppy;

import java.util.ArrayList;

public class PuppyFragmentAdapter extends RecyclerView.Adapter<PuppyFragmentAdapter.ViewHolder> {
    private ArrayList<Puppy> dataList;
    private Context context;

    public PuppyFragmentAdapter(ArrayList<Puppy> data, Context context) {
        this.dataList = data;
        this.context = context;
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

    public void setData(ArrayList<Puppy> data) {this.dataList = data;}

    @NonNull
    @Override
    public PuppyFragmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.puppy_recyclerview_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PuppyFragmentAdapter.ViewHolder holder, final int position) {
        final Puppy item = dataList.get(position);
        if(item != null) {
            if(item.getImageUrl() == null) {
                holder.imageView.setBackgroundResource(R.drawable.dog_img);
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
            if (item.getName() == null) {
                holder.titleView.setVisibility(View.GONE);
            } else {
                holder.titleView.setText(item.getName());
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

