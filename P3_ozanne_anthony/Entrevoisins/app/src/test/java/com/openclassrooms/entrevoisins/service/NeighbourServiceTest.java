package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;


    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

   /**
     * Unit test for Neighbour
     */
    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void createNeighbourWithSuccess() {
        // créer un voisin
        Neighbour neighbourToCreate = new Neighbour(1, "Caroline", "", "Paris", "123", "Caroline");
        service.createNeighbour(neighbourToCreate);
        // verifier que la liste contient bien un voisin en plus
        assertTrue(service.getNeighbours().contains(neighbourToCreate));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }


    /**
     * Unit test for FavoriteNeighbour
     */

    @Test
    public void getMyFavoriteNeighboursWithSuccess() {

        // Recuperer la liste qui est vide
        List<Neighbour> neighbours = service.getMyFavoriteNeighbours();
        // choisir un element de la liste DummyçNeighbour et l'ajouter à la liste des favoris
        Neighbour neighbour = DummyNeighbourGenerator.DUMMY_NEIGHBOURS.get(0);
        neighbours.add(neighbour);
        // verifier que la liste dispose / contain l'element que j'ai ajouté
        assertEquals(1 , neighbours.size());
    }

    @Test
    public void deleteMyFavoriteNeighbourWithSuccess() {
        // choisir un element à supprimé
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        // supprimer l'element de la liste : tester la fonctionnalité delete neighbour
        service.deleteFavoriteNeighbour(neighbourToDelete);
        // Verifier que la liste ne contient plus l'élement supprimé
        assertFalse(service.getMyFavoriteNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void addMyFavoriteNeighbourWithSuccess() {
        // choisir un element à ajouter
        Neighbour neighbourToAdd = service.getNeighbours().get(0);
        // inserer l'element dans la liste : tester le add neighbour fonctionnalité
        service.addFavoriteNeighbour(neighbourToAdd);
        // Verifier que la liste contien l'element ajouté
        assertTrue(service.getMyFavoriteNeighbours().contains(neighbourToAdd));
    }

}
