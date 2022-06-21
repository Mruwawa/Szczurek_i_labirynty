package Szczurki.Results;

import Szczurki.Configuration.SimulationSettings;
import Szczurki.Simulation.CauseOfFinish;
import Szczurki.Simulation.Entities.Animals.Animal;
import Szczurki.Simulation.Entities.Interfaces.IUpdatable;
import Szczurki.Utilities.Keys;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Klasa odpowiedzialna za zapisywanie wyników symulacji do pliku
 */
public class CsvResultsWriter implements IResultsWriter {

    private final String resultsFileName = Keys.RESULTS_FILENAME;
    private final char separator = ';';

    /**
     * W konstruktorze czyścimy zawartość pliku z wynikami
     */
    public CsvResultsWriter() {
        clearFile();
    }

    /**
     * Metoda dopisuje do pliku nagłówek zawierający wypis ustawień symulacji
     * @param settings ustawienia symulacji
     */
    public void writeSettings(SimulationSettings settings) {
        var builder = new StringBuilder();
        builder.append("Symulacja\n");
        builder.append("Labirynt:");
        builder.append(separator);
        builder.append(settings.getLabyrinthFileName());
        builder.append(separator);
        builder.append("Ilosc iteracji:");
        builder.append(separator);
        builder.append(settings.getTurnCount());
        builder.append("\n");
        settings.getAnimalCounts().forEach((animal, count) -> {
            for (int i = 0; i < count; i++) {
                builder.append(animal);
                builder.append(separator);
            }
        });
        builder.append("\n");
        append(builder.toString());

    }

    /**
     * Metoda przyjmuje listę agentów symulacji, następnie zbiera z niej dane
     * na temat czasu uraz powodu wyjścia z labiryntu przez każde zwierze
     * i dopisuje linijke z tymi danymi do pliku
     * Jeżeli zwierze wyszło z labiryntu liczba jest dodatnia
     * Jeżeli nie wyszło jest to 0
     * Jeżeli zostało dopadnięte przez strażnika liczba jest ujemna
     * @param updatableEntities Lista agentów symulacji
     */
    public void addResults(List<IUpdatable> updatableEntities) {
        var builder = new StringBuilder();
        updatableEntities
                .stream()
                .filter(x -> x instanceof Animal)
                .forEach(entity ->
                {
                    var animal = (Animal) entity;
                    if (animal.getCauseOfFinish() == CauseOfFinish.GUARDIAN) builder.append("-");
                    builder.append(animal.getTimeOfFinish());
                    builder.append(separator);
                });
        builder.append("\n");
        append(builder.toString());
    }

    /**
     * Metoda dopisująca zawartość do pliku z wynikami
     * @param content zawartość, którą chcemy dopisać do pliku
     */
    private void append(String content) {
        try {
            var resultsFile = new FileWriter(resultsFileName, true);
            var writer = new BufferedWriter(resultsFile);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            System.out.println("Nie udalo się zapisac danych do pliku" + resultsFileName);
        }
    }

    /**
     * Metoda czyszcząca zawartość pliku z wynikami
     */
    private void clearFile()
    {
        try {
            var resultsFile = new FileWriter(resultsFileName);
            var writer = new BufferedWriter(resultsFile);
            writer.write("");
            writer.close();
        } catch (IOException e) {
            System.out.println("Nie udało się wyczyscic pliku z wynikami");
        }

    }
}
