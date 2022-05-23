package Szczurki.Utilities;

import java.util.ArrayList;
import java.util.List;

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

    public void set(Vector newValue)
    {
        this.x = newValue.x;
        this.y = newValue.y;
    }

    public void add(Vector other)
    {
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

    public Vector copy()
    {
        return new Vector(this.x, this.y);
    }

    public static Vector ZERO = new Vector(0, 0);
    public static Vector UP = new Vector(0, -1);
    public static Vector DOWN = new Vector(0, 1);
    public static Vector LEFT = new Vector(-1, 0);
    public static Vector RIGHT = new Vector(1, 0);

    public static ArrayList<Vector> getAllDirections() {
        return new ArrayList<Vector>(List.of(new Vector[]
                {
                        UP, DOWN, LEFT, RIGHT
                }));
    }

    public static Vector add(Vector vector1, Vector vector2)
    {
        return new Vector(vector1.x + vector2.x, vector1.y + vector2.y);
    }

}
