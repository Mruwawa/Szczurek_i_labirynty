package Szczurki.Simulation.Visualization.Window;

import Szczurki.Simulation.Entities.Interfaces.IEntity;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

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

                var gridItem = _grid[x][y];

                if (gridItem == null) {
                    continue;
                }

                if (imageMappings.containsKey(gridItem.getClass())) {
                    var mapping = imageMappings.get(gridItem.getClass());

                    g.drawImage(mapping, x * squareSize, y * squareSize, null);
                    continue;
                }
                if (WindowRendererKeys.COLOR_MAPPINGS.containsKey(gridItem.getClass())) {
                    g.setColor(WindowRendererKeys.COLOR_MAPPINGS.get(gridItem.getClass()));
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
