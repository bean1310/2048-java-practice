package sample;

import javafx.scene.text.Font;
import javafx.scene.text.Text;

class Tile extends BaseTile {

    private int num;
    private Text value;
    private Board board;
    private boolean erase;
    private boolean merged;

    Tile(int x, int y, int num, Board board) {

        super(x, y);

        this.board = board;

        configTile(num);

    }

    private int getNum() { return num; }

    private void configTile(int num) {

        board.getGridpane().getChildren().remove(value);

        rectangle.setFill(Config.TILE_COLOR[num]);

        value = new Text(Integer.toString((int) Math.pow(2, num)));
        value.setFont(Font.font(30));
        value.setFill(Config.DARK_NUM_COLOR);

        this.num = num;

        setEraseFlag(false);

    }

    private void merge() {

        this.num += 1;
        this.merged = true;
        configTile(this.num);
        board.update(this);

    }


    Text getValue() { return value; }
    boolean getEraseFlag() { return this.erase; }
    boolean getMergedFlag() { return this.merged; }

    void setEraseFlag(boolean flag) { this.erase = flag; }
    void setMergedFlag(boolean flag) { this.merged = flag; }

    void findMergeTarget(int key) {

        Tile hitTile;

        switch (key) {
            case 0:
                for(int y = this.y - 1; y >= 0; y--) {

                    hitTile = this.board.getTile(this.x, y);

                    if( hitTile != null ) {
                        if( hitTile.getNum() == this.num && !hitTile.getMergedFlag()) {
                            board.delTile(this);
                            hitTile.merge();
                        }else{
                            this.y = y + 1;
                            board.update(this);
                        }
                        break;
                    }else{
                        if (y == 0) {
                            this.y = 0;
                            board.update(this);
                        }
                    }
                }
                break;
            case 1:
                for(int y = this.y + 1; y < Config.ROW_TILE_SIZE; y++) {

                    hitTile = this.board.getTile(this.x, y);

                    if( hitTile != null ) {
                        if( hitTile.getNum() == this.num && !hitTile.getMergedFlag()) {
                            board.delTile(this);
                            hitTile.merge();
                        }else{
                            this.y = y - 1;
                            board.update(this);
                        }
                        break;
                    }else{
                        if (y == Config.ROW_TILE_SIZE - 1) {
                            this.y = Config.ROW_TILE_SIZE - 1;
                            board.update(this);
                        }
                    }
                }
                break;
            case 2:
                for(int x = this.x - 1; x >=0; x--) {

                    hitTile = this.board.getTile(x, this.y);

                    if( hitTile != null ) {
                        if( hitTile.getNum() == this.num && !hitTile.getMergedFlag()) {
                            board.delTile(this);
                            hitTile.merge();
                        }else{
                            this.x = x + 1;
                            board.update(this);
                        }
                        break;
                    }else{
                        if (x == 0) {
                            this.x = 0;
                            board.update(this);
                        }
                    }
                }
                break;
            case 3:
                for(int x = this.x + 1; x < Config.COL_TILE_SIZE; x++) {

                    hitTile = this.board.getTile(x, this.y);

                    if( hitTile != null ) {
                        if( hitTile.getNum() == this.num && !hitTile.getMergedFlag()) {
                            board.delTile(this);
                            hitTile.merge();
                        }else{
                            this.x = x - 1;
                            board.update(this);
                        }
                        break;
                    }else{
                        if (x == Config.COL_TILE_SIZE - 1) {
                            this.x = Config.COL_TILE_SIZE -1;
                            board.update(this);
                        }
                    }
                }
                break;
        }

    }

}
