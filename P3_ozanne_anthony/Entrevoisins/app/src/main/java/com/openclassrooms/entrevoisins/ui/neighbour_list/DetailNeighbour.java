package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 *  DetailNeighbour is the class which creates the detail page for each neighbor
 *
 * @author ozanne
 *
 */
public class DetailNeighbour extends AppCompatActivity {

    @BindView(R.id.avatar)
    ImageView avatar;
    @BindView(R.id.textName)
    TextView textName;
    @BindView(R.id.textName2)
    TextView textName2;
    @BindView(R.id.aboutMe2)
    TextView aboutMe2;
    @BindView(R.id.addFavorite)
    ImageButton btnFavorite;
    @BindView(R.id.adresse)
    TextView address;
    @BindView(R.id.urlAddress)
    TextView urlAddress;
    @BindView(R.id.phoneNumber)
    TextView numberPhone;



    private NeighbourApiService mApiService;
    private String mNeighbourImage;
    private Neighbour favorite;

    /**
     * Permet d'initialiser la vue de détail d'un voisin
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_neighbour);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mApiService = DI.getNeighbourApiService();

        init (getIntent());
        // verifier si l'utilisateur existe deja dans la liste favori
        // utiliser bouton jaune s'il est dans la liste sinon laisser le bouton en blanc
    }

    /**
     *
     * @param menuItem
     * @return menuItem
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {

            DetailNeighbour.this.finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }


    /**
     * Init detail of favorite neighbours
     */
    private void init(Intent intent) {

        long id = Long.parseLong(intent.getStringExtra("Id"));
        String name = intent.getStringExtra("Name");
        String adresse = intent.getStringExtra("Address");
        String urlAvatar = intent.getStringExtra("AvatarUrl");
        String aboutMe = intent.getStringExtra("AboutMe");
        String phoneNumber = intent.getStringExtra("PhoneNumber");
        favorite = new Neighbour(id, name, urlAvatar, adresse,
                phoneNumber, aboutMe);

        mNeighbourImage = urlAvatar;
        Glide.with(this).load(mNeighbourImage).placeholder(R.drawable.ic_account)
                .apply(RequestOptions.centerCropTransform()).into(avatar);

        textName.setText(name);

        textName2.setText(name);

        address.setText(adresse);

        numberPhone.setText(phoneNumber);

        urlAddress.setText("www.facebook.com/"+name);

        aboutMe2.setText(aboutMe);

    }

    /**
     * Démarré si l'utilisateur clique sur le bouton d'ajout favori
     */
    @OnClick(R.id.addFavorite)
    void addFavorite() {
        //AddNeighbourActivity.navigate(this);

        if(mApiService.getMyFavoriteNeighbours().contains(favorite)){
            mApiService.deleteFavoriteNeighbour(favorite);
            btnFavorite.setImageDrawable(this.getDrawable(R.drawable.ic_baseline_star_outline_24));
            Toast.makeText(this,"Supprimé des favoris", Toast.LENGTH_LONG).show();
        }else {
            mApiService.addFavoriteNeighbour(favorite);
            btnFavorite.setImageDrawable(this.getDrawable(R.drawable.ic_baseline_star_rate_24));
            Toast.makeText(this,"Ajouté aux favoris", Toast.LENGTH_LONG).show();

        }


    }


}



