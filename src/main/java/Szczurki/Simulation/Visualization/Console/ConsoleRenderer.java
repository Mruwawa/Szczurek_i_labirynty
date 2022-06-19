package Szczurki.Simulation.Visualization.Console;

import Szczurki.Simulation.Entities.Animals.*;
import Szczurki.Simulation.Entities.Guardian;
import Szczurki.Simulation.Entities.Interfaces.IEntity;
import Szczurki.Simulation.Entities.Obstacle;
import Szczurki.Simulation.Entities.Wall;
import Szczurki.Simulation.Visualization.IRenderer;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static java.util.Map.entry;

public class ConsoleRenderer implements IRenderer {

    private final int _frameTime;

    public ConsoleRenderer(int frameTime)
    {
        _frameTime = frameTime;
    }


    private final Map<Class<?>, String> colorMappings = Map.ofEntries(
            entry(Guardian.class, ConsoleColors.RED_BACKGROUND + "GRL"),
            entry(Rat.class, ConsoleColors.YELLOW_BACKGROUND + " S "),
            entry(Mousedeer.class, ConsoleColors.BLUE_BACKGROUND + "MSJ"),
            entry(Gerbil.class, ConsoleColors.PURPLE_BACKGROUND + " G "),
            entry(Hamster.class, ConsoleColors.CYAN_BACKGROUND + " H "),
            entry(Mouse.class, ConsoleColors.GREEN_BACKGROUND + " M "),
            entry(Wall.class, ConsoleColors.BLACK_BACKGROUND + ConsoleColors.BLACK + "   "),
            entry(Obstacle.class, ConsoleColors.BLACK_BACKGROUND + ConsoleColors.RED + "TRP")
    );

    public void render(IEntity[][] entities) {
        var builder = new StringBuilder();


        builder.append("\033[H\033[2J");
        for (int y = 0; y < entities[0].length; y++) {
            for (int x = 0; x < entities.length; x++) {
                if (entities[x][y] != null && colorMappings.containsKey(entities[x][y].getClass())) {
                    builder.append(colorMappings.get(entities[x][y].getClass()));
                    builder.append(ConsoleColors.RESET);
                    continue;
                }
                builder.append(ConsoleColors.WHITE_BACKGROUND);
                builder.append("   ");
                builder.append(ConsoleColors.RESET);
            }
            builder.append("\n");
        }

        System.out.println(builder);

        try {
            TimeUnit.MILLISECONDS.sleep(_frameTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {

    }
}
