package Szczurki.Configuration;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class ConfigurationProvider {

    private static final String defaultConfigPath =
            Objects.requireNonNull(
                    ConfigurationProvider
                            .class
                            .getClassLoader()
                            .getResource("defaultConfiguration.json")
            ).getPath();

    public static ConfigurationData getConfiguration(String fileName) {
        try {
            var data = readAllLines(fileName);
            return new Gson().fromJson(data, ConfigurationData.class);

        } catch (Exception e) {
            if (!fileName.equals(defaultConfigPath)) {
                System.out.println("Wczytywanie konfiguracji nie powiodło się :(. Używam domyślnej konfiguracji.");
                return getConfiguration(defaultConfigPath);
            }
            System.out.println("Wczytywanie domyślnej konfiguracji też się nie powiodło :o");
            return null;
        }
    }

    private static String readAllLines(String fileName) throws IOException {
        var file = new FileReader(fileName);
        var reader = new BufferedReader(file);
        StringBuilder data = new StringBuilder();
        for (String line = reader.readLine(); line != null; line = reader.readLine()) {
            data.append(line);
        }
        return data.toString();
    }
}
