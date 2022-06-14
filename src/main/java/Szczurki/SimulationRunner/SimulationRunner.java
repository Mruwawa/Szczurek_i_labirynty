package Szczurki.SimulationRunner;

import Szczurki.Configuration.ConfigurationData;
import Szczurki.Simulation.Simulation;

public class SimulationRunner {

    private final ConfigurationData _config;

    public SimulationRunner(ConfigurationData configuration)
    {
        _config = configuration;
    }

    public void runSimulations()
    {
        for (var settings:_config.getSimulationSettingsList()) { //każda symulacja
            settings.animalNames = _config.getAnimalNames();
            for (int i = 0; i < _config.getRepeatCount(); i++) { //powtarzana jest określoną w configu ilość razy
                var simulation = new Simulation(settings);
                simulation.run();

            }
        }
    }
}
