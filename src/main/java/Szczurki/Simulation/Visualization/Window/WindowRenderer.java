package Szczurki.Simulation.Visualization.Window;

import Szczurki.Simulation.Entities.Interfaces.IEntity;
import Szczurki.Simulation.Visualization.IRenderer;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class WindowRenderer implements IRenderer {
    private final MapPanel _panel;
    private final JFrame _window;
    private final int _frameTime;


    public WindowRenderer(IEntity[][] entities, int frameTime) {
        _window = new JFrame();
        _window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _panel = new MapPanel(entities);
        _window.getContentPane().add(_panel);
        _window.pack();
        _window.setVisible(true);
        _frameTime = frameTime;
    }


    public void render(IEntity[][] entities) {
        _panel.updateGrid(entities);

        try {
            TimeUnit.MILLISECONDS.sleep(_frameTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        _window.setVisible(false);
        _window.dispose();
    }
}
