package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
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

public class MyFavoriteNeighbourRecyclerViewAdapter extends RecyclerView.Adapter<MyFavoriteNeighbourRecyclerViewAdapter.MyFavoriteViewHolder> {

    private final List<Neighbour> myFavoriteNeighbours;

    public MyFavoriteNeighbourRecyclerViewAdapter(List<Neighbour> items) {
        myFavoriteNeighbours = items;
    }

    /**
     * Create and return a new view
     * @return @{@link MyFavoriteViewHolder}
     */
    @Override
    public MyFavoriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_favorite_neighbour_item, parent, false);
        return new MyFavoriteViewHolder(view);
    }

    /**
     * Permet de lier les éléments de la ViewHolder à leurs valeurs dans la base de données.
     * @param holder
     * @param position   Permet de récupérer l'objet correspondant dans notre liste d'objet
     */
    @Override
    public void onBindViewHolder(final MyFavoriteViewHolder holder, int position) {
        Neighbour neighbour = myFavoriteNeighbours.get(position);
        holder.mNeighbourName.setText(neighbour.getName());
        Glide.with(holder.mNeighbourAvatar.getContext())
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mNeighbourAvatar);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creer une activity DetailsNeighbourActivity
                Intent intent = new Intent(v.getContext(), DetailNeighbour.class);

                // Remplir les champs de la vue detail avec les données du voisin.
                intent.putExtra("Id", String.valueOf(neighbour.getId()));
                intent.putExtra("Name", neighbour.getName());
                intent.putExtra("Address", neighbour.getAddress());
                intent.putExtra("AvatarUrl", neighbour.getAvatarUrl());
                intent.putExtra("AboutMe", neighbour.getAboutMe());
                intent.putExtra("PhoneNumber", neighbour.getPhoneNumber());

                // lancer la vue detail
                ContextCompat.startActivity(v.getContext(),intent,null);


            }
        });
    }

    /**
     * Renvoie le nombre total d'éléments dans le tableau de mes favoris
     */
    @Override
    public int getItemCount() {
        return myFavoriteNeighbours.size();
    }

    public class MyFavoriteViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_list_avatar)
        public ImageView mNeighbourAvatar;
        @BindView(R.id.item_list_name)
        public TextView mNeighbourName;

        public MyFavoriteViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
