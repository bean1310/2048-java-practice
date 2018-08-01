package sample;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class Controller {

    private Board board;

    Controller(Board board) { this.board = board; }

    EventHandler<KeyEvent> inputKeys = (event) -> {

        switch (event.getCode()) {

            case UP:
                board.move(Board.UP);
                board.mkTiles();
                break;

            case DOWN:
                board.move(Board.DOWN);
                board.mkTiles();
                break;

            case LEFT:
                board.move(Board.LEFT);
                board.mkTiles();
                break;

            case RIGHT:
                board.move(Board.RIGHT);
                board.mkTiles();
                break;

            default:
                break;

        }

    };

}
