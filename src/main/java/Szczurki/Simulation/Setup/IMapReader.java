package Szczurki.Simulation.Setup;

import Szczurki.Simulation.Entities.Interfaces.IEntity;

/**
 * interfejs przechowujący mapę
 */
public interface IMapReader {
    IEntity[][] getMap(String fileName);
}
