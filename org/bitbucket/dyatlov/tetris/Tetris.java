package org.bitbucket.dyatlov.tetris;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Dyatlov
 * Date: 05/05/14
 * Time: 19:59
 * To change this template use File | Settings | File Templates.
 */
public class Tetris {
    private Board board;
    private List<Shape> shapes;
    Canvas canvas;

    public Tetris(Board board, Set<Shape> shapes, Canvas canvas) {
        this.board = board;
        this.shapes = new ArrayList<Shape>();
        this.shapes.addAll(shapes);
        this.canvas = canvas;
    }

    private Piece getRandomPiece() {
        // Get random shape
        Shape shape = shapes.get(new Random().nextInt(shapes.size()));
        Piece piece = new Piece(shape);
        // Set random x position
        piece.setX(new Random().nextInt(board.getWidth() - piece.getWidth()));
        return piece;
    }

    public void start() {
        if (!this.board.addPiece(getRandomPiece())) {
            throw new IllegalStateException();
        }
        canvas.redraw(board.getCoordinates());
    }

    boolean setInput(String c) {
        boolean valid;
        if (c.equals("a")) {
            valid = board.moveLeft();
        } else if (c.equals("d")) {
            valid = board.moveRight();
        } else if (c.equals("w")) {
            valid = board.rotateLeft();
        } else if (c.equals("s")) {
            valid = board.rotateRight();
        } else {
            return true;
        }
        if (valid) {
            canvas.redraw(board.getCoordinates());
        }
        if (board.isStuck()) {
            if (board.addPiece(getRandomPiece())) {
                canvas.redraw(board.getCoordinates());
            } else {
                return false;
            }
        }
        return true;
    }
}
