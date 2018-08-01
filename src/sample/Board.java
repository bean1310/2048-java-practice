package sample;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.*;

class Board {

    static final int UP = 0;
    static final int DOWN = 1;
    static final int LEFT = 2;
    static final int RIGHT = 3;

    private GridPane gridpane;
    private List<Tile> tileList = new ArrayList<Tile>();

    private Main mainObj;

    Board(Main mainObj) {

        gridpane = new GridPane();
        gridpane.setBackground(new Background(new BackgroundFill(Config.BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
        gridpane.setHgap(10);
        gridpane.setVgap(10);
        gridpane.setPadding(new Insets(10, 10, 10, 10));

        this.mainObj = mainObj;

        mkBaseTiles();
        mkTiles();
        mkTiles();

    }

    private void tileSort(List tileList, int direction) {

        switch (direction) {

            case UP:
                Collections.sort(tileList, comparatorY);
                break;
            case DOWN:
                Collections.sort(tileList, comparatorY);
                Collections.reverse(tileList);
                break;

            case LEFT:
                Collections.sort(tileList, comparatorX);
                break;
            case RIGHT:
                Collections.sort(tileList, comparatorX);
                Collections.reverse(tileList);
                break;

        }

    }

    private void mkBaseTiles() {

        /* グリッドの表示 */
        for(int y = 0; y < Config.ROW_TILE_SIZE; y++) {
            for(int x = 0; x < Config.COL_TILE_SIZE; x++) {
                BaseTile basetile = new BaseTile(x, y);
                setTiles(basetile);
            }
        }

    }

    private void setTiles(BaseTile basetile) {

        gridpane.add(basetile.getRectangle(), basetile.getX() + 1, basetile.getY() + 1);
        gridpane.setHalignment(basetile.getRectangle(), HPos.CENTER);

    }

    private void setTiles(Tile tile) {

        gridpane.add(tile.getRectangle(), tile.getX() + 1, tile.getY() + 1);
        gridpane.setHalignment(tile.getRectangle(), HPos.CENTER);

        gridpane.add(tile.getValue(), tile.getX() + 1, tile.getY() + 1);
        gridpane.setHalignment(tile.getValue(), HPos.CENTER);

    }

    private Comparator<Tile> comparatorX = new Comparator<Tile>() {

        @Override
        public int compare(Tile o1, Tile o2) {

            if ( o1.getX() < o2.getX() ) {
                return -1;
            } else if ( o1.getX() > o2.getX() ) {
                return 1;
            } else {
                if ( o1.getY() < o2.getY() ) {
                    return -1;
                } else if ( o1.getY() > o2.getY() ) {
                    return 1;
                } else {
                    return 0;
                }
            }

        }
    };

    private Comparator<Tile> comparatorY = new Comparator<Tile>() {

        @Override
        public int compare(Tile o1, Tile o2) {

            if ( o1.getY() < o2.getY() ) {
                return -1;
            } else if ( o1.getY() > o2.getY() ) {
                return 1;
            } else {
                if ( o1.getX() < o2.getX() ) {
                    return -1;
                } else if ( o1.getX() > o2.getX() ) {
                    return 1;
                } else {
                    return 0;
                }
            }

        }
    };

    GridPane getGridpane() { return gridpane; }

    Tile getTile(int x, int y) {

        Tile matchTile = null;

        for (Tile tile : tileList) {
            if(tile.getX() == x && tile.getY() == y && !tile.getEraseFlag()) {
                matchTile = tile;
                break;
            }
        }

        return matchTile;

    }

    void move(int direction) {

        tileSort(tileList, direction);

        int cnt = 0;

        for (Tile tile : tileList) {
            cnt++;
            System.out.print(tile);
            System.out.println("(" + tile.getX() + "," + tile.getY() + ")");
            System.out.println("\n");
            tile.findMergeTarget(direction);
        }

        tileList.removeIf(Tile::getEraseFlag);

        for ( Tile tile : tileList ) {
            if ( tile.getMergedFlag() ) {
                tile.setMergedFlag(false);
            }
        }

        System.out.println("探索タイル数" + cnt + "\n");

        System.out.println("-------\n");

    }

    void mkTiles() {

        if ( tileList.size() == Config.ROW_TILE_SIZE * Config.COL_TILE_SIZE) {
            System.out.println("neko");
            mainObj.gameOver();
            return;
        }

        Random random = new Random();

        int randNumRow;
        int randNumCol;

        do {

            randNumRow = random.nextInt(Config.ROW_TILE_SIZE);
            randNumCol = random.nextInt(Config.COL_TILE_SIZE);

        }while(getTile(randNumCol, randNumRow) != null);

        Tile tile = new Tile(randNumCol, randNumRow, 1, this);
        setTiles(tile);
        tileList.add(tile);

    }

    void delTile(Tile tile) {

        gridpane.getChildren().remove(tile.getRectangle());
        gridpane.getChildren().remove(tile.getValue());

        tile.setEraseFlag(true);

    }

    void update(Tile tile) {

        gridpane.getChildren().remove(tile.getRectangle());
        gridpane.getChildren().remove(tile.getValue());
        setTiles(tile);

    }

}
