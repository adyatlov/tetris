package org.bitbucket.dyatlov.tetris;

/**
* Created with IntelliJ IDEA.
* User: Dyatlov
* Date: 05/05/14
* Time: 18:03
* To change this template use File | Settings | File Templates.
*/
public class Angle {
    private static final int MIN_DEGREE = 0;
    private static final int MAX_DEGREE = 3;
    private int value;

    Angle() {
        this.value = MIN_DEGREE;
    }

    public Angle(Angle angle) {
        this.value = angle.value;
    }

    public int getValue() {
        return value;
    }

    public void transformLeft() {
        if (value > MIN_DEGREE) {
            value--;
        } else {
            value = MAX_DEGREE;
        }
    }

    public void transformRight() {
        if (value < MAX_DEGREE) {
            value++;
        } else {
            value = MIN_DEGREE;
        }
    }

    /**
     * Rotates coordinate to about the origin.
     * @param coordinate initial coordinates
     * @return rotated coordinates
     */
    public Coordinate rotate(Coordinate coordinate) {
        final double degree = Math.toRadians(90 * value);
        int cosA = (int) Math.round(Math.cos(degree));
        int sinA = (int) Math.round(Math.sin(degree));
        int x = cosA * coordinate.getX() - sinA * coordinate.getY();
        int y = sinA * coordinate.getX() + cosA * coordinate.getY();
        return new Coordinate(x,y);
    }
}
