package Szczurki.Simulation;

import Szczurki.Simulation.Entities.Animals.Animal;
import Szczurki.Simulation.Entities.Interfaces.IEntity;
import Szczurki.Simulation.Entities.Interfaces.IUpdatable;
import Szczurki.Utilities.Vector;

import java.util.List;

/**
 * Klasa reprezentująca planszę
 */
public class Board {

    /**
     * Tablica reprezentująca elementy na planszy i ich położenie
     */
    private final IEntity[][] _map;
    /**
     * Lista agentów
     */
    private final List<IUpdatable> _updatableEntities;

    public Board(List<IUpdatable> updatableEntities, IEntity[][] map) {
        _updatableEntities = updatableEntities;
        _map = map;
    }

    /**
     * @param vector Położenie elementu
     * @return Czy podaje położenie znajduje się w granicach planszy
     */
    public boolean isOutside(Vector vector) {
        return vector.x < 0 ||
                vector.x >= _map.length ||
                vector.y < 0 ||
                vector.y >= _map[0].length;
    }

    /**
     * @param start Pozycja startowa
     * @param moveBy Wektor, o który chcemy przesunąć element
     */
    public void move(Vector start, Vector moveBy) {
        teleport(start, Vector.add(start, moveBy));
    }

    /**
     * @param start Pozycja startowa
     * @param end Pozycja docelowa elementu
     */
    public void teleport(Vector start, Vector end) {
        if (start.equals(end)) return;

        _map[end.x][end.y] = _map[start.x][start.y];
        _map[start.x][start.y] = null;
        start.set(end);
    }


    /**
     * @param entity agent
     * @param position  jego pozycja
     */
    public void remove(IUpdatable entity, Vector position) {
        //Jeżeli usuwamy zwierzaka, to ustawiamy jego status aktywności na false
        if(entity instanceof Animal) ((Animal) entity).setActive(false);
        //Czyścimy miejsce elementu na planszy
        _map[position.x][position.y] = null;
    }

    /**
     * @param vector Pozycja
     * @return Element znajdujący się na podanej pozycji
     */
    public IEntity getEntityAt(Vector vector)
    {
        return _map[vector.x][vector.y];
    }

    /**
     * @return Czy na planszy są jeszcze aktywne zwierzęta
     */
    public boolean areThereAnyActiveAnimalsLeft(){
        return _updatableEntities.stream().anyMatch(x -> x instanceof Animal && ((Animal)x).isActive());
    }

    public IEntity[][] getMap()
    {
        return _map;
    }
    public List<IUpdatable> getUpdatableEntities()
    {
        return _updatableEntities;
    }
}
