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


        IEntity[][] map = new IEntity[labirynth.get(0).length()][labirynth.size()];

        for (int i = 0; i < labirynth.get(0).length(); ++i) {
            for (int j = 0; j < labirynth.size(); ++j) {
                if (labirynth.get(j).charAt(i) == '#') {
                    map[i][j] = new Wall();
                }

                if (labirynth.get(j).charAt(i) == '@') {
                    map[i][j] = new Obstacle(10, 10);
                }

            }
        }

        return map;
    }

}
