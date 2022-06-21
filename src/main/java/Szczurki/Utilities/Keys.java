package Szczurki.Utilities;

import Szczurki.Simulation.Entities.Animals.*;
import Szczurki.Simulation.Entities.Guardian;
import Szczurki.Simulation.Entities.Interfaces.IEntity;
import Szczurki.Simulation.Entities.Obstacle;
import Szczurki.Simulation.Entities.Wall;

import java.util.List;

/**
 * Klasa zawierająca różne wartości używane w programie,
 * ale nie zmieniane tak często by wczytywać je z pliku
 */
public class Keys {
    //Nazwy plików
    public static final String RESULTS_FILENAME = "results.csv";
    public static final String CONFIG_FILENAME = "settings.json";
    public static final String DEFAULT_CONFIG_FILENAME = "defaultConfiguration.json";
    public static final String DEFAULT_MAP_FILENAME = "labirynt domyslny.txt";
    public static final String TEXTURE_FILES_EXTENSION = ".png";

    //Nazwy folderów
    public static final String MAP_DIRECTORY = "labyrinths";
    public static final String TEXTURE_DIRECTORY = "textures";


    //Reprezentacje elementów mapy w plikach
    public static final char WALL_SYMBOL = '#';
    public static final char OBSTACLE_SYMBOL = '@';

    /**
     * Lista klas wszystkich elementów, które mogą znaleźć się na planszy
     */
    public static final List<Class<? extends IEntity>> ENTITY_CLASSES = List.of(
            Rat.class,
            Mousedeer.class,
            Gerbil.class,
            Hamster.class,
            Mouse.class,
            Wall.class,
            Obstacle.class,
            Guardian.class);
}
