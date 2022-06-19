package Szczurki.SimulationRunner;

import Szczurki.Configuration.ConfigurationData;
import Szczurki.Simulation.Setup.SimulationFactory;
import Szczurki.Simulation.Simulation;

public class SimulationRunner {

    private final ConfigurationData _appConfig;

    public SimulationRunner(ConfigurationData configuration) {
        _appConfig = configuration;
    }

    public void runSimulations() {
        _appConfig.getSimulationSettingsList().forEach(simulationSettings -> { //każda symulacja
                    for (int i = 0; i < _appConfig.getRepeatCount(); i++) { //powtarzana jest określoną w configu ilość razy
                        var simulation = SimulationFactory.getSimulation(_appConfig, simulationSettings);
                        simulation.run();
                    }
                }
        );
    }
}
