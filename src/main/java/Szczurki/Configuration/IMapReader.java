package Szczurki.Configuration;

import Szczurki.Simulation.Entities.Interfaces.IEntity;

public interface IMapReader {
    IEntity[][] getMap(SimulationSettings settings);
}
