package Szczurki.Simulation.Visualization.Console;

import Szczurki.Simulation.Entities.Interfaces.IEntity;
import Szczurki.Simulation.Visualization.IRenderer;

import java.util.concurrent.TimeUnit;


/**
 * Renderer wyświetlający aktualny stan symulacji w konsoli
 */
public class ConsoleRenderer implements IRenderer {

    private final int _frameTime;

    public ConsoleRenderer(int frameTime)
    {
        _frameTime = frameTime;
    }

    /**
     * @param entities Tablica reprezentująca mapę
     */
    public void render(IEntity[][] entities) {
        var builder = new StringBuilder();

        //dopisujemy do stringa kod czyszczący konsolę
        builder.append("\033[H\033[2J");
        for (int y = 0; y < entities[0].length; y++) {
            for (int x = 0; x < entities.length; x++) {
                //dopisujemy do stringa reprezentację elementu mapy
                if (entities[x][y] != null && ConsoleRendererKeys.COLOR_MAPPINGS.containsKey(entities[x][y].getClass())) {
                    builder.append(ConsoleRendererKeys.COLOR_MAPPINGS.get(entities[x][y].getClass()));
                    builder.append(ConsoleRendererKeys.RESET);
                    continue;
                }
                //jeśli to pole jest puste wypisujemy biały kwardat
                builder.append(ConsoleRendererKeys.WHITE_BACKGROUND);
                builder.append("   ");
                builder.append(ConsoleRendererKeys.RESET);
            }
            builder.append("\n");
        }

        System.out.println(builder);

        //czekamy określoną ilość milisekund przed następnym
        //odświeżeniem mapy
        try {
            TimeUnit.MILLISECONDS.sleep(_frameTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda zatrzymująca działanie renderera
     * W tym przypadku nic nie robi, bo jest to niepotrzebne
     */
    @Override
    public void stop() {

    }
}
