package Szczurki.Configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ResourceProvider {
    public static InputStream getResource(final String fileName) throws FileNotFoundException {
        var input =
                ConfigurationProvider
                        .class
                        .getClassLoader()
                        .getResourceAsStream(fileName);

        if (input != null) return input;

        return new FileInputStream(fileName);
    }
}
