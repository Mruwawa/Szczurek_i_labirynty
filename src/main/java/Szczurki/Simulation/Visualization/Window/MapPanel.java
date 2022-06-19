package Szczurki.Simulation.Visualization.Window;

import Szczurki.Simulation.Entities.Animals.*;
import Szczurki.Simulation.Entities.Guardian;
import Szczurki.Simulation.Entities.Interfaces.IEntity;
import Szczurki.Simulation.Entities.Obstacle;
import Szczurki.Simulation.Entities.Wall;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.Objects;

import static java.util.Map.entry;

public class MapPanel extends JPanel {

    private IEntity[][] _grid;
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


    public MapPanel(IEntity[][] entities) {
        _grid = entities;
        squareSize = 1000 / entities[0].length;
        imageMappings = new ImageLoader(squareSize).getImageMappings();
    }

    public void updateGrid(IEntity[][] newGrid) {
        _grid = newGrid;
        this.repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(_grid.length * squareSize, _grid[0].length * squareSize);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Color gColor = g.getColor();

        for (int y = 0; y < _grid[0].length; y++) {
            for (int x = 0; x < _grid.length; x++) {
                if (!Objects.isNull(_grid[x][y]) && imageMappings.containsKey(_grid[x][y].getClass())) {
                    g.drawImage(imageMappings.get(_grid[x][y].getClass()), x * squareSize, y * squareSize, null);
                    continue;
                }
                if (_grid[x][y] != null && colorMappings.containsKey(_grid[x][y].getClass())) {
                    g.setColor(colorMappings.get(_grid[x][y].getClass()));
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
