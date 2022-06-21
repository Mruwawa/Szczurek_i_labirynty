package Szczurki;

import Szczurki.Configuration.ConfigurationProvider;
import Szczurki.SimulationRunner.SimulationRunner;
import Szczurki.Utilities.Keys;

public class Program {
    public static void main(String[] args) {

        //wczytywanie konfiguracji
        var configuration = ConfigurationProvider.getConfiguration(Keys.CONFIG_FILENAME);

        //inicjalizacja symulacji
        var simulationRunner = new SimulationRunner(configuration);

        simulationRunner.runSimulations();


    }
}
