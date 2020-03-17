package com.openclassrooms.entrevoisins.service;


import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_list.DetailedNeighbourActivity;
import com.openclassrooms.entrevoisins.ui.neighbour_list.FavoritesFragment;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
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

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours(); // neighbours = list neighbour of the fake DB return by getNeighbours
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS; //expectedNeighbours = list neigbour (Fake) DB
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));// //contain in any order neighbours /e
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0); // neigbourToDelete is equal to first element of the list
        service.deleteNeighbour(neighbourToDelete); // delete neighbour
        assertFalse(service.getNeighbours().contains(neighbourToDelete)); // check if the list not containt neighbourToDelete
    }

    @Test
    public void addFavoriteWithSucces(){//ok un autre a faux
        Neighbour neighbourToFavorite = service.getNeighbours().get(1);
        service.setFavorite((int)neighbourToFavorite.getId(),true);
        assertTrue(service.getNeighbours().contains(neighbourToFavorite));

    }

    @Test
    public void removeFavoriteWithSucces(){//ok un autre a faux
        Neighbour neighbourToFavorite = service.getNeighbours().get(1);
        service.setFavorite((int)neighbourToFavorite.getId(),false);
        assertTrue(service.getNeighbours().contains(neighbourToFavorite));

    }

    @Test
    public void createNeighbourWithSucces(){ //ok
        Neighbour neighbourAdd= new Neighbour(13,"MathieuADD","https://i.pravatar.cc/150?u=a042581f4e29026704d","Dans le test","0750886058","Dans le test",false);
        service.createNeighbour(neighbourAdd);
        assertTrue(service.getNeighbours().contains(neighbourAdd));
    }

   /* @Test
    public void getNeighbourWithSuccess (){ // ?
        Neighbour neighbourexcpected = DummyNeighbourGenerator.DUMMY_NEIGHBOURS.get(1);
        Neighbour neighbour = service.getNeighbours().get(1);
        assertEquals(neighbourexcpected,neighbour);
    }*/


    @Test
    public void getFavoriteWithSucces(){
        List<Neighbour> neighboursFav = service.getNeighboursFavorites();
        List<Neighbour> neighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        List<Neighbour> neighboursFavExpected = new ArrayList<>();

        for (Neighbour neighbour : neighbours) {
            if (neighbour.getFavorite())
                neighboursFavExpected.add(neighbour);
        }
        assertEquals(neighboursFavExpected,neighboursFav);
    }
}
