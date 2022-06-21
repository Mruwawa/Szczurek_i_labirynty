package Szczurki.SimulationRunner;

import Szczurki.Configuration.ConfigurationData;
import Szczurki.Results.ResultsWriterFactory;
import Szczurki.Simulation.Setup.SimulationFactory;

/**
 * Klasa odpowiedzialna za wywoływanie symulacji określoną ilość razy
 * z odpowiednimi ustawieniami
 */
public class SimulationRunner {

    private final ConfigurationData _appConfig;

    public SimulationRunner(ConfigurationData configuration) {
        _appConfig = configuration;
    }

    /**
     * Metoda przeprowadzająca symulacje
     */
    public void runSimulations() {
        //tworzymy instancję klasy wypisującej wyniki
        var results = ResultsWriterFactory.getResultsWriter(_appConfig.getResultsWriterType());

        _appConfig.getSimulationSettingsList().forEach(simulationSettings -> { //każda symulacja
                    results.writeSettings(simulationSettings);
                    for (int i = 0; i < _appConfig.getRepeatCount(); i++) { //powtarzana jest określoną w configu ilość razy
                        //pobieramy z fabryki odpowiednią instancję symulacji
                        var simulation = SimulationFactory.getSimulation(_appConfig, simulationSettings);
                        simulation.run();
                        //po przeprowadzeniu jej zrzucamy rezultaty do klasy odpowiedzialnej za ich zapisywanie
                        results.addResults(simulation.getResults());
                    }
                }
        );
    }
}
