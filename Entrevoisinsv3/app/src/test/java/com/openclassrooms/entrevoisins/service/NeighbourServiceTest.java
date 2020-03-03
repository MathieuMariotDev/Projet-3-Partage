package com.openclassrooms.entrevoisins.service;


import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_list.DetailedNeighbourActivity;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

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
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void addFavoriteWithSucces(){
        Neighbour neighbourToFavorite = service.getNeighbours().get(1);
        neighbourToFavorite.setFavorite(false);
        assertTrue(service.getNeighbours().contains(neighbourToFavorite));

    }

    @Test
    public void createNeighbourWithSucces(){
        Neighbour neighbourAdd= new Neighbour(13,"MathieuADD","https://i.pravatar.cc/150?u=a042581f4e29026704d","Dans le test","0750886058","Dans le test",false);
        service.createNeighbour(neighbourAdd);
        assertTrue(service.getNeighbours().contains(neighbourAdd));
    }

    @Test
    public void getNeighbourWithSuccess (){
        Neighbour neighbour = service.getNeighbours().get(1);
        assertTrue(service.getNeighbours().contains(neighbour));
    }

    @Test
    public void actualyFavorite(){
        Neighbour neighbour = service.getNeighbours().get(0);
        assertTrue(neighbour.getFavorite()==true);

    }
}
