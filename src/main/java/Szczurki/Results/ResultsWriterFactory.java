package Szczurki.Results;

import Szczurki.Configuration.ResultsWriterType;
import Szczurki.Configuration.SimulationSettings;
import Szczurki.Simulation.Entities.Interfaces.IUpdatable;

import java.util.List;

public class ResultsWriterFactory {
    public static IResultsWriter getResultsWriter(ResultsWriterType type) {
        if (type != null)
            switch (type) {
                case CSV:
                    return new CsvResultsWriter();
            }
        return new IResultsWriter() {
            @Override
            public void writeSettingsToFile(SimulationSettings settings) {

            }

            @Override
            public void addResults(List<IUpdatable> updatableEntities) {

            }
        };
    }
}
