package Szczurki.Simulation.Setup;

import Szczurki.Configuration.SimulationSettings;
import Szczurki.Simulation.Entities.Interfaces.IEntity;
import Szczurki.Simulation.Entities.Obstacle;
import Szczurki.Simulation.Entities.Wall;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MapReader implements IMapReader {

    ArrayList<String> labirynth = new ArrayList<>();

    public IEntity[][] getMap(SimulationSettings settings) {

        String fileName = settings.fileName;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            String line;

            while((line = reader.readLine())!=null){
                labirynth.add(line);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


        IEntity[][] map = new IEntity[labirynth.size()][labirynth.get(0).length()];

        for (int i = 0; i < labirynth.size(); ++i) {
            for (int j = 0; j < labirynth.get(0).length(); ++j) {
                if (labirynth.get(i).charAt(j) == '#') {
                    map[j][i] = new Wall();
                }

                if (labirynth.get(i).charAt(j) == '@') {
                    map[j][i] = new Obstacle(10, 10);
                }

            }
        }
        int mapHeight=labirynth.size();
        int mapWidth = labirynth.get(0).length();

        return map;
    }

}
