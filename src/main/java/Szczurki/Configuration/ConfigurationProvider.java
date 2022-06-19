package Szczurki.Configuration;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConfigurationProvider {
    private static final String defaultConfigFileName = "defaultConfiguration.json";

    public static ConfigurationData getConfiguration(String fileName) {
        try {
            var data = readAllLines(fileName);
            return new Gson().fromJson(data, ConfigurationData.class);

        } catch (Exception e) {
            if (!fileName.equals(defaultConfigFileName)) {
                System.out.println("Wczytywanie konfiguracji nie powiodlo sie :(. Uzywam domyslnej konfiguracji.");
                return getConfiguration(defaultConfigFileName);
            }
            System.out.println("Wczytywanie domyslnej konfiguracji tez sie nie powiodlo :o");
            return null;
        }
    }

    private static String readAllLines(String fileName) throws IOException {
        var input = ResourceProvider.getResource(fileName);
        var reader = new BufferedReader(new InputStreamReader(input));
        var builder = new StringBuilder();

        for (String line = reader.readLine(); line != null; line = reader.readLine()) {
            builder.append(line);
        }

        return builder.toString();
    }
}
