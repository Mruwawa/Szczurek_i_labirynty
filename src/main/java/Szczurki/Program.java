package Szczurki;

import Szczurki.Configuration.Configuration;
import Szczurki.SimulationRunner.SimulationRunner;

public class
Program {
    public static void main(String[] args)
    {

        //wczytywanie konfiguracji z pliku
        var configuration = new Configuration("settings.json");

        //inicjalizacja symulacji
        var simulationRunner = new SimulationRunner(configuration);

        simulationRunner.runSimulations();

    }
}
