package Szczurki.Results;

import Szczurki.Configuration.SimulationSettings;
import Szczurki.Simulation.Entities.Interfaces.IUpdatable;

import java.util.List;

/**
 * Interfejs pozwalający wypisać wyniki symulacji
 */
public interface IResultsWriter {
    /**
     * @param settings Ustawienia poszczególnej symulacji
     */
    void writeSettings(SimulationSettings settings);

    /**
     * @param updatableEntities Lista agentów symulacji
     */
    void addResults(List<IUpdatable> updatableEntities);
}
