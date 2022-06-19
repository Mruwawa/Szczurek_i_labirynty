package Szczurki.Configuration;

import java.util.Map;

public class SimulationSettings {
    private String labyrinthFileName;
    private int turnCount;
    private int guardianCount;
    private Map<String, Integer> animalCounts;

    public String getLabyrinthFileName() {
        return labyrinthFileName;
    }

    public int getTurnCount() {
        return turnCount;
    }

    public int getGuardianCount() {
        return guardianCount;
    }

    public Map<String, Integer> getAnimalCounts() {
        return animalCounts;
    }
}
