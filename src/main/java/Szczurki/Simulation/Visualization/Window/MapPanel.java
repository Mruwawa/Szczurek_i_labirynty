package Szczurki.Simulation.Visualization.Window;

import Szczurki.Simulation.Entities.Interfaces.IEntity;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.Objects;

public class MapPanel extends JPanel {

    private IEntity[][] _grid;
    private final int squareSize;
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
                if (_grid[x][y] != null && WindowRendererKeys.COLOR_MAPPINGS.containsKey(_grid[x][y].getClass())) {
                    g.setColor(WindowRendererKeys.COLOR_MAPPINGS.get(_grid[x][y].getClass()));
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
