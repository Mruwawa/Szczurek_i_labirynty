package Szczurki.Simulation.Visualization.Window;

import Szczurki.Simulation.Entities.Interfaces.IEntity;
import Szczurki.Simulation.Visualization.IRenderer;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class WindowRenderer implements IRenderer {
    private final GridPanel _panel;
    private final JFrame _window;


    public WindowRenderer(IEntity[][] entities) {
        _window = new JFrame();
        _window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _panel = new GridPanel(entities);
        _window.getContentPane().add(_panel);
        _window.pack();
        _window.setVisible(true);
    }


    public void render(IEntity[][] entities) {
        _panel.updateGrid(entities);

        try {
            TimeUnit.MILLISECONDS.sleep(500);
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
