package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Main extends Application {

    private Stage primaryStage;
    private Controller ctrl;
    private Board board;
    private Scene scene;

    @Override
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;

        board = new Board(this);
        this.ctrl = new Controller(board);

        this.primaryStage.setTitle("2048");
        scene = new Scene(board.getGridpane(), 400, 400);
        //scene.setOnKeyPressed(ctrl::inputKeys);
        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED,ctrl.inputKeys);
        this.primaryStage.setScene(scene);

        this.primaryStage.show();

    }

    void gameOver() {

        primaryStage.removeEventHandler(KeyEvent.KEY_PRESSED, ctrl.inputKeys);

        GridPane gridPane = new GridPane();
        gridPane.setPrefSize(400, 400);
        gridPane.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        gridPane.setOpacity(20);
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setAlignment(Pos.CENTER);

        Text text = new Text("GameOver !!");
        text.setFont(new Font(60));
        text.setFill(Color.WHITE);
        gridPane.add(text, 0, 0, 3, 1);

        Button continueButton = new Button("New game");
        continueButton.setFont(new Font(20));
        continueButton.setOnAction((e) -> this.newGame());
        gridPane.add(continueButton, 2, 3);

        scene = new Scene(gridPane, 400, 400);
        this.primaryStage.setScene(scene);


    }

    private void newGame() {

        new Main().start(new Stage());
        this.primaryStage.close();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
