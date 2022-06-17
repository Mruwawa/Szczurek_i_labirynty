package Szczurki.Configuration;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConfigurationProvider {

    public static ConfigurationData getConfiguration(String fileName) {
        try {
            var data = readAllLines(fileName);
            return new Gson().fromJson(data, ConfigurationData.class);

        } catch (Exception e) {
            if (!fileName.equals("defaultConfiguration.json")) {
                System.out.println("Wczytywanie konfiguracji nie powiodlo sie :(. Uzywam domyslnej konfiguracji.");
                return getConfiguration("defaultConfiguration.json");
            }
            System.out.println("Wczytywanie domyslnej konfiguracji tez sie nie powiodlo :o");
            return null;
        }
    }

    private static String readAllLines(String fileName) throws IOException {
        var input = ResourceProvider.getResource(fileName);
        var reader = new BufferedReader(new InputStreamReader(input));

        StringBuilder data = new StringBuilder();
        for (String line = reader.readLine(); line != null; line = reader.readLine()) {
            data.append(line);
        }
        return data.toString();
    }
}
