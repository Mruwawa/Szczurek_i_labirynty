package Szczurki.Simulation.Setup;

import Szczurki.Simulation.Entities.Interfaces.IEntity;
import Szczurki.Simulation.Entities.Interfaces.IUpdatable;

import java.util.List;

public interface IEntityPositioner {
    List<IUpdatable> getPlacedEntities(IEntity[][] map);
}
