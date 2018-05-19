package com.project.swagkennels;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.swagkennels.pojo.Breeding;
import com.project.swagkennels.pojo.Dog;
import com.project.swagkennels.pojo.News;
import com.project.swagkennels.pojo.Puppy;
import com.project.swagkennels.presenters.BreedingPresenter;
import com.project.swagkennels.presenters.DogsPresenter;
import com.project.swagkennels.presenters.NewsPresenter;
import com.project.swagkennels.presenters.PuppyPresenter;

import java.util.ArrayList;

public class FireBaseRepositoryImpl implements FireBaseRepository {

    @Override
    public void getNews(final NewsPresenter.NewsCallbacks listener) {
        FirebaseDatabase.getInstance().getReference().child("news").getRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<News> data = new ArrayList<>();
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

    @Override
    public void getDogs(final DogsPresenter.DogsCallbacks listener) {
        FirebaseDatabase.getInstance().getReference().child("dogs").getRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Dog> data = new ArrayList<>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    data.add(dataSnapshot1.getValue(Dog.class));
                }
                listener.onDataLoaded(data);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                listener.onDataLoaded(new ArrayList<Dog>());
            }
        });
    }

    @Override
    public void getPuppies(final PuppyPresenter.PuppyCallback listener) {
        FirebaseDatabase.getInstance().getReference().child("puppies").getRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Puppy> data = new ArrayList<>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    data.add(dataSnapshot1.getValue(Puppy.class));
                }
                listener.onDataLoaded(data);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                listener.onDataLoaded(new ArrayList<Puppy>());
            }
        });
    }

    @Override
    public void getBreedings(final BreedingPresenter.BreedingCallback listener) {
        FirebaseDatabase.getInstance().getReference().child("breedings").getRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Breeding> data = new ArrayList<>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    data.add(dataSnapshot1.getValue(Breeding.class));
                }
                listener.onDataLoaded(data);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                listener.onDataLoaded(new ArrayList<Breeding>());
            }
        });
    }

    @Override
    public void getShopItems() {

    }
}
