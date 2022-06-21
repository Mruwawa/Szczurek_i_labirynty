package Szczurki.Simulation.Visualization.Window;

import Szczurki.Simulation.Entities.Animals.*;
import Szczurki.Simulation.Entities.Guardian;
import Szczurki.Simulation.Entities.Obstacle;
import Szczurki.Simulation.Entities.Wall;

import java.awt.*;
import java.util.Map;

import static java.util.Map.entry;

/**
 * Klasa zawierająca wartości z których korzysta WindowRenderer
 */
class WindowRendererKeys {
    /**
     * Lista mapująca klasy na kolory rozumiane przez
     * bibliotekę javax.swing
     */
    public static final Map<Class<?>, Color> COLOR_MAPPINGS = Map.ofEntries(
            entry(Guardian.class, Color.red),
            entry(Rat.class, Color.yellow),
            entry(Mousedeer.class, Color.blue),
            entry(Gerbil.class, new Color(153, 0, 204)),
            entry(Hamster.class, Color.cyan),
            entry(Mouse.class, Color.green),
            entry(Wall.class, Color.black),
            entry(Obstacle.class, Color.magenta)
    );
}
