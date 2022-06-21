package Szczurki.Simulation.Visualization.Window;

import Szczurki.Configuration.ResourceProvider;
import Szczurki.Utilities.Keys;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class ImageLoader {

    private final int _imgSize;

    public ImageLoader(int imgSize) {
        _imgSize = imgSize;
    }

    public Map<Class<?>, Image> getImageMappings() {
        var output = new HashMap<Class<?>, Image>();

        Keys.ENTITY_CLASSES.forEach(type -> {
            try {
                var inputStream = ResourceProvider.getResource(Keys.TEXTURE_DIRECTORY + "/" + type.getSimpleName() + Keys.TEXTURE_FILES_EXTENSION);
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
