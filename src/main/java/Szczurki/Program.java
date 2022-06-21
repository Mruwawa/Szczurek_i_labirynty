package Szczurki;

import Szczurki.Configuration.ConfigurationProvider;
import Szczurki.SimulationRunner.SimulationRunner;

/**
 * Główna klasa programu
 */
public class Program {
    /**
     * @param args Argumenty wejściowe programu
     */
    public static void main(String[] args) {

        //wczytywanie konfiguracji z pliku settings.json
        var configuration = ConfigurationProvider.getConfiguration("settings.json");

        //inicjalizacja symulacji
        var simulationRunner = new SimulationRunner(configuration);

        //przeprowadzanie wszystkich symulacji z ustawień
        simulationRunner.runSimulations();
    }
}
