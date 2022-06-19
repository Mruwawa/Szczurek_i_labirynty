package Szczurki.Simulation.Visualization.Window;

import Szczurki.Configuration.ResourceProvider;
import Szczurki.Simulation.Entities.Animals.*;
import Szczurki.Simulation.Entities.Guardian;
import Szczurki.Simulation.Entities.Obstacle;
import Szczurki.Simulation.Entities.Wall;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImageLoader {

    private final String textureDirectory = "textures/";
    private final int _imgSize;

    public ImageLoader(int imgSize) {
        _imgSize = imgSize;
    }

    public Map<Class<?>, Image> getImageMappings() {
        var output = new HashMap<Class<?>, Image>();

        var classes = List.of(
                Rat.class,
                Mousedeer.class,
                Gerbil.class,
                Hamster.class,
                Mouse.class,
                Wall.class,
                Obstacle.class,
                Guardian.class);

        classes.forEach(type -> {
            try {
                var inputStream = ResourceProvider.getResource(textureDirectory + type.getSimpleName() + ".png");
                var originalImg = ImageIO.read(inputStream);
                var img = resizeImage(originalImg, _imgSize, _imgSize);
                output.put(type, img);
            } catch (Exception e) {
                System.out.println("Wczytywanie tekstury dla " + type.getSimpleName() + " nie powiodlo sie, zamiast niej zostanie uzyty kolor.");
            }
        });

        return output;
    }

    private BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        var resultingImage =
                originalImage
                        .getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
        var outputImage =
                new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        outputImage
                .getGraphics()
                .drawImage(resultingImage, 0, 0, null);
        return outputImage;
    }
}
