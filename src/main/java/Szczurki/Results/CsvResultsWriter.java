package Szczurki.Results;

import Szczurki.Configuration.SimulationSettings;
import Szczurki.Simulation.CauseOfFinish;
import Szczurki.Simulation.Entities.Animals.Animal;
import Szczurki.Simulation.Entities.Interfaces.IUpdatable;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvResultsWriter implements IResultsWriter {
    private final String resultsFileName = "results.csv";
    private final char separator = ';';

    public CsvResultsWriter() {
        clearFile();
    }

    public void writeSettingsToFile(SimulationSettings settings) {
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
        write(builder.toString());

    }

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
        write(builder.toString());
    }

    private void write(String content) {
        try {
            var resultsFile = new FileWriter(resultsFileName, true);
            var writer = new BufferedWriter(resultsFile);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            System.out.println("Nie udalo się zapisac danych do pliku" + resultsFileName);
        }
    }

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
