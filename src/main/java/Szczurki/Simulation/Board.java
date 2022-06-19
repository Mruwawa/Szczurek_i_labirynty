package Szczurki.Simulation;

import Szczurki.Simulation.Entities.Animals.Animal;
import Szczurki.Simulation.Entities.Interfaces.IEntity;
import Szczurki.Simulation.Entities.Interfaces.IUpdatable;
import Szczurki.Utilities.Vector;

import java.util.List;

public class Board {

    private final IEntity[][] _map;
    private final List<IUpdatable> _updatableEntities;

    public Board(List<IUpdatable> updatableEntities, IEntity[][] map) {
        _updatableEntities = updatableEntities;
        _map = map;
    }

    public boolean isOutside(Vector vector) {
        return vector.x < 0 ||
                vector.x >= _map.length ||
                vector.y < 0 ||
                vector.y >= _map[0].length;
    }

    public void move(Vector start, Vector moveBy) {
        teleport(start, Vector.add(start, moveBy));
    }

    public void teleport(Vector start, Vector end) {
        if (start.equals(end)) return;

        _map[end.x][end.y] = _map[start.x][start.y];
        _map[start.x][start.y] = null;
        start.set(end);
    }


    public void remove(IUpdatable entity, Vector position) {
        if(entity instanceof Animal) ((Animal) entity).setActive(false);
        _map[position.x][position.y] = null;
    }

    public IEntity getEntityAt(Vector vector)
    {
        return _map[vector.x][vector.y];
    }

    public IEntity[][] getMap()
    {
        return _map;
    }
    public List<IUpdatable> getUpdatableEntities()
    {
        return _updatableEntities;
    }

    public boolean areThereAnyActiveAnimalsLeft(){
        return _updatableEntities.stream().anyMatch(x -> x instanceof Animal && ((Animal)x).isActive());
    }
}
