package com.openclassrooms.entrevoisins.ui.neighbour_list;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * MyFavoriteFragment est la classe qui va gérer notre page de détail pour chaque voisin
 * @author ozanne
 */
public class MyFavoriteFragment extends Fragment {

    private NeighbourApiService mApiService;
    private List<Neighbour> myFavoriteList;
    private RecyclerView mRecyclerView;



    /**
     * Create and return a new instance
     * @return @{@link MyFavoriteFragment}
     */
    public static MyFavoriteFragment newInstance() {
        MyFavoriteFragment fragment = new MyFavoriteFragment();
        return fragment;
    }

    /**
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();
    }

    /**
     * Initialise la vue de la page des voisins favories
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_neighbour_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        return view;
    }

    /**
     * Init the List of favorite neighbours
     */
    private void initList() {
        myFavoriteList = mApiService.getMyFavoriteNeighbours();
        mRecyclerView.setAdapter(new MyFavoriteNeighbourRecyclerViewAdapter(myFavoriteList));


    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

}
