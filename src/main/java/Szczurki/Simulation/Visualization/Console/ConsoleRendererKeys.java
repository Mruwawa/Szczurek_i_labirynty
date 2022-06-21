package Szczurki.Simulation.Visualization.Console;

import Szczurki.Simulation.Entities.Animals.*;
import Szczurki.Simulation.Entities.Guardian;
import Szczurki.Simulation.Entities.Obstacle;
import Szczurki.Simulation.Entities.Wall;

import java.util.Map;

import static java.util.Map.entry;

/**
 * Klasa Zawierająca reprezentacje klas jako tekst do wyświetlenia w konsoli
 * oraz kody ANSI kolorów w konsoli
 */
class ConsoleRendererKeys {
    public static final String RESET = "\u001B[0m";

    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    public static final String BLACK_BACKGROUND = "\u001B[40m";
    public static final String RED_BACKGROUND = "\u001B[41m";
    public static final String GREEN_BACKGROUND = "\u001B[42m";
    public static final String YELLOW_BACKGROUND = "\u001B[43m";
    public static final String BLUE_BACKGROUND = "\u001B[44m";
    public static final String PURPLE_BACKGROUND = "\u001B[45m";
    public static final String CYAN_BACKGROUND = "\u001B[46m";
    public static final String WHITE_BACKGROUND = "\u001B[47m";


    public static final Map<Class<?>, String> COLOR_MAPPINGS = Map.ofEntries(
            entry(Guardian.class, ConsoleRendererKeys.RED_BACKGROUND + "GRL"),
            entry(Rat.class, ConsoleRendererKeys.YELLOW_BACKGROUND + " S "),
            entry(Mousedeer.class, ConsoleRendererKeys.BLUE_BACKGROUND + "MSJ"),
            entry(Gerbil.class, ConsoleRendererKeys.PURPLE_BACKGROUND + " G "),
            entry(Hamster.class, ConsoleRendererKeys.CYAN_BACKGROUND + " H "),
            entry(Mouse.class, ConsoleRendererKeys.GREEN_BACKGROUND + " M "),
            entry(Wall.class, ConsoleRendererKeys.BLACK_BACKGROUND + ConsoleRendererKeys.BLACK + "   "),
            entry(Obstacle.class, ConsoleRendererKeys.BLACK_BACKGROUND + ConsoleRendererKeys.RED + "TRP")
    );

}
