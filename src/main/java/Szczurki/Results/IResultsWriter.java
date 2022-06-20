package Szczurki.Results;

import Szczurki.Configuration.SimulationSettings;
import Szczurki.Simulation.Entities.Interfaces.IUpdatable;

import java.util.List;

public interface IResultsWriter {
    void writeSettingsToFile(SimulationSettings settings);

    void addResults(List<IUpdatable> updatableEntities);
}
