package Szczurki.Simulation.Setup;

import Szczurki.Configuration.ResourceProvider;
import Szczurki.Simulation.Entities.Interfaces.IEntity;
import Szczurki.Simulation.Entities.Obstacle;
import Szczurki.Simulation.Entities.Wall;
import Szczurki.Utilities.Keys;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MapReader implements IMapReader {

    ArrayList<String> labirynth = new ArrayList<>();

    public IEntity[][] getMap(String fileName) {


        try {

            var inputStream = ResourceProvider.getResource(Keys.MAP_DIRECTORY + "/" + fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;

            while((line = reader.readLine())!=null){
                labirynth.add(line);
            }


        } catch (IOException e) {
            if (!fileName.equals(Keys.MAP_DIRECTORY + "/" + Keys.DEFAULT_MAP_FILENAME)) {
                System.out.println("Wczytywanie mapy nie powiodlo sie. Wczytuje domyslna mape");
                return getMap(Keys.MAP_DIRECTORY + "/" + Keys.DEFAULT_MAP_FILENAME);
            }
            System.out.println("Wczytywanie domyslnej mapy tez sie nie powiodlo!");
            return null;
        }

        IEntity[][] map = new IEntity[labirynth.get(0).length()][labirynth.size()];

        for (int i = 0; i < labirynth.get(0).length(); ++i) {
            for (int j = 0; j < labirynth.size(); ++j) {
                if (labirynth.get(j).charAt(i) == Keys.WALL_SYMBOL) {
                    map[i][j] = new Wall();
                }

                if (labirynth.get(j).charAt(i) == Keys.OBSTACLE_SYMBOL) {
                    map[i][j] = new Obstacle(10, 10);
                }

            }
        }

        return map;
    }

}
