package Szczurki.Simulation.Visualization;

import Szczurki.Simulation.Entities.Interfaces.IEntity;

public interface IRenderer {
    void render(IEntity[][] entities);
    void stop();
}
