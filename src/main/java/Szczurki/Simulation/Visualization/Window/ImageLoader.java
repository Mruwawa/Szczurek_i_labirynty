package Szczurki.Simulation.Visualization.Window;

import Szczurki.Configuration.ResourceProvider;
import Szczurki.Utilities.Keys;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * Klasa odpowiedzialna za wczytywanie i skalowanie tekstur
 */
public class ImageLoader {

    private final int _imgSize;

    /**
     * @param imgSize Rozmiar pojedyńczej tekstury w pikselach (wszystkie są kwadratami)
     */
    public ImageLoader(int imgSize) {
        _imgSize = imgSize;
    }

    /**
     * @return Lista mapująca klasę na odpowiadającą jej teksturę
     */
    public Map<Class<?>, Image> getImageMappings() {
        var output = new HashMap<Class<?>, Image>();
        Keys.ENTITY_CLASSES.forEach(type -> {
            try {
                var inputStream = ResourceProvider.getResource(Keys.TEXTURE_DIRECTORY + "/" + type.getSimpleName() + Keys.TEXTURE_FILES_EXTENSION);
                var originalImg = ImageIO.read(inputStream);
                //skalujemy odpowiednio teksturę
                var img = resizeImage(originalImg, _imgSize, _imgSize);
                output.put(type, img);
            } catch (Exception e) {
                System.out.println("Wczytywanie tekstury dla " + type.getSimpleName() + " nie powiodlo sie, zamiast niej zostanie uzyty kolor.");
            }
        });

        return output;
    }

    /**
     * @param originalImage Obraz do przeskalowania
     * @param targetWidth Oczekiwana szerokość
     * @param targetHeight Oczekiwana wysokość
     * @return Przeskalowany obraz
     */
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
