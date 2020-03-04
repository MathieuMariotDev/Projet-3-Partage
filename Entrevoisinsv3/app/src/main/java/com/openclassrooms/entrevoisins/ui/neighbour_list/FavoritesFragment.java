package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Optional;

public class FavoritesFragment extends Fragment {

    @BindView(R.id.list_neighbours_favorites) RecyclerView mRecyclerViewFavorite; // declare recycler view
    private List<Neighbour> neighbourFavorites;  // ne pas oublier ArrayList
    private MyNeighbourRecyclerViewAdapter adapterfav; //adapter for recycler view
    private NeighbourApiService mApiservice;  //interface///
    boolean favorite;




    public static FavoritesFragment newInstance() {
        FavoritesFragment fragment = new FavoritesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiservice = DI.getNeighbourApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        ButterKnife.bind(this, view); //for bindview
        configureRecyclerView();
        return view;

    }

 private void configureRecyclerView(){
        neighbourFavorites = new ArrayList<>();
        List<Neighbour> Neighbourstempo;
        Neighbourstempo=mApiservice.getNeighbours();
        for(int i=0;i<Neighbourstempo.size();i++){
            Neighbour neighbour=Neighbourstempo.get(i);
            favorite=neighbour.getFavorite();
            if(favorite) {
                neighbourFavorites.add(neighbour);
            }
        }
        this.adapterfav = new MyNeighbourRecyclerViewAdapter(neighbourFavorites,2);
        this.mRecyclerViewFavorite.setAdapter(this.adapterfav);
        this.mRecyclerViewFavorite.setLayoutManager(new LinearLayoutManager(getContext()));
 }



}
