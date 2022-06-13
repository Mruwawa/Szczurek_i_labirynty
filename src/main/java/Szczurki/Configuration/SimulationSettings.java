package Szczurki.Configuration;

import java.util.ArrayList;
import java.util.Map;

import static java.util.Map.entry;

public class SimulationSettings {
    //docelowo te wartości będą wczywywane z plików

    public String fileName = "labirynt 40x40.txt";
    public int mapHeight = 40;
    public int mapWidth = 40;
    public int turnCount = 1000;
    public Map<String, Integer> animalCounts = Map.ofEntries(
            entry("gerbils", 2),
            entry("hamsters", 2),
            entry("mice", 2),
            entry("mousedeer", 2),
            entry("rats", 2)
    );
    public ArrayList<String> animalNames;

}
