package com.project.swagkennels.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.project.swagkennels.BreedingItemActivity;
import com.project.swagkennels.NewsListActivity;
import com.project.swagkennels.R;
import com.project.swagkennels.pojo.Breeding;
import java.util.ArrayList;

public class BreedingFragmentAdapter extends RecyclerView.Adapter<BreedingFragmentAdapter.ViewHolder> {

    private ArrayList<Breeding> dataList;
    private Context context;

    public BreedingFragmentAdapter(ArrayList<Breeding> data, Context context) {
        this.dataList = data;
        this.context = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewMale;
        ImageView imageViewFemale;
        TextView titleView;
        CardView card;

        ViewHolder(View itemView) {
            super(itemView);
            this.imageViewMale = itemView.findViewById(R.id.imageMale);
            this.imageViewFemale = itemView.findViewById(R.id.imageFemale);
            this.titleView = itemView.findViewById(R.id.title);
            this.card = itemView.findViewById(R.id.card);
        }
    }

    public void setData(ArrayList<Breeding> data) {this.dataList = data;}

    @NonNull
    @Override
    public BreedingFragmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.breeding_recyclerview_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final BreedingFragmentAdapter.ViewHolder holder, final int position) {
        final Breeding item = dataList.get(position);
        if(item != null) {
            holder.card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, BreedingItemActivity.class);
                    intent.putExtra("breedingItem", dataList.get(position));
                    context.startActivity(intent);
                }
            });

            if(item.getImageUrlMale() == null) {
                holder.imageViewMale.setBackgroundResource(R.drawable.dog_img);
            } else {
                RequestOptions requestOptions = new RequestOptions();
                requestOptions = requestOptions.placeholder(new ColorDrawable(holder.imageViewMale.getContext().getResources().getColor(R.color.lightGrey)));
                requestOptions = requestOptions.error(new ColorDrawable(holder.imageViewMale.getContext().getResources().getColor(R.color.colorPrimary)));
                requestOptions = requestOptions.centerCrop();

                Glide.with(holder.imageViewMale.getContext())
                        .setDefaultRequestOptions(requestOptions)
                        .load(item.getImageUrlMale())
                        .into(holder.imageViewMale);
            }
            if(item.getImageUrlFemale() == null) {
                holder.imageViewFemale.setBackgroundResource(R.drawable.dog_img);
            } else {
                RequestOptions requestOptions = new RequestOptions();
                requestOptions = requestOptions.placeholder(new ColorDrawable(holder.imageViewFemale.getContext().getResources().getColor(R.color.lightGrey)));
                requestOptions = requestOptions.error(new ColorDrawable(holder.imageViewFemale.getContext().getResources().getColor(R.color.colorPrimary)));
                requestOptions = requestOptions.centerCrop();

                Glide.with(holder.imageViewFemale.getContext())
                        .setDefaultRequestOptions(requestOptions)
                        .load(item.getImageUrlMale())
                        .into(holder.imageViewFemale);
            }
            if (item.getTitle() == null) {
                holder.titleView.setVisibility(View.GONE);
            } else {
                holder.titleView.setText(item.getTitle());
            }
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
