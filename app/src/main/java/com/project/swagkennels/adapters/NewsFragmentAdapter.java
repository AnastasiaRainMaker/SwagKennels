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
import com.project.swagkennels.pojo.News;
import com.project.swagkennels.NewsItemActivity;
import com.project.swagkennels.NewsListActivity;
import com.project.swagkennels.R;

import java.util.ArrayList;

public class NewsFragmentAdapter extends RecyclerView.Adapter<NewsFragmentAdapter.ViewHolder> {
    private ArrayList<News> dataList;
    private Context context;

    public NewsFragmentAdapter(ArrayList<News> data, Context context) {
        this.dataList = data;
        this.context = context;
    }

    public void setData(ArrayList<News> data) {
        this.dataList = data;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewDate;
        TextView textViewDescription;
        TextView textViewLink;
        CardView card;

        ViewHolder(View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.image);
            this.textViewDescription = itemView.findViewById(R.id.description);
            this.textViewDate = itemView.findViewById(R.id.date);
            this.textViewLink = itemView.findViewById(R.id.link);
            this.card = itemView.findViewById(R.id.card);
        }
    }

    @NonNull
    @Override
    public NewsFragmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_recyclerview_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NewsFragmentAdapter.ViewHolder holder, final int position) {
        final News item = dataList.get(position);
        if(item != null) {

            holder.card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, NewsItemActivity.class);

                  //  intent.putExtra("imageUrl", "");
                    intent.putExtra("description", item.getDescription());
                    intent.putExtra("date", item.getDate());
                    intent.putExtra("youtubelink", item.getYoutubeLink());

                    String transitionName = context.getString(R.string.transition_shared_element);

                    ActivityOptionsCompat options =
                            ActivityOptionsCompat.makeSceneTransitionAnimation((NewsListActivity) context,
                                    holder.imageView,   // Starting view
                                    transitionName    // The String
                            );
                    ActivityCompat.startActivity(context, intent, options.toBundle());
                }
            });

            if(item.getImageLink() == null) {
                holder.imageView.setBackgroundResource(R.drawable.dog_img);
            } else {
                RequestOptions requestOptions = new RequestOptions();
                requestOptions = requestOptions.placeholder(new ColorDrawable(holder.imageView.getContext().getResources().getColor(R.color.lightGrey)));
                requestOptions = requestOptions.error(new ColorDrawable(holder.imageView.getContext().getResources().getColor(R.color.black)));
                requestOptions = requestOptions.centerCrop();

                Glide.with(holder.imageView.getContext())
                        .setDefaultRequestOptions(requestOptions)
                        .load(item.getImageLink())
                        .into(holder.imageView);
            }
            if (item.getDate() == null) {
                holder.textViewDate.setVisibility(View.GONE);
            } else {
                holder.textViewDate.setText(item.getDate());
            }
            if(item.getDescription() != null) {
                holder.textViewDescription.setText(item.getDescription());
            } else {
                holder.textViewDescription.setVisibility(View.GONE);
            }
            if (item.getYoutubeLink() != null) {
                holder.textViewLink.setText(item.getYoutubeLink());
            } else {
                holder.textViewLink.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}

