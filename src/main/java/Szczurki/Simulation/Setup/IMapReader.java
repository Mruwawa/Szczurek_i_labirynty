package Szczurki.Simulation.Setup;

import Szczurki.Simulation.Entities.Interfaces.IEntity;

public interface IMapReader {
    IEntity[][] getMap(String fileName);
}
