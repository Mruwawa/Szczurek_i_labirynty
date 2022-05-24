package Szczurki.SimulationRunner;

import Szczurki.Configuration.IConfiguration;
import Szczurki.Simulation.Simulation;

import java.io.FileNotFoundException;

public class SimulationRunner {

    private final IConfiguration _config;

    public SimulationRunner(IConfiguration configuration)
    {
        _config = configuration;
    }

    public void runSimulations()
    {
        for (var settings:_config.getSimulationSettingsList()) { //każda symulacja
            for (int i = 0; i < _config.getRepeatCount(); i++) { //powtarzana jest określoną w configu ilość razy
                var simulation = new Simulation(settings);
                simulation.run();

            }
        }
    }
}
