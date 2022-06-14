package Szczurki;

import Szczurki.Configuration.ConfigurationProvider;
import Szczurki.SimulationRunner.SimulationRunner;

public class Program {
    public static void main(String[] args) {

        //wczytywanie konfiguracji
        var configuration = ConfigurationProvider.getConfiguration("settings.json");

        //inicjalizacja symulacji
        var simulationRunner = new SimulationRunner(configuration);

        simulationRunner.runSimulations();


    }
}
