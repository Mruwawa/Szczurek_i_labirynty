
# Szczurek i labirynty

Projekt na programowanie obiektowe jest to symulacja agentów - zwierząt w labiryncie.


Program można uruchomić za pomocą polecenia ```gradle run ```, lub zbudować za pomocą ```gradle jar```, a następnie uruchomić za pomocą ```java -jar Szczurek_i_labirynty-1.0-SNAPSHOT.jar``` w folderze ```Szczurek_i_labirynty\build\libs```

Symulacja włączy się bez żadnych plików konfiguracyjnych, jednak aby zmieniać jej parametry potrzebny będzie plik ```settings.json```

Jego struktura wygląda następująco:
```json
{
  "repeatCount": 1000,
  "rendererType": "WINDOW",
  "resultsWriterType": "CSV",
  "frameTime": 300,
  "animalNames": [
    "Kłapcio",
    "Dee dee",
    "Remi",
    "Reksio",
    "Derpy",
    "Azor",
    "Bambi",
    "Mario",
    "Luigi",
    "Waluigi"
  ],
  "simulationSettingsList": [
    {
      "labyrinthFileName": "labirynt 20x20.txt",
      "turnCount": 1000,
      "guardianCount": 1,
      "animalCounts": {
        "hamsters": 2,
        "gerbils": 2,
        "mousedeer": 2,
        "mice": 2,
        "rats": 2
      }
    },
    {
      "labyrinthFileName": "labirynt 15x15.txt",
      "turnCount": 1000,
      "guardianCount": 1,
      "animalCounts": {
        "hamsters": 2,
        "gerbils": 2,
        "mousedeer": 2,
        "mice": 2,
        "rats": 2
      }
    }
  ]
}
```
Gdzie:
```repeatCount``` to ilość powtórzeń każdej z symulacji
```rendererType``` to rodzaj renderera. Możliwe opcje: ```"WINDOW"```, ```"CONSOLE"``` .Można zostawić tą opcję pustą, by nie wizualizować symulacji.
```resultsWriterType``` to rodzaj wypisywania do pliku. Możliwe opcje to ```"CSV"``` lub puste (spowoduje brakiem zapisywania wyników do pliku)
```frameTime``` to czas wyświetlania każdej klatki przy wizualizacji
```animalNames``` to lista, z której wybrane zostaną imiona zwierząt w symulacji
```simulationSettingsList``` jest to lista obiektów konfiguracji symulacji, można ich dodać kilka - wtedy zostanie przeprowadzona każda z nich

Opcje pojedynczych symulacji:
```labyrinthFileName``` to plik zawierający mapę
```turnCount``` ilość tur w symulacji
```guardianCount``` ilość strażników (możliwe 1 albo 0)
```animalCounts``` ilość każdego typu zwierzęcia w labiryncie

## Pliki labiryntów
Przykładowy plik labiryntu:
```txt
###############
#       #   # #
#@##### # # # #
#   # # # # # #
# ### # # #   #
#     #@# ### #
#######     # #
#       ###@###
#@#######     #
#       # # # #
# ##### # # # #
# #     # # # #
# ## #@## ### #
#  # #        #
#### ##########

```

Gdzie ```#``` reprezentuje ścianę  a ```@``` reprezentuje przeszkodę
Jeśli chcemy dodać nowy plik labiryntu należy go dodać do folderu ```labyrinths``` w tym samym folderze co plik .jar
Musi on mieć wszystkie linijki tej samej długości.


##Dokumentacja projektu

![Alt text](readme_resources/0001.jpg?raw=true)
![Alt text](readme_resources/0002.jpg?raw=true)
![Alt text](readme_resources/0003.jpg?raw=true)
![Alt text](readme_resources/0004.jpg?raw=true)
![Alt text](readme_resources/0005.jpg?raw=true)