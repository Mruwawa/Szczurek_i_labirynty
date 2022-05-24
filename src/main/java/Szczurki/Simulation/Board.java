package Szczurki.Simulation;

import Szczurki.Configuration.IMapReader;
import Szczurki.Configuration.MapReader;
import Szczurki.Configuration.SimulationSettings;
import Szczurki.Simulation.Entities.Animals.Rat;
import Szczurki.Simulation.Entities.Interfaces.IEntity;
import Szczurki.Simulation.Entities.Interfaces.IUpdatable;
import Szczurki.Utilities.Vector;

import java.util.ArrayList;

public class Board {

    public IEntity[][] map;
    public final ArrayList<IUpdatable> updatableEntities;
    private final ArrayList<IUpdatable> removedEntities;
    private final IMapReader _mapReader;
    private final SimulationSettings _settings;


    public Board(SimulationSettings settings) {
        updatableEntities = new ArrayList<>();
        removedEntities = new ArrayList<>();
        _mapReader = new MapReader();
        _settings = settings;
    }


    public void initializeEntities() {

        map = _mapReader.getMap(_settings);

        var klapcio = new Rat(1, 4, "Kłapcio");
        var deedee = new Rat(6, 1, "Dee Dee");

        map[1][4] = klapcio;
        map[6][1] = deedee;

        updatableEntities.add(klapcio);
        updatableEntities.add(deedee);

    }

    public boolean isOutside(Vector vector) {
        return vector.x < 0 ||
                vector.x >= map.length ||
                vector.y < 0 ||
                vector.y >= map[0].length;
    }

    public void move(Vector start, Vector moveBy)
    {
        teleport(start, Vector.add(start, moveBy));
    }

    public void teleport(Vector start, Vector end)
    {
        if(start.equals(end)) return;

        map[end.x][end.y] = map[start.x][start.y];
        map[start.x][start.y] = null;
        start.set(end);
    }



    public void remove(IUpdatable entity, Vector position)
    {
        removedEntities.add(entity);
        map[position.x][position.y] = null;
    }

    public void removeInactiveEntities()
    {
        updatableEntities.removeIf(removedEntities::contains);
    }
}
