package Szczurki.Simulation.Visualization.Console;

import Szczurki.Simulation.Entities.Animals.*;
import Szczurki.Simulation.Entities.Guardian;
import Szczurki.Simulation.Entities.Interfaces.IEntity;
import Szczurki.Simulation.Entities.Obstacle;
import Szczurki.Simulation.Entities.Wall;
import Szczurki.Simulation.Visualization.IRenderer;

import java.util.concurrent.TimeUnit;

public class ConsoleRenderer implements IRenderer {
    public void render(IEntity[][] entities) {
        var builder = new StringBuilder();


        builder.append("\033[H\033[2J");
        builder.append(horizontalLine(entities.length + 2, "   ", ConsoleColors.WHITE_BACKGROUND));

        for (int y = 0; y < entities[0].length; y++) {

            builder.append(ConsoleColors.WHITE_BACKGROUND);
            builder.append("   ");
            builder.append(ConsoleColors.RESET);

            for (int x = 0; x < entities.length; x++) {
                var entity= entities[x][y];
                if (entity instanceof Guardian) {
                    builder.append(ConsoleColors.RED_BACKGROUND);
                    builder.append("GRL");//GRL - góral
                    builder.append(ConsoleColors.RESET);

                    continue;
                }

                if (entity instanceof Obstacle) {
                    builder.append(ConsoleColors.BLACK_BACKGROUND);
                    builder.append(ConsoleColors.RED);
                    builder.append("TRP");//TRP - it's a trap!
                    builder.append(ConsoleColors.RESET);
                    continue;
                }
                if (entity instanceof Rat) {
                    builder.append(ConsoleColors.YELLOW_BACKGROUND);
                    builder.append(" S ");
                    builder.append(ConsoleColors.RESET);

                    continue;
                }
                if (entity instanceof Mousedeer) {
                    builder.append(ConsoleColors.BLUE_BACKGROUND);
                    builder.append("MSJ");//msj - myszojelen
                    builder.append(ConsoleColors.RESET);

                    continue;
                }
                if (entity instanceof Gerbil) {
                    builder.append(ConsoleColors.PURPLE_BACKGROUND);
                    builder.append(" G ");
                    builder.append(ConsoleColors.RESET);

                    continue;
                }

                if (entity instanceof Hamster) {
                    builder.append(ConsoleColors.CYAN_BACKGROUND);
                    builder.append(" H ");
                    builder.append(ConsoleColors.RESET);

                    continue;
                }

                if (entity instanceof Mouse) {
                    builder.append(ConsoleColors.GREEN_BACKGROUND);
                    builder.append(" M ");
                    builder.append(ConsoleColors.RESET);

                    continue;
                }

                if (entity instanceof Wall) {
                    builder.append(ConsoleColors.BLACK_BACKGROUND);
                    builder.append(ConsoleColors.BLACK);
                    builder.append("   ");
                    builder.append(ConsoleColors.RESET);
                    continue;
                }
                builder.append(ConsoleColors.WHITE_BACKGROUND);
                builder.append("   ");
                builder.append(ConsoleColors.RESET);
            }

            builder.append(ConsoleColors.WHITE_BACKGROUND);
            builder.append("   ");
            builder.append(ConsoleColors.RESET);
            builder.append("\n");

        }

        builder.append(horizontalLine(entities.length + 2, "   ", ConsoleColors.WHITE_BACKGROUND));
        System.out.println(builder.toString());

        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {

    }

    private String horizontalLine(int length, String characters, String color) {
        var builder = new StringBuilder();
        builder.append(color);
        for (int i = 0; i < length; i++) {
            builder.append(characters);
        }
        builder.append(ConsoleColors.RESET);
        builder.append("\n");
        return builder.toString();
    }
}
