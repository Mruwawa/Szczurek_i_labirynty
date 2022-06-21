package Szczurki.Utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Klasa pomocnicza reprezentująca wektor
 */
public class Vector {
    public int x, y;

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @param other Wektor, z którym chcemy porównać ten wektor
     * @return Czy są takie same pod względem współrzędnych
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Vector))
            return false;

        Vector otherVector = ((Vector) other);

        return otherVector.x == this.x && otherVector.y == this.y;
    }

    public void set(Vector newValue) {
        this.x = newValue.x;
        this.y = newValue.y;
    }

    /**
     * Metoda dodaje do tego wektora wartość innego
     * @param other Wektor, który chcemy dodać
     */
    public void add(Vector other) {
        this.x += other.x;
        this.y += other.y;
    }

    /**
     * Metoda odwraca ten wektor
     */
    public void reverse() {
        this.x *= -1;
        this.y *= -1;
    }

    /**
     * @return Kopia tego wekora
     */
    public Vector copy() {
        return new Vector(this.x, this.y);
    }

    /**
     * @return Nowy, odwrócony wektor
     */
    public Vector reversed() {
        return new Vector(-this.x, -this.y);
    }

    public Vector turnRight() {
        int y=this.x;
        int x=-this.y;
        return new Vector(x,y);
    }

    public Vector turnLeft() {
        int x=this.y;
        int y=-this.x;
        return new Vector(x,y);
    }

    public static Vector ZERO() {
        return new Vector(0, 0);
    }

    public static Vector UP() {
        return new Vector(0, -1);
    }

    public static Vector DOWN() {
        return new Vector(0, 1);
    }

    public static Vector LEFT() {
        return new Vector(-1, 0);
    }

    public static Vector RIGHT() {
        return new Vector(1, 0);
    }

    public static ArrayList<Vector> getAllDirections() {
        return new ArrayList<>(List.of(new Vector[]
                {
                        UP(), DOWN(), LEFT(), RIGHT()
                }));
    }

    /**
     * @param vector1 Wektor 1 do dodania
     * @param vector2 Wektor 2 do dodania
     * @return Nowy wektor ze zsumowanymi współrzędnymi podanych wektorów
     */
    public static Vector add(Vector vector1, Vector vector2) {
        return new Vector(vector1.x + vector2.x, vector1.y + vector2.y);
    }

    /**
     * @param maxX Maksymalne X
     * @param maxY Maksymalne Y
     * @return Losowy wektor z podanego przedziału
     */
    public static Vector getRandomVector(int maxX, int maxY) {
        var random = new Random();

        var chosenX = random.nextInt(maxX);
        var chosenY = random.nextInt(maxY);

        return new Vector(chosenX, chosenY);
    }

}
