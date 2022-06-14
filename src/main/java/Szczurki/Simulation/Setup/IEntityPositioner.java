package Szczurki.Simulation.Setup;

import Szczurki.Simulation.Entities.Interfaces.IEntity;
import Szczurki.Simulation.Entities.Interfaces.IUpdatable;

import java.util.List;

public interface IEntityPositioner {
    void placeEntities(IEntity[][] map, List<IUpdatable> updatableEntities);
}
