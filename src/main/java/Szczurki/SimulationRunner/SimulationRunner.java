package Szczurki.SimulationRunner;

import Szczurki.Configuration.ConfigurationData;
import Szczurki.Results.ResultsWriterFactory;
import Szczurki.Simulation.Setup.SimulationFactory;

public class SimulationRunner {

    private final ConfigurationData _appConfig;

    public SimulationRunner(ConfigurationData configuration) {
        _appConfig = configuration;
    }

    public void runSimulations() {
        var results = ResultsWriterFactory.getResultsWriter(_appConfig.getResultsWriterType());

        _appConfig.getSimulationSettingsList().forEach(simulationSettings -> { //każda symulacja
                    results.writeSettingsToFile(simulationSettings);
                    for (int i = 0; i < _appConfig.getRepeatCount(); i++) { //powtarzana jest określoną w configu ilość razy
                        var simulation = SimulationFactory.getSimulation(_appConfig, simulationSettings);
                        simulation.run();
                        results.addResults(simulation.getResults());
                    }
                }
        );
    }
}
