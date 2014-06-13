package org.bitbucket.dyatlov.tetris;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Dyatlov
 * Date: 05/05/14
 * Time: 20:20
 * To change this template use File | Settings | File Templates.
 */
public class Canvas {
    public void redraw(Collection<Coordinate> coordinates) {
        int offsetX = Integer.MAX_VALUE;
        int offsetY = Integer.MAX_VALUE;
        int width = Integer.MIN_VALUE;
        int height = Integer.MIN_VALUE;
        HashSet<Coordinate> set = new HashSet<Coordinate>(coordinates.size());
        for (Coordinate coordinate: coordinates) {
            offsetX = Math.min(offsetX, coordinate.getX());
            offsetY = Math.min(offsetY, coordinate.getY());
            width = Math.max(width, coordinate.getX());
            height = Math.max(height, coordinate.getY());
        }
        for (Coordinate coordinate : coordinates) {
            set.add(new Coordinate(coordinate.getX() - offsetX, coordinate.getY() - offsetY));
        }
        width = width  - offsetX + 1;
        height = height + offsetY + 1;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (set.contains(new Coordinate(j, i))) {
                    drawPixel();
                } else {
                    drawEmptySpace();
                }
            }
            newLine();
        }
        newLine();
    }
    private void drawPixel() {
        System.out.print('*');
    }

    private void drawEmptySpace() {
        System.out.print(' ');
    }

    private void newLine() {
        System.out.println();
    }
}

class CoordinatesComparator implements Comparator<Coordinate> {
    @Override
    public int compare(Coordinate o1, Coordinate o2) {
        if (o1.getY() < o2.getY()) {
            return -1;
        } else if (o1.getY() > o2.getY()) {
            return 1;
        } else {
            if (o1.getX() < o2.getX()) {
                return -1;
            } else if (o1.getX() > o2.getX()) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
