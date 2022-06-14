package Szczurki;
import Szczurki.Configuration.ConfigurationData;
import Szczurki.Configuration.ConfigurationProvider;
import Szczurki.SimulationRunner.SimulationRunner;

import java.nio.file.Path;
import java.util.Objects;

public class Program {
    public static void main(String[] args) {

        //wczytywanie konfiguracji
        var configuration = ConfigurationProvider.getConfiguration("settings.json");

        //inicjalizacja symulacji
        var simulationRunner = new SimulationRunner(configuration);

        simulationRunner.runSimulations();


    }
}
