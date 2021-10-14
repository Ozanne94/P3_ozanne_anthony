package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 * @author ozanne
 */
public class DummyNeighbourApiService implements NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();


    /**
     * Creation of favorites list
     */
    private List<Neighbour> myFavoriteNeighbour = new ArrayList<Neighbour>();

    /**
     * {@inheritDoc}
     * Get all my list Neighbours
     * @return neighbours
     *              Retourne la liste des voisins neighbours
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     * Deletes a neighbour
     * @param neighbour
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     * Ajoute un voisin à la liste
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }


    /**
     * Get all my favorites Neighbours
     * @return myFavoriteNeighbour
     *              Retourne la liste myFavoriteNeighbour
     */
    @Override
    public List<Neighbour> getMyFavoriteNeighbours() {
        return myFavoriteNeighbour;
    }


    /**
     * Add a favorite neighbour
     * @param neighbour
     */
    @Override
    public void addFavoriteNeighbour(Neighbour neighbour) {
        myFavoriteNeighbour.add(neighbour);
    }

    /**
     * Deletes a favorite neighbour
     * @param neighbour
     */
    public void deleteFavoriteNeighbour(Neighbour neighbour) {
        myFavoriteNeighbour.remove(neighbour);
    }
}
