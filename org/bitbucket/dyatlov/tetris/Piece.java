package org.bitbucket.dyatlov.tetris;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Dyatlov
 * Date: 05/05/14
 * Time: 14:21
 * To change this template use File | Settings | File Templates.
 */
public class Piece implements Drawable {
    private int x;
    private int y;
    private Angle angle;
    private Shape shape;

    public Piece(Shape shape) {
        this.x = 0;
        this.y = 0;
        this.angle = new Angle();
        this.shape = shape;
    }

    public Piece(Piece anotherPiece) {
        this.x = anotherPiece.x;
        this.y = anotherPiece.y;
        this.angle = new Angle(anotherPiece.angle);
        this.shape = anotherPiece.shape;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Angle getAngle() {
        return angle;
    }

    public int getWidth() {
        return shape.getWidth();
    }

    public Set<Coordinate> getCoordinates() {
        HashSet<Coordinate> coordinates = new HashSet<Coordinate>();
        Coordinate center = shape.getCenter();
        if (center != null) {
            for (Coordinate coordinate: shape.getCoordinates()) {
                // Move center to the origin
                int xt = coordinate.getX() - center.getX();
                int xy = coordinate.getY() - center.getY();
                // Rotate
                Coordinate rotated = angle.rotate(new Coordinate(xt, xy));
                // Move center back and add parent coordinates
                int xNew = rotated.getX() + center.getX() + x;
                int yNew = rotated.getY() + center.getY() + y;
                coordinates.add(new Coordinate(xNew, yNew));
            }
        } else { // Don't rotate
            for (Coordinate coordinate: shape.getCoordinates()) {
                // Just add parent coordinate
                coordinates.add(new Coordinate(coordinate.getX() + x,  coordinate.getY() + y));
            }
        }
        return coordinates;
    }
}
