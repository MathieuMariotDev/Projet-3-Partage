package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;

import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Optional;

import static android.support.constraint.Constraints.TAG;

public class FavoritesFragment extends Fragment {

    @BindView(R.id.list_neighbours_favorites) RecyclerView mRecyclerViewFavorite; // declare recycler view
    private MyNeighbourRecyclerViewAdapter adapterfav; //adapter for recycler view
    private NeighbourApiService mApiservice;  //interface///
    public List<Neighbour> neighbourFavorites = new ArrayList<>();




    public static FavoritesFragment newInstance() {
        FavoritesFragment fragment = new FavoritesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: /////////////");
        mApiservice = DI.getNeighbourApiService();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        ButterKnife.bind(this, view); //for bindview
        this.mRecyclerViewFavorite.setLayoutManager(new LinearLayoutManager(getContext()));
        initListFav();
        return view;

    }


    public void initListFav() {
        neighbourFavorites=mApiservice.getNeighboursFavorites();
        mRecyclerViewFavorite.setAdapter(new MyNeighbourRecyclerViewAdapter(neighbourFavorites,2));
    }

    @Override
    public void onResume() {
        initListFav();
        Log.d(TAG, "onResume://///////");
        super.onResume();
    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe
    public void onDeleteNeighbour(DeleteNeighbourEvent event) {
        //mApiservice.deleteNeighbour(event.neighbour);
        initListFav();
    }
}
