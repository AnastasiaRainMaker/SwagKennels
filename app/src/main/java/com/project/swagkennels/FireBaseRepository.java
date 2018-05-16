package com.project.swagkennels;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.swagkennels.presenters.NewsPresenter;

import java.util.ArrayList;

public class FireBaseRepository {

    public void getNews(final NewsPresenter.NewsCallbacks listener) {
        FirebaseDatabase.getInstance().getReference().child("news").getRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList data = new ArrayList<News>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    data.add(dataSnapshot1.getValue(News.class));
                }
                listener.onDataLoaded(data);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                listener.onDataLoaded(new ArrayList<News>());
            }
        });
    }

}
