package Szczurki.Simulation.Visualization;

import Szczurki.Simulation.Entities.Animals.Rat;
import Szczurki.Simulation.Entities.Interfaces.IEntity;
import Szczurki.Simulation.Entities.Wall;

import java.util.concurrent.TimeUnit;

public class ConsoleRenderer implements IRenderer {
    public void render(IEntity[][] entities) {

        horizontalLine(entities.length + 2, "   ", ConsoleColors.WHITE_BACKGROUND);

        for (int y = 0; y < entities[0].length; y++) {

            System.out.print(ConsoleColors.WHITE_BACKGROUND);
            System.out.print("   ");
            System.out.print(ConsoleColors.RESET);

            for (int x = 0; x < entities.length; x++) {
                var entity= entities[x][y];
                if (entity instanceof Rat) {
                    System.out.print(ConsoleColors.YELLOW_BACKGROUND);
                    System.out.print(" S ");
                    System.out.print(ConsoleColors.RESET);

                    continue;
                }
                if (entity instanceof Wall) {
                    System.out.print(ConsoleColors.BLACK_BACKGROUND);
                    System.out.print(ConsoleColors.BLACK);
                    System.out.print("   ");
                    System.out.print(ConsoleColors.RESET);
                    continue;
                }
                System.out.print("   ");
            }

            System.out.print(ConsoleColors.WHITE_BACKGROUND);
            System.out.print("   ");
            System.out.print(ConsoleColors.RESET);
            System.out.print("\n");

        }

        horizontalLine(entities.length + 2, "   ", ConsoleColors.WHITE_BACKGROUND);

        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void horizontalLine(int length, String characters, String color) {
        System.out.print(color);
        for (int i = 0; i < length; i++) {
            System.out.print(characters);
        }
        System.out.print(ConsoleColors.RESET);
        System.out.print("\n");
    }
}
