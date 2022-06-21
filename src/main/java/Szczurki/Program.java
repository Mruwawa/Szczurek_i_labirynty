package Szczurki;

import Szczurki.Configuration.ConfigurationProvider;
import Szczurki.SimulationRunner.SimulationRunner;
import Szczurki.Utilities.Keys;

/**
 * Główna klasa programu
 */
public class Program {
    /**
     * @param args Argumenty wejściowe programu
     */
    public static void main(String[] args) {

        //wczytywanie konfiguracji
        var configuration = ConfigurationProvider.getConfiguration(Keys.CONFIG_FILENAME);

        //inicjalizacja symulacji
        var simulationRunner = new SimulationRunner(configuration);

        //przeprowadzanie wszystkich symulacji z ustawień
        simulationRunner.runSimulations();
    }
}
