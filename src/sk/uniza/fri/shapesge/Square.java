package sk.uniza.fri.shapesge;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 * A square that can be manipulated and that draws itself on a canvas.
 *
 * @author original: Michael Kölling and David J. Barnes
 * @author engine: Ján Janech
 * @version 1.0  (9.11.2022)
 */
@SuppressWarnings("unused")
public class Square {
    private final SquareDrawable drawable;
    private int size;
    private int xPosition;
    private int yPosition;
    private Color color;
    private boolean isVisible;

    /**
     * Create a new square at default position with default color.
     */
    @SuppressWarnings("unused")
    public Square() {
        this(60, 50);
    }

    @SuppressWarnings("unused")
    public Square(int x, int y) {
        this.size = 30;
        this.xPosition = x;
        this.yPosition = y;
        this.color = Color.red;
        this.isVisible = false;

        this.drawable = new SquareDrawable();
    }

    /**
     * Make this square visible. If it was already visible, do nothing.
     */
    @SuppressWarnings("unused")
    public void makeVisible() {
        if (this.isVisible) {
            return;
        }

        Game.getGame().registerDrawable(this.drawable);
        this.isVisible = true;
    }

    /**
     * Make this square invisible. If it was already invisible, do nothing.
     */
    @SuppressWarnings("unused")
    public void makeInvisible() {
        if (!this.isVisible) {
            return;
        }

        Game.getGame().unregisterDrawable(this.drawable);
        this.isVisible = false;
    }

    /**
     * Move the square a few pixels to the right.
     */
    @SuppressWarnings("unused")
    public void moveRight() {
        this.moveHorizontal(20);
    }

    /**
     * Move the square a few pixels to the left.
     */
    @SuppressWarnings("unused")
    public void moveLeft() {
        this.moveHorizontal(-20);
    }

    /**
     * Move the square a few pixels up.
     */
    @SuppressWarnings("unused")
    public void moveUp() {
        this.moveVertical(-20);
    }

    /**
     * Move the square a few pixels down.
     */
    @SuppressWarnings("unused")
    public void moveDown() {
        this.moveVertical(20);
    }

    /**
     * Move the square horizontally by 'distance' pixels.
     */
    @SuppressWarnings("unused")
    public void moveHorizontal(int distance) {
        this.xPosition += distance;
    }

    /**
     * Move the square vertically by 'distance' pixels.
     */
    @SuppressWarnings("unused")
    public void moveVertical(int distance) {
        this.yPosition += distance;
    }

    /**
     * Change the size to the new size (in pixels). Size must be greater or equal 0.
     */
    @SuppressWarnings("unused")
    public void changeSize(int newSize) {
        this.size = newSize;
    }

    /**
     * Change the color.
     */
    @SuppressWarnings("unused")
    public void changeColor(String newColor) {
        this.color = Game.getGame().getParser().parseColor(newColor);
    }

    private class SquareDrawable extends GameDrawable {
        @Override
        public void draw(Graphics2D canvas) {
            if (!Square.this.isVisible) {
                return;
            }

            var shape = new Rectangle2D.Double(Square.this.xPosition, Square.this.yPosition, Square.this.size, Square.this.size);
            canvas.setColor(Square.this.color);
            canvas.fill(shape);
        }
    }
}
