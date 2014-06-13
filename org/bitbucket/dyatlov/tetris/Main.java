package org.bitbucket.dyatlov.tetris;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Board board = new Board(20, 20);

        // I shape
        Set<Coordinate> iCoordinates = new HashSet<Coordinate>(4);
        iCoordinates.add(new Coordinate(0, 0));
        iCoordinates.add(new Coordinate(1, 0));
        iCoordinates.add(new Coordinate(2, 0));
        iCoordinates.add(new Coordinate(3, 0));
        Shape iShape = new Shape(iCoordinates, new Coordinate(1, 0));

        // L shape
        Set<Coordinate> lCoordinates = new HashSet<Coordinate>(4);
        lCoordinates.add(new Coordinate(0, 0));
        lCoordinates.add(new Coordinate(0, 1));
        lCoordinates.add(new Coordinate(0, 2));
        lCoordinates.add(new Coordinate(1, 2));
        Shape lShape = new Shape(lCoordinates, new Coordinate(1, 1));

        // J shape
        Set<Coordinate> jCoordinates = new HashSet<Coordinate>(4);
        jCoordinates.add(new Coordinate(1, 0));
        jCoordinates.add(new Coordinate(1, 1));
        jCoordinates.add(new Coordinate(0, 2));
        jCoordinates.add(new Coordinate(1, 2));
        Shape jShape = new Shape(jCoordinates, new Coordinate(0, 1));

        // Z shape
        Set<Coordinate> zCoordinates = new HashSet<Coordinate>(4);
        zCoordinates.add(new Coordinate(1, 0));
        zCoordinates.add(new Coordinate(0, 1));
        zCoordinates.add(new Coordinate(1, 1));
        zCoordinates.add(new Coordinate(0, 2));
        Shape zShape = new Shape(zCoordinates, new Coordinate(1, 1));

        // O shape
        Set<Coordinate> oCoordinates = new HashSet<Coordinate>(4);
        oCoordinates.add(new Coordinate(1, 0));
        oCoordinates.add(new Coordinate(0, 1));
        oCoordinates.add(new Coordinate(1, 1));
        oCoordinates.add(new Coordinate(0, 0));
        Shape oShape = new Shape(oCoordinates, null);

        HashSet<Shape> shapes = new HashSet<Shape>(4);
        shapes.addAll(Arrays.asList(iShape, lShape, jShape, zShape, oShape));
        Canvas canvas = new Canvas();

        Tetris tetris = new Tetris(board, shapes, canvas);
        tetris.start();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input;
            while ((input = br.readLine()) != null) {
                if (!tetris.setInput(input)) {
                    System.out.println("Game over");
                    System.exit(0);
                }
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
