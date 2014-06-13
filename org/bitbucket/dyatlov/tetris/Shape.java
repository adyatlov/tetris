package org.bitbucket.dyatlov.tetris;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Dyatlov
 * Date: 05/05/14
 * Time: 16:40
 * To change this template use File | Settings | File Templates.
 */
public class Shape {
    private Set<Coordinate> coordinates;
    private Coordinate center;
    private int width;
    private int height;

    public Shape(Collection<Coordinate> coordinates, Coordinate center) {
        this.coordinates = new HashSet<Coordinate>();
        int offsetX = Integer.MAX_VALUE;
        int offsetY = Integer.MAX_VALUE;
        width = Integer.MIN_VALUE;
        height = Integer.MIN_VALUE;
        for (Coordinate coordinate : coordinates) {
            offsetX = Math.min(offsetX, coordinate.getX());
            offsetY = Math.min(offsetY, coordinate.getY());
            width = Math.max(width, coordinate.getX());
            height = Math.max(height, coordinate.getY());
        }
        for (Coordinate coordinate : coordinates) {
            this.coordinates.add(new Coordinate(
                    coordinate.getX() - offsetX,
                    coordinate.getY() - offsetY));
        }
        width = width - offsetX + 1;
        height = height + offsetY + 1;
        this.center = center;
    }

    public Set<Coordinate> getCoordinates() {
        return Collections.unmodifiableSet(coordinates);
    }

    public Coordinate getCenter() {
        if (center != null) {
            return new Coordinate(center.getX(), center.getY());
        } else {
            return null;
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
