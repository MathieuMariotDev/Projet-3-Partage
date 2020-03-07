package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_list.FavoritesFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     *
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    public List<Neighbour> getNeighboursFavorites() {
        List<Neighbour> neighboursfavorites = new ArrayList<>();

        for (Neighbour neighbour : neighbours) {
            if (neighbour.getFavorite())
                neighboursfavorites.add(neighbour);
        }
        return neighboursfavorites;
    }

    public void setFavorite(int id, boolean favorite) {
        for (int i = 0; i < neighbours.size(); i++) {
            Neighbour neighbour = neighbours.get(i);
            if ((int)neighbour.getId()==id) {
                neighbour.setFavorite(favorite);
            }

        }
    }
}
/*for (Neighbour neighbour : neighbours) {
         if (neighbour.getId()==fav)
             neighbour.setFavorite(boll);
     }
        }*/