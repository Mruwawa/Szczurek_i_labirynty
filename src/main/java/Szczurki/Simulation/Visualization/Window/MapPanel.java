package Szczurki.Simulation.Visualization.Window;

import Szczurki.Simulation.Entities.Interfaces.IEntity;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * Metoda rozszerzająca panel interfejsu z bibioteki javax.swing
 */
public class MapPanel extends JPanel {

    private IEntity[][] _grid;
    private final int squareSize;
    /**
     * Lista mapująca klasę na odpowiadającą jej teksturę
     */
    private final Map<Class<?>, Image> imageMappings;


    /**
     * @param entities Tablica reprezentująca mapę
     */
    public MapPanel(IEntity[][] entities) {
        _grid = entities;
        //ustawiamy rozmiar pojedyńczej tekstury tak
        //żeby okienko miało maksymalnie 1000 pikseli wysokości i szerokości
        squareSize = 1000 / entities[0].length;
        imageMappings = new ImageLoader(squareSize).getImageMappings();
    }

    /**
     * @param newGrid Tablica reprezentująca mapę
     */
    public void updateGrid(IEntity[][] newGrid) {
        _grid = newGrid;
        this.repaint();
    }

    /**
     * Metoda nadpisana z klasy JPanel
     * @return Rozmiar okienka
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(_grid.length * squareSize, _grid[0].length * squareSize);
    }


    /**
     * Metoda wyświetlająca element w okienku
     * nadpisana z klasy JPAnel
     * @param g Element UI
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Color gColor = g.getColor();

        //dla każdego elementu na planszy
        for (int y = 0; y < _grid[0].length; y++) {
            for (int x = 0; x < _grid.length; x++) {

                var gridItem = _grid[x][y];
                //Jeżeli pole na mapie jest puste przechodzimy do kolejnej iteracji
                if (gridItem == null) {
                    continue;
                }

                //Jeżeli istnieje tekstura dla tego elementu to wyświetlamy ją
                if (imageMappings.containsKey(gridItem.getClass())) {
                    var mapping = imageMappings.get(gridItem.getClass());
                    g.drawImage(mapping, x * squareSize, y * squareSize, null);
                    continue;
                }
                //Jeżeli tesktura nie istnieje (na przykład nie udało się jej wczytać)
                //To wyświetlamy kolor odpowiedni dla tego elementu
                if (WindowRendererKeys.COLOR_MAPPINGS.containsKey(gridItem.getClass())) {
                    g.setColor(WindowRendererKeys.COLOR_MAPPINGS.get(gridItem.getClass()));
                    g.fillRect(x * squareSize, y * squareSize, squareSize, squareSize);
                }
            }
        }
        g.setColor(gColor);
    }
}
