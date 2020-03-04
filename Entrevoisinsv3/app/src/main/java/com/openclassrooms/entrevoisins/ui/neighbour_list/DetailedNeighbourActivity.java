package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;


public class DetailedNeighbourActivity extends AppCompatActivity {
    private ImageView avatarview;
    private TextView NameLarge;
    private TextView PhoneNumberCV;
    private TextView AddressCV;
    private TextView SocialReseauCV;
    private TextView NameCV;
    private TextView AboutMeCV;
    private ImageButton btnaddToFavorite;
    private boolean addFavorite=true;
    private NeighbourApiService mApiService;  //Déclaration Interface
    private List<Neighbour> mNeighboursfav = new ArrayList<>(); //déclare une list Neighbour



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_neighbour);
        mApiService = DI.getNeighbourApiService();   // importe l'interface
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //display back button
        getSupportActionBar().setDisplayShowTitleEnabled(false);// Not display titre
        Intent intent = getIntent();
        Neighbour neighbour = (Neighbour) intent.getParcelableExtra("Neigh");
        setDetailedView(neighbour); //call method bellow
        if(neighbour.getFavorite()==true){
            btnaddToFavorite.setColorFilter(Color.RED);
        }
        mNeighboursfav=mApiService.getNeighbours(); // donne la valeur de note bd a mNeighbours

        btnaddToFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(neighbour.getFavorite()== true){
                    neighbour.setFavorite(false);
                    mNeighboursfav.set((int)neighbour.getId()-1,neighbour);//-1 for dif id and size
                    btnaddToFavorite.setColorFilter(Color.YELLOW);
                }
                else{
                    neighbour.setFavorite(addFavorite);
                    mNeighboursfav.set((int)neighbour.getId()-1,neighbour);
                    btnaddToFavorite.setColorFilter(Color.RED);
                }
            }
        });

    }

    private void setDetailedView(Neighbour neighbour){
        PhoneNumberCV = findViewById(R.id.PhoneNumber_CV);
        NameCV = findViewById(R.id.Name_CV);
        AboutMeCV=findViewById(R.id.About_me);
        AddressCV = findViewById(R.id.Address_CV);
        SocialReseauCV = findViewById(R.id.SocialReseau_CV);
        String url = neighbour.getAvatarUrl();
        avatarview = findViewById(R.id.AvatarView);
        NameLarge = findViewById(R.id.Name_Large);
        NameLarge.setText(neighbour.getName());
        Glide.with(this).load(url) //load url
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(avatarview);
        AboutMeCV.setText(neighbour.getAboutMe());
        PhoneNumberCV.setText(neighbour.getPhoneNumber());
        NameCV.setText(neighbour.getName());
        AddressCV.setText(neighbour.getAddress());
        SocialReseauCV.setText("www.facebook.fr/"+neighbour.getName());
        btnaddToFavorite= findViewById(R.id.Favori_Add);
    }

}
