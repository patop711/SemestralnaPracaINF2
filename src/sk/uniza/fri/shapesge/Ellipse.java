package sk.uniza.fri.shapesge;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

/**
 * A circle that can be manipulated and that draws itself on a canvas.
 *
 * @author original: Michael Kölling and David J. Barnes
 * @author engine: Ján Janech
 * @version 1.0  (9.11.2022)
 */

@SuppressWarnings("unused")
public class Ellipse {
    private final EllipseDrawable drawable;
    private int diameterX;
    private int diameterY;
    private int xPosition;
    private int yPosition;
    private Color color;
    private boolean isVisible;

    /**
     * Create a new circle at default position with default color.
     */
    @SuppressWarnings("unused")
    public Ellipse() {
        this(20, 60, 20, 60);
    }

    @SuppressWarnings("unused")
    public Ellipse(int diameterX, int diameterY, int x, int y) {
        this.diameterX = diameterX;
        this.diameterY = diameterY;
        this.xPosition = x;
        this.yPosition = y;
        this.color = Color.white;
        //Color.black bolo pred tým
        this.isVisible = false;

        this.drawable = new EllipseDrawable();
    }

    public void setX(int xPosition) {
        this.xPosition = xPosition;
    }

    public void setY(int yPosition) {
        this.yPosition = yPosition;
    }

    public int getDiameterX() {
        return this.diameterX;
    }

    public int getDiameterY() {
        return this.diameterY;
    }

    public int getPoziciaX() {
        return this.xPosition;
    }

    public int getPoziciaY() {
        return this.yPosition;
    }

    /**
     * Make this circle visible. If it was already visible, do nothing.
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
     * Make this circle invisible. If it was already invisible, do nothing.
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
     * Move the circle a few pixels to the right.
     */
    @SuppressWarnings("unused")
    public void moveRight() {
        this.moveHorizontal(20);
    }

    /**
     * Move the circle a few pixels to the left.
     */
    @SuppressWarnings("unused")
    public void moveLeft() {
        this.moveHorizontal(-20);
    }

    /**
     * Move the circle a few pixels up.
     */
    @SuppressWarnings("unused")
    public void moveUp() {
        this.moveVertical(-20);
    }

    /**
     * Move the circle a few pixels down.
     */
    @SuppressWarnings("unused")
    public void moveDown() {
        this.moveVertical(20);
    }

    /**
     * Move the circle horizontally by 'distance' pixels.
     */
    @SuppressWarnings("unused")
    public void moveHorizontal(int distance) {
        this.xPosition += distance;
    }

    /**
     * Move the circle vertically by 'distance' pixels.
     */
    @SuppressWarnings("unused")
    public void moveVertical(int distance) {
        this.yPosition += distance;
    }

    /**
     * Change the size to the new size (in pixels). Size must be greater or equal 0.
     */
    @SuppressWarnings("unused")
    public void changeSize(int newDiameterX, int newDiameterY) {
        this.diameterX = newDiameterX;
        this.diameterY = newDiameterY;
    }

    /**
     * Change the color.
     */
    @SuppressWarnings("unused")
    public void changeColor(String newColor) {
        this.color = Game.getGame().getParser().parseColor(newColor);
    }

    private class EllipseDrawable extends GameDrawable {
        @Override
        public void draw(Graphics2D canvas) {
            if (!Ellipse.this.isVisible) {
                return;
            }

            var shape = new Ellipse2D.Double(Ellipse.this.xPosition, Ellipse.this.yPosition, Ellipse.this.diameterX, Ellipse.this.diameterY);
            canvas.setColor(Ellipse.this.color);
            canvas.fill(shape);
        }
    }
}
