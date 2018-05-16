package com.project.swagkennels.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.swagkennels.Dog;
import com.project.swagkennels.R;

import java.util.ArrayList;

public class PuppyFragmentAdapter extends RecyclerView.Adapter<PuppyFragmentAdapter.ViewHolder> {
    private ArrayList<Dog> dataList;
    private Context context;

    public PuppyFragmentAdapter(ArrayList<Dog> data, Context context) {
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

    @NonNull
    @Override
    public PuppyFragmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.puppy_recyclerview_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PuppyFragmentAdapter.ViewHolder holder, final int position) {
        final Dog item = dataList.get(position);
        if(item != null) {

//            holder.card.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(context, NewsItemActivity.class);
//
//                  //  intent.putExtra("imageUrl", "");
//                    intent.putExtra("description", item.getDescription());
//                    intent.putExtra("title", item.getTitle());
//                    intent.putExtra("imgUrl", item.getImageUrl());
//
//                    String transitionName = context.getString(R.string.transition_shared_element);
//
//                    ActivityOptionsCompat options =
//                            ActivityOptionsCompat.makeSceneTransitionAnimation((NewsListActivity) context,
//                                    holder.imageView,   // Starting view
//                                    transitionName    // The String
//                            );
//                    ActivityCompat.startActivity(context, intent, options.toBundle());
//                }
//            });

            if(item.getImageUrl() == null) {
                holder.imageView.setBackgroundResource(R.drawable.dog_img);
            } else {
                //use link to load pic into view
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

