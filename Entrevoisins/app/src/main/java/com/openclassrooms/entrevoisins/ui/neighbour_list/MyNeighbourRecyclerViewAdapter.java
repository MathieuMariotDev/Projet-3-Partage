package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;

import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;



public class MyNeighbourRecyclerViewAdapter extends RecyclerView.Adapter<MyNeighbourRecyclerViewAdapter.ViewHolder> {

    private boolean Setvisibilitybtn=true;
    private final List<Neighbour> mNeighbours;

    public MyNeighbourRecyclerViewAdapter(List<Neighbour> items, int tabpage) {
        mNeighbours = items;
        if(tabpage==2){
            Setvisibilitybtn=false;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_neighbour, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Neighbour neighbour = mNeighbours.get(position);
        holder.mNeighbourName.setText(neighbour.getName());
        Glide.with(holder.mNeighbourAvatar.getContext())
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mNeighbourAvatar);
        if(!Setvisibilitybtn){
            holder.mDeleteButton.setVisibility(View.GONE);
        }
        holder.mNeighbourName.setOnClickListener(new View.OnClickListener() { ///   Observe le clic sur bouton name
            @Override
            public void onClick(View view) {
                Intent DetailedActivity = new Intent(view.getContext(), DetailedNeighbourActivity.class);       //
                DetailedActivity.putExtra("Neigh", neighbour);
                view.getContext().startActivity(DetailedActivity);//
                EventBus.getDefault();//
                Log.i("DEBUG","l'utilisateur essaye d'ouvrir le détaille");
            }
        });
        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteNeighbourEvent(neighbour));

            }
        });
    }

    @Override
    public int getItemCount() {
        return mNeighbours.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_list_avatar)
        public ImageView mNeighbourAvatar;
        @BindView(R.id.item_list_name)
        public TextView mNeighbourName;
        @BindView(R.id.item_list_delete_button)
        public ImageButton mDeleteButton;

        public ViewHolder(View view) {

            super(view);
            ButterKnife.bind(this, view);

        }

    }
}
