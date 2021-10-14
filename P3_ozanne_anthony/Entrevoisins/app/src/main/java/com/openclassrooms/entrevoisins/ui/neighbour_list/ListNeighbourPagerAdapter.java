package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class ListNeighbourPagerAdapter extends FragmentPagerAdapter {

    public ListNeighbourPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * getItem est appelé pour instancier le fragment pour la page donnée.
     * @param position
     * @return MyFavoriteFragment   Retourne une nouvelle instance de MyFavoriteFragment
     */
    @Override
    public Fragment getItem(int position) {
       if(position== 0 ) return NeighbourFragment.newInstance();
       else return MyFavoriteFragment.newInstance();
    }

    /**
     * get the number of pages
     * @return   Retourne le nombre de pages
     */
    @Override
    public int getCount() {
        return 2;
    }
}