package Szczurki.Results;

import Szczurki.Configuration.ResultsWriterType;
import Szczurki.Configuration.SimulationSettings;
import Szczurki.Simulation.Entities.Interfaces.IUpdatable;

import java.util.List;

/**
 * Klasa odpowiedzialna za stworzenie odpowiedniej instancji klasy
 * wypisującej wyniki na podstawie ustawień aplikacji
 */
public class ResultsWriterFactory {
    /**
     * Jeżeli nie podano typu, zostanie zwrócona pusta implementacja
     * dzięki czemu możemy wyłaczyć wypisywanie wyników
     * @param type typ klasy wypisującej wyniki
     * @return odpowiednia implementacja
     */
    public static IResultsWriter getResultsWriter(ResultsWriterType type) {
        if (type != null)
            //tutaj mógłby być if, ale zostawiamy switch, aby umożliwić łatwiejszą
            //rozbudowę aplikacji
            switch (type) {
                case CSV:
                    return new CsvResultsWriter();
            }
        return new IResultsWriter() {
            @Override
            public void writeSettings(SimulationSettings settings) {

            }

            @Override
            public void addResults(List<IUpdatable> updatableEntities) {

            }
        };
    }
}
