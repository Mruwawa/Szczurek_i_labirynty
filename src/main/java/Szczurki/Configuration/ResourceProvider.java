package Szczurki.Configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Klasa odpowiedzialna za wczytywanie danych z plików, które znajdują się w folderze resources w IDE lub są spakowne do pliku .jar
 */
public class ResourceProvider {
    /**
     * Ta metoda najpierw próbuje wczytać plik z paczki .jar, a jeśli takiego nie znajdzie próbuje
     * odczytać dane z folderu resources w IDE
     * @param fileName nazwa pliku
     * @return  InputStream zawierający wczytane dane
     * @throws FileNotFoundException wyjątek rzucany kiedy nie uda sie wczytać pliku
     */
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
