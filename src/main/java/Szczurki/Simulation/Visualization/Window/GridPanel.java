package Szczurki.Simulation.Visualization.Window;

import Szczurki.Simulation.Entities.Animals.*;
import Szczurki.Simulation.Entities.Guardian;
import Szczurki.Simulation.Entities.Interfaces.IEntity;
import Szczurki.Simulation.Entities.Obstacle;
import Szczurki.Simulation.Entities.Wall;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

import static java.util.Map.entry;

public class GridPanel extends JPanel {

    private IEntity[][] grid;
    private final int squareSize;
    private final Map<Class<?>, Color> colorMappings = Map.ofEntries(
            entry(Guardian.class, Color.red),
            entry(Rat.class, Color.yellow),
            entry(Mousedeer.class, Color.blue),
            entry(Gerbil.class, new Color(153, 0, 204)),
            entry(Hamster.class, Color.cyan),
            entry(Mouse.class, Color.green),
            entry(Wall.class, Color.black),
            entry(Obstacle.class, Color.magenta)
    );
    private final Map<Class<?>, Image> imageMappings;


    public GridPanel(IEntity[][] entities) {
        grid = entities;
        squareSize = 1000 / entities[0].length;
        imageMappings = new ImageLoader(squareSize).getImageMappings();
    }

    public void updateGrid(IEntity[][] newGrid) {
        grid = newGrid;
        this.repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(grid.length * squareSize, grid[0].length * squareSize);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Color gColor = g.getColor();

        for (int y = 0; y < grid[0].length; y++) {
            for (int x = 0; x < grid.length; x++) {
                if (grid[x][y] != null && imageMappings.containsKey(grid[x][y].getClass())) {
                    g.drawImage(imageMappings.get(grid[x][y].getClass()), x * squareSize, y * squareSize, null);
                    continue;
                }
                if (grid[x][y] != null && colorMappings.containsKey(grid[x][y].getClass())) {
                    g.setColor(colorMappings.get(grid[x][y].getClass()));
                    g.fillRect(x * squareSize, y * squareSize, squareSize, squareSize);
                    continue;
                }
                g.setColor(Color.white);
                g.fillRect(x * squareSize, y * squareSize, squareSize, squareSize);
            }

        }
        g.setColor(gColor);
    }
}
