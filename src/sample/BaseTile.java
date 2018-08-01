package sample;

import javafx.scene.shape.Rectangle;

class BaseTile {

    protected Rectangle rectangle;

    protected int x;
    protected int y;

    BaseTile(int x, int y) {

        rectangle = new Rectangle();
        rectangle.setWidth(Config.TILE_WIDTH);
        rectangle.setHeight(Config.TILE_HEIGHT);
        rectangle.setFill(Config.EMPTY_TILE_COLOR);
        rectangle.setArcWidth(20);
        rectangle.setArcHeight(20);

        this.x = x;
        this.y = y;

    }

    int getX() { return x; }
    int getY() { return y; }
    Rectangle getRectangle() { return rectangle; }

}
