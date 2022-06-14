package Szczurki.Utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Vector {
    public int x = 0, y = 0;

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

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

    public void add(Vector other) {
        this.x += other.x;
        this.y += other.y;
    }

    public void reverse() {
        this.x *= -1;
        this.y *= -1;
    }

    public Vector reversed() {
        return new Vector(-this.x, -this.y);
    }

    public Vector turnLeft() {
        int y=this.x;
        int x=-this.y;
        return new Vector(x,y);
    }

    public Vector turnRight() {
        int x=this.y;
        int y=-this.x;
        return new Vector(x,y);
    }

    public Vector copy() {
        return new Vector(this.x, this.y);
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
        return new ArrayList<Vector>(List.of(new Vector[]
                {
                        UP(), DOWN(), LEFT(), RIGHT()
                }));
    }

    public static Vector add(Vector vector1, Vector vector2) {
        return new Vector(vector1.x + vector2.x, vector1.y + vector2.y);
    }

    public static Vector getRandomVector(int maxX, int maxY) {
        var random = new Random();

        var chosenX = random.nextInt(maxX);
        var chosenY = random.nextInt(maxY);

        return new Vector(chosenX, chosenY);
    }

}
