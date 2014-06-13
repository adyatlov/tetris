package org.bitbucket.dyatlov.tetris;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Dyatlov
 * Date: 05/05/14
 * Time: 14:22
 * To change this template use File | Settings | File Templates.
 */
public class Board implements Drawable {
    private final int width;
    private final int height;
    private Piece currentPiece;
    private Shape shape;
    private HashSet<Coordinate> coordinates;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.currentPiece = null;

        // Add coordinates of the board edges
        coordinates = new HashSet<Coordinate>();
        // Bottom edge
        for (int i = -1; i <= width; i++) {
            coordinates.add(new Coordinate(i, height));
        }
        // Left and right edges
        for (int i = 0; i <= height; i++) {
            coordinates.add(new Coordinate(-1, i));
            coordinates.add(new Coordinate(width, i));
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean addPiece(Piece piece) {
        if (currentPiece != null) {
            coordinates.addAll(currentPiece.getCoordinates());
        }
        if (!canPlace(piece)) {
            return false;
        }
        currentPiece = piece;
        return true;
    }

    private boolean transform(Transformation transformation) {
        Piece piece = transformation.makeTransformed(currentPiece);
        if (!canPlace(piece)) {
            return false;
        }
        currentPiece = piece;
        return true;
    }

    public boolean moveLeft() {
        return transform(Transformation.MOVE_LEFT);
    }

    public boolean moveRight() {
        return transform(Transformation.MOVE_RIGHT);
    }

    public boolean rotateLeft() {
        return transform(Transformation.ROTATE_LEFT);
    }

    public boolean rotateRight() {
        return transform(Transformation.ROTATE_RIGHT);
    }

    public boolean isStuck() {
        List<Transformation> transformations = Arrays.asList(
                Transformation.MOVE_LEFT,
                Transformation.MOVE_RIGHT,
                Transformation.ROTATE_LEFT,
                Transformation.ROTATE_RIGHT);
        for (Transformation transformation: transformations) {
            Piece piece = transformation.makeTransformed(currentPiece);
            if (canPlace(piece)) {
                return false;
            }
        }
        return true;
    }

    private boolean canPlace(Piece piece) {
        for(Coordinate coordinate: piece.getCoordinates()) {
            if (coordinate.getX() < 0 ||
                coordinate.getX() >= width ||
                coordinate.getY() < 0 ||
                coordinate.getY() >= height ||
                coordinates.contains(coordinate)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Set<Coordinate> getCoordinates() {
        Set<Coordinate> set = new HashSet<Coordinate>();
        set.addAll(coordinates);
        if (currentPiece != null) {
            set.addAll(currentPiece.getCoordinates());
        }
        return set;
    }
}

abstract class Transformation {
    public static final Transformation MOVE_LEFT = new MoveLeft();
    public static final Transformation MOVE_RIGHT = new MoveRight();
    public static final Transformation ROTATE_LEFT = new RotateLeft();
    public static final Transformation ROTATE_RIGHT = new RotateRight();

    public final Piece makeTransformed(Piece piece) {
        Piece newPiece = new Piece(piece);
        transform(newPiece);
        return newPiece;
    }

    abstract protected void transform(Piece piece);
}

class MoveLeft extends Transformation {
    @Override
    protected void transform(Piece piece) {
        piece.setX(piece.getX() - 1);
        piece.setY(piece.getY() + 1);
    }
}

class MoveRight extends Transformation {
    @Override
    protected void transform(Piece piece) {
        piece.setX(piece.getX() + 1);
        piece.setY(piece.getY() + 1);
    }
}

class RotateLeft extends Transformation {
    @Override
    protected void transform(Piece piece) {
        piece.getAngle().transformLeft();
        piece.setY(piece.getY() + 1);
    }
}

class RotateRight extends Transformation {
    @Override
    public void transform(Piece piece) {
        piece.getAngle().transformRight();
        piece.setY(piece.getY() + 1);
    }
}